package com.example.fonecompany.model

import com.squareup.moshi.Json

data class RefreshTokenResDTO(@Json (name = "token")val token:String)