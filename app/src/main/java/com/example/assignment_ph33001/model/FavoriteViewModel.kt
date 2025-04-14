package com.example.assignment_ph33001.model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FavoriteViewModel : ViewModel() {
    private val _favoriteProducts = mutableStateListOf<Product>()
    val favoriteProducts: List<Product> = _favoriteProducts
    private val firestore = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        auth.currentUser?.email?.let { userEmail ->
            firestore.collection("favorites")
                .document(userEmail)
                .collection("userFavorites")
                .addSnapshotListener { snapshot, e ->
                    if (e != null) return@addSnapshotListener
                    _favoriteProducts.clear()
                    snapshot?.documents?.forEach { doc ->
                        val product = doc.toObject(Product::class.java)
                        product?.let { _favoriteProducts.add(it) }
                    }
                }
        }
    }

    fun addToFavorites(product: Product) {
        auth.currentUser?.email?.let { userEmail ->
            firestore.collection("favorites")
                .document(userEmail)
                .collection("userFavorites")
                .document(product.id.toString())
                .set(product)
        }
    }

    fun removeFromFavorites(product: Product) {
        auth.currentUser?.email?.let { userEmail ->
            firestore.collection("favorites")
                .document(userEmail)
                .collection("userFavorites")
                .document(product.id.toString())
                .delete()
        }
    }
}