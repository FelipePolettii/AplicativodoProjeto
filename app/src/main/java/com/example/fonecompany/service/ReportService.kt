package com.example.fonecompany.service

import com.example.fonecompany.model.ReportResDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ReportService {
    @GET("report/findreports")
    suspend fun findreports(@Query("userId") userId: String, @Query("date") date: Long): List<ReportResDTO>
}