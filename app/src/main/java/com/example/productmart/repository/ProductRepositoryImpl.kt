package com.example.productmart.repository

import com.example.productmart.network.DummyJsonApi
import com.example.productmart.model.ProductResponse
import com.example.productmart.model.Product
import retrofit2.Response



class ProductRepositoryImpl(private val apiService: DummyJsonApi) : ProductRepository {

    override suspend fun getAllProducts(): Response<ProductResponse> {
        return apiService.getAllProducts()
    }

    override suspend fun getProductById(id: Int): Product {
        return apiService.getProductById(id)
    }

    override suspend fun searchProducts(query: String): List<Product> {
        val response = apiService.searchProducts(query)
        return response.products
    }

    override suspend fun getCategories(): List<String> {
        return apiService.getCategories()
    }

    override suspend fun getProductsByCategory(category: String): List<Product> {
        return apiService.getProductsByCategory(category).products
    }

}
