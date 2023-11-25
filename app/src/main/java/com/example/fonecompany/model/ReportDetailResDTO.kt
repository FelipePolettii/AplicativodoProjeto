package com.example.fonecompany.model

import com.squareup.moshi.Json

data class ReportDetailResDTO(
    @Json(name = "id") val id: String,
    @Json(name = "creationDate") val creationDate: Long,
    @Json(name = "salary") val salary: String,
    @Json(name = "inss") val inss: String,
    @Json(name = "irpf") val irpf: String,
    @Json(name = "transportationValue") val transportationValue: String,
    @Json(name = "deductions") val deductions: String,
    @Json(name = "salaryLiquid") val salaryLiquid: String,
    @Json(name = "userId") val userId: String,
    @Json(name = "name") val name: String,
    @Json(name = "office") val office: String
)
