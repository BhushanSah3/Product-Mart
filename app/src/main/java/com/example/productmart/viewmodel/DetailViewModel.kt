
package com.example.productmart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productmart.model.Product
import com.example.productmart.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ProductDetailState {
    object Loading : ProductDetailState()
    data class Success(val product: Product) : ProductDetailState()
    data class Error(val message: String) : ProductDetailState()
}

class DetailViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _state = MutableStateFlow<ProductDetailState>(ProductDetailState.Loading)
    val state: StateFlow<ProductDetailState> = _state

    fun loadProduct(id: Int) {
        viewModelScope.launch {
            try {
                val product = repository.getProductById(id)
                _state.value = ProductDetailState.Success(product)
            } catch (e: Exception) {
                _state.value = ProductDetailState.Error("Error: ${e.message}")
            }
        }
    }
}
