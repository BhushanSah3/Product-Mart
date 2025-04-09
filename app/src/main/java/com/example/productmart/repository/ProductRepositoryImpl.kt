package com.example.productmart.repository

import com.example.productmart.network.DummyJsonApi
import com.example.productmart.model.ProductResponse
import com.example.productmart.model.Product
import retrofit2.Response

class ProductRepositoryImpl(private val api: DummyJsonApi) : ProductRepository {

    override suspend fun getAllProducts(): Response<ProductResponse> {
        return api.getAllProducts()
    }

    override suspend fun getProductById(id: Int): Product {
        return api.getProductById(id)
    }
}

