package com.example.productmart.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

import coil.compose.rememberAsyncImagePainter
import com.example.productmart.model.Product
import com.example.productmart.repository.ProductRepositoryImpl
import com.example.productmart.viewmodel.ProductViewModel
import com.example.productmart.viewmodel.ProductViewModelFactory
import com.example.productmart.viewmodel.ProductUiState
import com.example.productmart.network.NetworkModule
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onProductClick: (Int) -> Unit
) {

    val viewModel: ProductViewModel = viewModel(
        factory = ProductViewModelFactory(
            ProductRepositoryImpl(NetworkModule.api)
        )
    )

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Product Mart") }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (uiState) {
                is ProductUiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is ProductUiState.Error -> {
                    val errorMessage = (uiState as ProductUiState.Error).message
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                is ProductUiState.Success -> {
                    val products = (uiState as ProductUiState.Success).products
                    ProductList(
                        products = products,
                        onProductClick = { product -> onProductClick(product.id) }
                    )
                }
            }
        }
    }
}


@Composable
fun ProductList(products: List<Product>, onProductClick: (Product) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            ProductItem(product = product, onProductClick = onProductClick)
        }
    }
}

@Composable
fun ProductItem(product: Product, onProductClick: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onProductClick(product) },
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = rememberAsyncImagePainter(product.thumbnail),
                contentDescription = product.title,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 12.dp),
                contentScale = ContentScale.Crop
            )
            Column {
                Text(text = product.title, style = MaterialTheme.typography.titleMedium)
                Text(
                    text = "₹${product.price}",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = product.brand,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                )
            }
        }
    }
}
