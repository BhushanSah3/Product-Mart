package com.example.productmart.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.productmart.repository.ProductRepository

class ProductDetailViewModelFactory(
    private val repository: ProductRepository,
    private val productId: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailViewModel(repository, SavedStateHandle(mapOf("productId" to productId))) as T
    }
}
