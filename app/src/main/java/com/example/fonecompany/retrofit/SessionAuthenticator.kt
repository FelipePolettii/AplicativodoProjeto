package com.example.fonecompany.retrofit

import com.example.fonecompany.AppApplication
import com.example.fonecompany.model.RefreshTokenDTO
import com.example.fonecompany.repository.token.TokenDataStore
import com.example.fonecompany.repository.token.TokenRepositoryImpl
import com.example.fonecompany.repository.token.TokenRepositoy
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import java.io.IOException

class SessionAuthenticator : Authenticator {
    private val tokenRepository: TokenRepositoy by lazy { TokenRepositoryImpl() }
    private val tokenDataStore: TokenDataStore by lazy { TokenDataStore() }
    override fun authenticate(route: Route?, response: Response): Request? {
        val request = response.request
        if (request.header("Authorization").isNullOrBlank()) return null

        val newToken = try {
            runBlocking {
                val refreshToken =
                    tokenDataStore.getrefreshtoken(AppApplication.instance).firstOrNull() ?: ""
                val responsetoken =
                    tokenRepository.refreshToken(RefreshTokenDTO(refreshToken)).firstOrNull()?:throw IOException()
                tokenDataStore.savenewtoken(AppApplication.instance, responsetoken)
                tokenDataStore.getrefreshtoken(AppApplication.instance).firstOrNull()
            }
        } catch (err: ExpiredTokenException) {
            // The token has expired, we need to logout.
            runBlocking {
                tokenDataStore.logout(AppApplication.instance)
            }
            null
        } catch (err: Throwable) {
            // Another error occurred, we need to suppress it inside an IOException to avoid
            // a fatal crash.
            if (err !is IOException) throw AuthenticationException(cause = err)
            else throw err
        }

        return if (newToken.isNullOrBlank()) null
        else request.newBuilder().header("Authorization", "Bearer $newToken").build()
    }

}

class AuthenticationException(message: String? = null, cause: Throwable? = null) :
    IOException(message, cause)

class ExpiredTokenException(message: String? = null, cause: Throwable? = null) :
    IOException(message, cause)