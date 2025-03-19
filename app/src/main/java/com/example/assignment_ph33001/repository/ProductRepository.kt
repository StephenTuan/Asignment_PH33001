package com.example.assignment_ph33001.repository

import android.content.Context
import com.example.assignment_ph33001.model.Category
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object ProductRepository {
    fun loadCategories(context: Context): List<Category> {
        val jsonString = context.resources.openRawResource(com.example.assignment_ph33001.R.raw.products)
            .bufferedReader()
            .use { it.readText() }

        val listType = object : TypeToken<List<Category>>() {}.type
        return Gson().fromJson(jsonString, listType)
    }
}
