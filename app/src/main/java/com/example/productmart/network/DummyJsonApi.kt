package com.example.productmart.network

import com.example.productmart.model.Product
import com.example.productmart.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DummyJsonApi {

    @GET("products")
    suspend fun getAllProducts(): Response<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product
}

