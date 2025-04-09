package com.example.productmart.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.productmart.model.Product

@Composable
fun ProductDetailContent(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(product.thumbnail),
            contentDescription = product.title,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = product.title, style = MaterialTheme.typography.headlineSmall)
        Text(text = "₹${product.price}", style = MaterialTheme.typography.bodyLarge)
        Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
        Text(text = "Brand: ${product.brand}", style = MaterialTheme.typography.bodySmall)
    }
}
@Preview(showBackground = true)
@Composable
fun ProductDetailContentPreview() {
    val dummyProduct = Product(
        id = 1,
        title = "Preview Product",
        description = "This is a sample product description.",
        price = 199.00,
        discountPercentage = 10.0,
        rating = 4.3,
        stock = 100,
        brand = "PreviewBrand",
        category = "electronics",
        thumbnail = "https://i.dummyjson.com/data/products/1/thumbnail.jpg",
        images = emptyList()
    )
    ProductDetailContent(product = dummyProduct)
}
