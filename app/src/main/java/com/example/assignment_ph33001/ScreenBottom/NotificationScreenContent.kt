package com.example.assignment_ph33001.ScreenBottom

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NotificationScreenContent() {
    val userEmail = FirebaseAuth.getInstance().currentUser?.email
    val invoices = remember { mutableStateListOf<Map<String, Any>>() }

    LaunchedEffect(userEmail) {
        userEmail?.let {
            val firestore = FirebaseFirestore.getInstance()
            firestore.collection("invoices").document(it).collection("userInvoices")
                .get()
                .addOnSuccessListener { result ->
                    invoices.clear()
                    invoices.addAll(result.documents.map { it.data ?: emptyMap() })
                }
                .addOnFailureListener { e ->
                    // Handle error
                }
        }
    }

    LazyColumn {
        items(invoices) { invoice ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
                    // Navigate to Invoice Detail Screen
                    navController.navigate("invoice_detail/${invoice["invoiceId"]}")
                }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Invoice ID: ${invoice["invoiceId"]}", fontWeight = FontWeight.Bold)
                    Text(text = "Date: ${invoice["date"]}")
                }
            }
        }
    }
}