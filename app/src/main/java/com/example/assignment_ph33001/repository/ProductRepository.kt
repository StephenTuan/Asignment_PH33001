package com.example.assignment_ph33001.repository

import android.content.Context
import com.example.assignment_ph33001.R
import com.example.assignment_ph33001.model.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ProductRepository {
    companion object {
        fun loadCategories(context: Context): List<Category> {
            return try {
                val inputStream = context.resources.openRawResource(R.raw.products)
                val jsonString = inputStream.bufferedReader().use { it.readText() }
                val listType = object : TypeToken<Map<String, List<Category>>>() {}.type
                val data: Map<String, List<Category>> = Gson().fromJson(jsonString, listType)
                data["categories"] ?: emptyList()
            } catch (e: Exception) {
                e.printStackTrace()
                emptyList()
            }
        }
    }
}
