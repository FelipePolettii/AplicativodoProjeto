package com.example.fonecompany.repository

import okhttp3.ResponseBody

interface PDFRepository {
    suspend fun downloadPDF(): ResponseBody
}