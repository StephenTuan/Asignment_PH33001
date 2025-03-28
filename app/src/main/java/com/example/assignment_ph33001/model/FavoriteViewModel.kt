package com.example.assignment_ph33001.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class FavoriteViewModel : ViewModel() {
    private val _favoriteProducts = mutableStateListOf<Product>()
    val favoriteProducts: List<Product> = _favoriteProducts

    fun addToFavorites(product: Product) {
        if (!_favoriteProducts.contains(product)) {
            _favoriteProducts.add(product)
        }
    }

    fun removeFromFavorites(product: Product) {
        _favoriteProducts.remove(product)
    }
}