package com.example.assignment_ph33001.ScreenBottom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment_ph33001.R
import androidx.compose.material3.CardDefaults
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.lazy.LazyColumn


class CheckOutScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                CheckOutScreenContent()
        }
    }
}
@Composable
fun CheckOutScreenContent() {

    val orderTotal: Float = 100.0f // Example value, replace with actual calculation
    val deliveryFee: Float = 5.0f
    val totalAmount = orderTotal + deliveryFee
    val screenWith = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.Black,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Check Out",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 140.dp) // Add padding for bottom elements
                    .weight(1f)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Shipping Address",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        IconButton(onClick = { /* Handle edit action */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = "Edit",
                                tint = Color.Black
                            )
                        }
                    }
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                            .background(Color.White),
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.elevatedCardElevation(4.dp),
                    ) {
                        Text(
                            text = "Shipping Address Details",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Payment", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                        IconButton(onClick = { /* Handle edit action */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = "Edit",
                                tint = Color.Black
                            )
                        }
                    }
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                            .background(Color.White),
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.elevatedCardElevation(4.dp),
                    ) {
                        Text(
                            text = "Payment Details",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Delivery Method",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        IconButton(onClick = { /* Handle edit action */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_edit),
                                contentDescription = "Edit",
                                tint = Color.Black
                            )
                        }
                    }
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
                            .background(Color.White),
                        shape = RoundedCornerShape(8.dp),
                        elevation = CardDefaults.elevatedCardElevation(4.dp),
                    ) {
                        Text(
                            text = "Delivery Method Details",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter).background(Color.White),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Column(
                modifier = Modifier.width(screenWith * 0.92f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Order: $${String.format("%.2f", orderTotal)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = colorResource(id = R.color.desOB),
                    modifier = Modifier
                )
                Text(
                    text = "Delivery: $${String.format("%.2f", deliveryFee)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                )
                Text(
                    text = "Total: $${String.format("%.2f", totalAmount)}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                )
            }

            Box(
                modifier = Modifier.padding(top = 16.dp).width(screenWith * 0.92f)
                    .background(Color.White)
            ) {
                Button(
                    onClick = { /* Implement checkout */ },
                    modifier = Modifier.width(screenWith * 0.92f).height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF242424)),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Checkout", color = Color.White, fontSize = 16.sp)
                }
            }
        }
    }
}