package com.example.fonecompany.retrofit

import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitInstance {
    private val interceptor =
        HttpLoggingInterceptor().apply { setLevel(HttpLoggingInterceptor.Level.BODY) }
    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
    private val moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    val retrofit =
        Retrofit.Builder().baseUrl("https://www.caceres.mt.gov.br/").client(client).addConverterFactory(
            MoshiConverterFactory.create(
                moshi
            )
        ).build()
}