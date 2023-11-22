package com.example.fonecompany.model

import com.squareup.moshi.Json

data class TokenDTO(
    @Json(name = "token") val token: String,
    @Json(name = "refreshToken") val refreshToken: String,
    @Json(name = "isAdmin") val isAdmin: Boolean
)