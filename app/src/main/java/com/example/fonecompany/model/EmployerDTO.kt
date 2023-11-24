package com.example.fonecompany.model

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class EmployerDTO(
    @Json(name = "id") val id: String,
    @Json(name = "name") val name: String,
    @Json(name = "cpf") val cpf: String,
    @Json(name = "email") val email: String,
    @Json(name = "office") val office: String
):Parcelable