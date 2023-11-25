package com.example.fonecompany.model

import com.squareup.moshi.Json

data class ReportResDTO(
    @Json(name = "id") val id: String, @Json(name = "creationDate") val creationDate: Long
)