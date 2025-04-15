package com.example.assignment_ph33001.ScreenBottom

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.assignment_ph33001.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NotificationScreenContent(navController: NavController) {
    val userEmail = FirebaseAuth.getInstance().currentUser?.email
    val invoices = remember { mutableStateListOf<Map<String, Any>>() }
    val context = LocalContext.current

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
        items(invoices.size) { index ->
            val invoice = invoices[index]
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable {
                        val intent = Intent(context, InvoiceDetailScreen::class.java)
                        intent.putExtra("INVOICE_ID", invoice["invoiceId"]?.toString())
                        context.startActivity(intent)
                    },
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Order #${invoice["invoiceId"]?.toString()?.takeLast(6) ?: "N/A"}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.Black
                        )
                        Text(
                            text = invoice["date"]?.let { timestamp ->
                                val dateFormat = java.text.SimpleDateFormat("dd/MM/yy HH:mm", java.util.Locale.getDefault())
                                dateFormat.format(java.util.Date((timestamp as Long)))
                            } ?: "N/A",
                            color = Color.Gray,
                            fontSize = 14.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "${(invoice["items"] as? List<*>)?.size ?: 0} items",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                            Text(
                                text = "Total Amount: $${invoice["totalAmount"]?.toString() ?: "0.00"}",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = Color(0xFF1E88E5)
                            )
                        }
                        Icon(
                            painter = painterResource(id = R.drawable.next),
                            contentDescription = "View Details",
                            tint = Color.Gray,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }
    }
}