package com.example.productmart.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.productmart.model.Product
import com.example.productmart.network.NetworkModule
import com.example.productmart.repository.ProductRepositoryImpl
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

    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FFF5)),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is ProductDetailUiState.Loading -> {
                CircularProgressIndicator()
            }
            is ProductDetailUiState.Error -> {
                Text(
                    text = (uiState as ProductDetailUiState.Error).message,
                    color = MaterialTheme.colorScheme.error
                )
            }
            is ProductDetailUiState.Success -> {
                DetailLayout(product = (uiState as ProductDetailUiState.Success).product)
            }
        }
    }
}
@Composable
fun DetailLayout(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = product.thumbnail,
            contentDescription = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                //.clip(RoundedCornerShape(12.dp))
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = product.title,
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "₹${product.price}",
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = product.description,
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Brand: ${product.brand}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )

        Text(
            text = "Category: ${product.category}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray
        )

        Text(
            text = "Rating: ${product.rating} ⭐",
            style = MaterialTheme.typography.bodySmall,
            color = Color.DarkGray
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DetailScreen(productId = 1)
}
