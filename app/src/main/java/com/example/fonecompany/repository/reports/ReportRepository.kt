package com.example.fonecompany.repository.reports

import com.example.fonecompany.model.ReportDetailResDTO
import com.example.fonecompany.model.ReportResDTO
import com.example.fonecompany.retrofit.RetrofitInstance
import com.example.fonecompany.service.ReportService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.ResponseBody

class ReportRepository {
    private val service: ReportService by lazy { RetrofitInstance.retrofit.create(ReportService::class.java) }
    suspend fun findreport(userId: String, date: Long): Flow<List<ReportResDTO>> = flow {
        emit(service.findreports(userId, date))
    }.flowOn(Dispatchers.IO)
    suspend fun findreportbyid(reportId: String): Flow<ReportDetailResDTO> = flow {
        emit(service.findreportbyid(reportId))
    }.flowOn(Dispatchers.IO)
    suspend fun downloadpdf(userId: String,reportId: String): Flow<ResponseBody> = flow {
        emit(service.downloadpdf(reportId,userId))
    }.flowOn(Dispatchers.IO)
}