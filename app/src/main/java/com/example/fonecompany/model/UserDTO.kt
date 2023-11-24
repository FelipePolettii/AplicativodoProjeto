package com.example.fonecompany.model

import com.squareup.moshi.Json

data class UserDTO(@Json(name = "id")val id: String, @Json(name = "name")val name: String, @Json(name ="cpf")val cpf: String)