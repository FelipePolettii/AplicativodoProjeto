package com.example.fonecompany.service

import okhttp3.ResponseBody
import retrofit2.http.GET

interface PDFService {
    @GET("fotos_institucional_downloads/2.pdf")
    suspend fun downloadPDF(): ResponseBody
}