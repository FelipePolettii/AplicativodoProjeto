package com.example.fonecompany.repository.token

import com.example.fonecompany.model.LoginRecDTO
import com.example.fonecompany.model.RefreshTokenDTO
import com.example.fonecompany.model.RefreshTokenResDTO
import com.example.fonecompany.model.TokenDTO
import com.example.fonecompany.retrofit.RetrofitInstance
import com.example.fonecompany.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class TokenRepositoryImpl : TokenRepositoy {
    private val retrofit = RetrofitInstance.retrofit.create(UserService::class.java)
    override suspend fun login(loginRecDTO: LoginRecDTO): Flow<TokenDTO> = flow {
        emit(retrofit.login(loginRecDTO))
    }.flowOn(Dispatchers.IO)

    override suspend fun refreshToken(refreshTokenDTO: RefreshTokenDTO): Flow<RefreshTokenResDTO> =
        flow<RefreshTokenResDTO> {
            emit(retrofit.refreshtoken(refreshTokenDTO))
        }.flowOn(Dispatchers.IO)
}