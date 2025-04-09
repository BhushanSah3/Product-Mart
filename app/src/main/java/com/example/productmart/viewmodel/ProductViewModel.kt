package com.example.productmart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productmart.model.Product
import com.example.productmart.model.ProductResponse
import com.example.productmart.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ProductUiState {
    object Loading : ProductUiState()
    data class Success(val products: List<Product>) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct

    init {
        getAllProducts()
    }

    fun getAllProducts() {
        viewModelScope.launch {
            try {
                val response = repository.getAllProducts()
                if (response.isSuccessful) {
                    val products = response.body()?.products.orEmpty()
                    _uiState.value = ProductUiState.Success(products)
                } else {
                    _uiState.value = ProductUiState.Error("Failed to load products")
                }
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error("Something went wrong: ${e.message}")
            }
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            try {
                val product = repository.getProductById(id)
                _selectedProduct.value = product
            } catch (e: Exception) {
                _selectedProduct.value = null // You may want to handle errors better
            }
        }
    }
}
