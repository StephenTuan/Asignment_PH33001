package com.example.assignment_ph33001.model

import androidx.annotation.Keep

@Keep
data class Category(
    val id: String = "",
    val name: String = "",
    val icon: String = "",
    val products: List<Product> = emptyList()  // Changed back to List
)

data class Product(
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val price: Double = 0.0,
    val rate: Double = 0.0,
    val description: String = "",
    val review: String = ""
)

