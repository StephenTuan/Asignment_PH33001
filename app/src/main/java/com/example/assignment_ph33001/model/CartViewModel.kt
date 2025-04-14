package com.example.assignment_ph33001.model

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.State

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> = _cartItems

    private val _totalAmount = mutableStateOf(0.0)
    val totalAmount: State<Double> = _totalAmount

    fun clearCart() {
        _cartItems.clear()
        _totalAmount.value = 0.0
    }

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