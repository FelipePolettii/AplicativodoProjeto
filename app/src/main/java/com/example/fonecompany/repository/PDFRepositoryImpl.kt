package com.example.fonecompany.repository

import com.example.fonecompany.retrofit.RetrofitInstance
import com.example.fonecompany.service.PDFService
import okhttp3.ResponseBody

class PDFRepositoryImpl: PDFRepository {
    val service: PDFService by lazy { RetrofitInstance.retrofit.create(PDFService::class.java)}
    override suspend fun downloadPDF(): ResponseBody {
        return service.downloadPDF()
    }

}