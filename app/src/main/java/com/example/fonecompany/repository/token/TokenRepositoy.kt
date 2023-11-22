package com.example.fonecompany.repository.token

import com.example.fonecompany.model.LoginRecDTO
import com.example.fonecompany.model.RefreshTokenDTO
import com.example.fonecompany.model.RefreshTokenResDTO
import com.example.fonecompany.model.TokenDTO
import kotlinx.coroutines.flow.Flow

interface TokenRepositoy {
    suspend fun login(loginRecDTO: LoginRecDTO): Flow<TokenDTO>
    suspend fun refreshToken(refreshTokenDTO: RefreshTokenDTO): Flow<RefreshTokenResDTO>
}