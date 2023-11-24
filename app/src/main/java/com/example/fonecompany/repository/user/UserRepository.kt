package com.example.fonecompany.repository.user

import com.example.fonecompany.model.EmployerDTO
import com.example.fonecompany.model.UserDTO
import com.example.fonecompany.retrofit.RetrofitInstance
import com.example.fonecompany.service.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class UserRepository {
    private val service: UserService by lazy { RetrofitInstance.retrofit.create(UserService::class.java) }
    suspend fun getalluser(): Flow<List<EmployerDTO>> = flow {
        emit(service.getalluser())
    }.flowOn(Dispatchers.IO)
    suspend fun me(): Flow<UserDTO> = flow {
        emit(service.me())
    }.flowOn(Dispatchers.IO)
    suspend fun finduser(userId: String): Flow<UserDTO> = flow {
        emit(service.finduser(userId))
    }.flowOn(Dispatchers.IO)
}