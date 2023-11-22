package com.example.fonecompany.model

import com.squareup.moshi.Json

data class LoginRecDTO(@Json(name = "email") val email: String, @Json(name = "cpf") val cpf: String)