package com.example.assignment_ph33001.model

data class Category(
    val id: String,
    val name: String,
    val products: List<Product>
)

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String
)
