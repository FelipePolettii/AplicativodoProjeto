package com.example.fonecompany.retrofit

import com.example.fonecompany.AppApplication
import com.example.fonecompany.repository.token.TokenDataStore
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response

class SessionInterceptor : Interceptor {
    val dataStore: TokenDataStore by lazy { TokenDataStore() }
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
        val token = runBlocking { dataStore.gettoken(AppApplication.instance).firstOrNull() }
        return if (token.isNullOrBlank()) {
            chain.proceed(chain.request())
        } else {
            chain.proceed(
                request.header("Authorization", "Bearer $token").build()
            )
        }
    }
}