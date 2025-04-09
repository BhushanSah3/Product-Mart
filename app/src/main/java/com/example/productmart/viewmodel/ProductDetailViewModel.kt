package com.example.productmart.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productmart.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val repository: ProductRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val uiState: StateFlow<ProductDetailUiState> = _uiState

    init {
        val productId = savedStateHandle["productId"] ?: -1
        fetchProduct(productId)
    }

    private fun fetchProduct(id: Int) {
        viewModelScope.launch {
            try {
                val product = repository.getProductById(id)
                _uiState.value = ProductDetailUiState.Success(product)
            } catch (e: Exception) {
                _uiState.value = ProductDetailUiState.Error("Failed to load product: ${e.message}")
            }
        }
    }
}
