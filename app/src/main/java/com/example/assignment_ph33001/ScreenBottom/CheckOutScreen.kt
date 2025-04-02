package com.example.assignment_ph33001.ScreenBottom

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.shadow


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
                        modifier = Modifier.width(screenWith*0.9f).padding(bottom = 16.dp)
                            .background(Color.White)
                            .shadow(8.dp, shape = RoundedCornerShape(5.dp)).background(Color.White),
                        elevation = CardDefaults.elevatedCardElevation(4.dp),
                    ) {
                        Text(
                            text = "Nguyen Anh Tuan",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.fillMaxWidth()
                                .background(Color.White).padding(10.dp)
                        )
                        Text(
                            text = "So 46, ngach 143/45, duong Xuan Phuong, phuong Phuong Canh, Nam Tu Liem",
                            fontSize = 18.sp,
                            modifier = Modifier.fillMaxWidth().background(Color.White).padding(10.dp)
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().background(Color.White),
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
                        modifier = Modifier.width(screenWith*0.9f).padding(bottom = 16.dp)
                            .background(Color.White)
                            .shadow(8.dp, shape = RoundedCornerShape(5.dp)).background(Color.White),
                        elevation = CardDefaults.elevatedCardElevation(4.dp),
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth().background(Color.White)
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.payment),
                                contentDescription = "",
                                modifier = Modifier.size(35.dp).background(Color.White)
                            )
                            Text(
                                text = "**** **** **** 1972",
                                fontSize = 18.sp,
                                modifier = Modifier.background(Color.White).padding(20.dp)
                            )
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().background(Color.White),
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
                        modifier = Modifier.width(screenWith*0.9f).padding(bottom = 16.dp)
                            .background(Color.White)
                            .shadow(8.dp, shape = RoundedCornerShape(5.dp)).background(Color.White),
                        elevation = CardDefaults.elevatedCardElevation(4.dp),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.background(Color.White).fillMaxWidth()
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.delivery),
                                contentDescription = "",
                                modifier = Modifier.size(35.dp)
                                    .background(Color.White)
                            )
                            Text(
                                text = "Fast( 2-3 day )",
                                fontSize = 18.sp,
                                modifier = Modifier.background(Color.White).padding(20.dp)
                            )
                        }
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
            Box(
                modifier = Modifier
                    .width(screenWith * 0.92f)
                    .shadow(4.dp, shape = RoundedCornerShape(8.dp))
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier.width(screenWith * 0.92f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = "Order:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.desOB),
                            modifier = Modifier
                        )
                        Text(
                            text = "$${String.format("%.2f", orderTotal)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(bottom = 10.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = "Delivery:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.desOB),
                            modifier = Modifier

                        )
                        Text(
                            text = "$${String.format("%.2f", deliveryFee)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier

                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.padding(bottom = 5.dp).fillMaxWidth()
                    ) {
                        Text(
                            text = "Total:",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.desOB),
                            modifier = Modifier
                        )
                        Text(
                            text = "$${String.format("%.2f", totalAmount)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.Black,
                            modifier = Modifier
                        )
                    }
                }
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