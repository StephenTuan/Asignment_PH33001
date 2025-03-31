package com.example.assignment_ph33001.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> = _cartItems

    fun addToCart(product: Product) {
        val existingItem = _cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            updateQuantity(product.id, existingItem.quantity + 1)
        } else {
            _cartItems.add(CartItem(product, 1))
        }
    }

    fun removeFromCart(productId: Int) {
        _cartItems.removeAll { it.product.id == productId }
    }

    fun updateQuantity(productId: Int, newQuantity: Int) {
        val index = _cartItems.indexOfFirst { it.product.id == productId }
        if (index != -1) {
            _cartItems[index] = _cartItems[index].copy(quantity = newQuantity)
        }
    }
}