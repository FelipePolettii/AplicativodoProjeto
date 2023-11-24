package com.example.fonecompany.repository.reports

import com.example.fonecompany.model.ReportResDTO
import com.example.fonecompany.retrofit.RetrofitInstance
import com.example.fonecompany.service.ReportService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ReportRepository {
    private val service: ReportService by lazy { RetrofitInstance.retrofit.create(ReportService::class.java) }
    fun findreport(userId: String, date: Long): Flow<List<ReportResDTO>> = flow {
        emit(service.findreports(userId, date))
    }
}