package com.example.fonecompany.model

import java.util.Date

data class ReportResDTO (val list: List<ReportList>)
data class ReportList (val date: Date)