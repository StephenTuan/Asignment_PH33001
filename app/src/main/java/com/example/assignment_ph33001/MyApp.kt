package com.example.assignment_ph33001

import android.app.Application
import com.example.assignment_ph33001.model.CartViewModel
import com.google.firebase.FirebaseApp

class MyApp : Application() {
    lateinit var cartViewModel: CartViewModel
    override fun onCreate() {
        super.onCreate()
        cartViewModel = CartViewModel()
        FirebaseApp.initializeApp(this)
    }
}
