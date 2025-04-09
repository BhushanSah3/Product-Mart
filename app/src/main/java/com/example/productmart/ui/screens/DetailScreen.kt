package com.example.productmart.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.productmart.repository.ProductRepositoryImpl
import com.example.productmart.network.NetworkModule
import com.example.productmart.viewmodel.ProductDetailUiState
import com.example.productmart.viewmodel.ProductDetailViewModel
import com.example.productmart.viewmodel.ProductDetailViewModelFactory

@Composable
fun DetailScreen(productId: Int) {
    val viewModel: ProductDetailViewModel = viewModel(
        factory = ProductDetailViewModelFactory(
            ProductRepositoryImpl(NetworkModule.api),
            productId
        )
    )

    val uiState = viewModel.uiState.collectAsState().value

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (uiState) {
            is ProductDetailUiState.Loading -> {
                CircularProgressIndicator()
            }
            is ProductDetailUiState.Error -> {
                Text(
                    text = uiState.message,
                    color = MaterialTheme.colorScheme.error
                )
            }
            is ProductDetailUiState.Success -> {
                ProductDetailContent(product = uiState.product)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {

    DetailScreen(productId = 1)
}
