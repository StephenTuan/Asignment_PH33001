package com.example.assignment_ph33001.ScreenBottom

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun InvoiceDetailScreen(invoiceId: String) {
    val userEmail = FirebaseAuth.getInstance().currentUser?.email
    val invoiceDetails = remember { mutableStateOf<Map<String, Any>?>(null) }

    LaunchedEffect(invoiceId) {
        userEmail?.let {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("invoices").document(it).collection("userInvoices").document(invoiceId)
                .get()
                .addOnSuccessListener { document ->
                    invoiceDetails.value = document.data
                }
                .addOnFailureListener { e ->
                    // Handle error
                }
        }
    }

    invoiceDetails.value?.let { invoice ->
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text(text = "Invoice ID: ${invoice["invoiceId"]}", fontWeight = FontWeight.Bold)
            Text(text = "Date: ${invoice["date"]}")
            invoice["items"]?.let { items ->
                (items as List<Map<String, Any>>).forEach { item ->
                    Text(text = "Name: ${item["name"]}")
                    Text(text = "Price: ${item["price"]}")
                    Text(text = "Quantity: ${item["quantity"]}")
                }
            }
        }
    }
}