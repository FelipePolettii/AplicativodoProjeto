package com.example.fonecompany.service

import okhttp3.ResponseBody
import retrofit2.http.GET

interface PDFService {
    @GET("pdf-test.pdf")
    suspend fun downloadPDF(): ResponseBody
}