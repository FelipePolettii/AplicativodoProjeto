package com.example.fonecompany.model

import com.squareup.moshi.Json

data class RefreshTokenDTO(@Json(name = "refreshToken") val refreshTokenDTO: String)