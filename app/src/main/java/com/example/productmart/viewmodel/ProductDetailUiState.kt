package com.example.productmart.viewmodel

import com.example.productmart.model.Product

sealed class ProductDetailUiState {
    object Loading : ProductDetailUiState()
    data class Success(val product: Product) : ProductDetailUiState()
    data class Error(val message: String) : ProductDetailUiState()
}
