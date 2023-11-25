package com.example.fonecompany.service

import com.example.fonecompany.model.ReportDetailResDTO
import com.example.fonecompany.model.ReportResDTO
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ReportService {
    @GET("report/findreports")
    suspend fun findreports(
        @Query("userId") userId: String, @Query("date") date: Long
    ): List<ReportResDTO>

    @GET("report/findreportbyid")
    suspend fun findreportbyid(@Query("reportId") reportId: String): ReportDetailResDTO

    @GET("report")
    suspend fun downloadpdf(@Query("reportId") reportId: String, @Query("userId")userId: String): ResponseBody
}