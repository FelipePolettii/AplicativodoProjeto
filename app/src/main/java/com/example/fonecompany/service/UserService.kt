package com.example.fonecompany.service

import com.example.fonecompany.model.LoginRecDTO
import com.example.fonecompany.model.RefreshTokenDTO
import com.example.fonecompany.model.RefreshTokenResDTO
import com.example.fonecompany.model.TokenDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UserService {
    @POST("user")
    suspend fun login(@Body loginRecDTO: LoginRecDTO): TokenDTO
    @POST("user/refreshtoken")
    suspend fun refreshtoken(@Body refreshTokenDTO: RefreshTokenDTO): RefreshTokenResDTO
}