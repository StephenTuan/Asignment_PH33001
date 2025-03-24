package com.example.assignment_ph33001.model

data class Category(
    val name: String,
    val products: List<Product>,
    val icon: String
)

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val image: String // Đường dẫn ảnh
)
