package com.example.fonecompany.service

import com.example.fonecompany.model.EmployerDTO
import com.example.fonecompany.model.LoginRecDTO
import com.example.fonecompany.model.RefreshTokenDTO
import com.example.fonecompany.model.RefreshTokenResDTO
import com.example.fonecompany.model.TokenDTO
import com.example.fonecompany.model.UserDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserService {
    @POST("user")
    suspend fun login(@Body loginRecDTO: LoginRecDTO): TokenDTO
    @POST("user/refreshtoken")
    suspend fun refreshtoken(@Body refreshTokenDTO: RefreshTokenDTO): RefreshTokenResDTO
    @GET("user/getAll")
    suspend fun getalluser(): List<EmployerDTO>
    @GET("user/me")
    suspend fun  me(): UserDTO
    @GET("user/finduser")
    suspend fun  finduser(@Query("userId") userId: String): UserDTO
}