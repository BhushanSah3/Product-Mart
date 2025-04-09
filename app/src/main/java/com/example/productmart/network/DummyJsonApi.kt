package com.example.productmart.network

import com.example.productmart.model.Product
import com.example.productmart.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface DummyJsonApi {

    @GET("products")
    suspend fun getAllProducts(): Response<ProductResponse>

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String): ProductResponse

    @GET("products/categories")
    suspend fun getCategories(): List<String>

    @GET("products/category/{category}")
    suspend fun getProductsByCategory(@Path("category") category: String): ProductResponse
}
