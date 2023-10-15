package com.example.fonecompany.model

data class ReportDetailDTO(
    val name: String,
    val salary: Double,
    val inss: String,
    val irpf: String,
    val transportationValue: Double,
    val deductions: Double,
    val salaryLiquid: Double,
    val office: String,
)