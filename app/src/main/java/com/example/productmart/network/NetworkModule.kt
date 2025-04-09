package com.example.productmart.network

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkModule {
    private const val BASE_URL = "https://dummyjson.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val api: DummyJsonApi = retrofit.create(DummyJsonApi::class.java)
}
