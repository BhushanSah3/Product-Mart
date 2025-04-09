package com.example.productmart.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val thumbnail: String,
    val images: List<String>,
    val brand: String  // added brand here
)
