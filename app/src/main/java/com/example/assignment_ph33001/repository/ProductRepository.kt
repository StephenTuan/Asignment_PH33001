package com.example.assignment_ph33001.repository

import android.util.Log
import com.example.assignment_ph33001.model.Category
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object ProductRepository {
    private val database = FirebaseDatabase.
    getInstance("https://assignment-ph33001-default-rtdb.asia-southeast1.firebasedatabase.app")

        fun loadCategories(callback: (List<Category>) -> Unit) {
            val databaseRef = database.reference.child("categories")

            databaseRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val categories = mutableListOf<Category>()
                    snapshot.children.forEach { childSnapshot ->
                        val category = childSnapshot.getValue(Category::class.java)
                        Log.d("Firebase", "Category data: $category")
                        category?.let {
                            categories.add(it)
                        }
                    }
                    Log.d("Firebase", "Total categories loaded: ${categories.size}")
                    callback(categories)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("Firebase", "Error loading categories: ${error.message}")
                    callback(emptyList())
                }
            }
            )
        }
    }