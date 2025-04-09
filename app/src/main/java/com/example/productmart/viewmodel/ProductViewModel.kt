package com.example.productmart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.productmart.model.Product
import com.example.productmart.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class ProductUiState {
    object Loading : ProductUiState()
    data class Success(val products: List<Product>) : ProductUiState()
    data class Error(val message: String) : ProductUiState()
}

class ProductViewModel(private val repository: ProductRepository) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _uiState = MutableStateFlow<ProductUiState>(ProductUiState.Loading)
    val uiState: StateFlow<ProductUiState> = _uiState

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    private val _sortOption = MutableStateFlow("title_asc") // default sort
    val sortOption: StateFlow<String> = _sortOption.asStateFlow()

    init {
        fetchCategories()
        fetchProducts()
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        if (query.isBlank()) {
            fetchProducts()
        } else {
            searchProducts(query)
        }
    }

    private fun searchProducts(query: String) {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading
            try {
                val result = repository.searchProducts(query)
                _uiState.value = ProductUiState.Success(result)
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun getProductById(id: Int) {
        viewModelScope.launch {
            try {
                val product = repository.getProductById(id)
                _selectedProduct.value = product
            } catch (e: Exception) {
                _selectedProduct.value = null
            }
        }
    }

    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val result = repository.getCategories()
                _categories.value = result
            } catch (e: Exception) {
                _categories.value = emptyList()
            }
        }
    }

    fun selectCategory(category: String?) {
        _selectedCategory.value = category
        fetchProducts()
    }

    fun setSortOption(option: String) {
        _sortOption.value = option
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _uiState.value = ProductUiState.Loading
            try {
                val rawProducts = if (_selectedCategory.value != null) {
                    repository.getProductsByCategory(_selectedCategory.value!!)
                } else {
                    repository.getAllProducts().body()?.products.orEmpty()
                }

                val sorted = when (_sortOption.value) {
                    "price_asc" -> rawProducts.sortedBy { it.price }
                    "price_desc" -> rawProducts.sortedByDescending { it.price }
                    "title_asc" -> rawProducts.sortedBy { it.title }
                    "title_desc" -> rawProducts.sortedByDescending { it.title }
                    else -> rawProducts
                }

                _uiState.value = ProductUiState.Success(sorted)
            } catch (e: Exception) {
                _uiState.value = ProductUiState.Error("Failed to fetch products: ${e.message}")
            }
        }
    }
}