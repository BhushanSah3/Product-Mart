package com.example.productmart.repository

import com.example.productmart.model.ProductResponse
import com.example.productmart.model.Product
import retrofit2.Response


interface ProductRepository {
    suspend fun getAllProducts(): Response<ProductResponse>
    suspend fun getProductById(id: Int): Product
}

