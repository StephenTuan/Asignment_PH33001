package com.example.assignment_ph33001.ScreenBottom

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.assignment_ph33001.MyApp
import com.example.assignment_ph33001.R
import com.example.assignment_ph33001.model.CartItem
import com.example.assignment_ph33001.model.CartViewModel



class CartScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val cartViewModel = (application as MyApp).cartViewModel

        setContent {
            CartScreenContent(cartViewModel)
        }
    }
}

@Composable
fun CartScreenContent(cartViewModel: CartViewModel,navController: NavController? = null) {
    val items = cartViewModel.cartItems
    val context = LocalContext.current
    var promoCode by remember { mutableStateOf("") }
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
                IconButton(
                    onClick = {
                        val intent = Intent(context, HomeScreen::class.java)
                        context.startActivity(intent)
                        (context as? Activity)?.finish()
                    }
                ) {
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
                        text = "My Cart",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .padding(bottom = 140.dp) // Add padding for bottom elements
            ) {
                items(
                    count = items.size,
                    itemContent = { index ->
                        val cartItem = items[index]
                        CartItemRow(
                            cartItem = cartItem,
                            onQuantityChange = { newQuantity ->
                                cartViewModel.updateQuantity(cartItem.product.id, newQuantity)
                            },
                            onRemove = {
                                cartViewModel.removeFromCart(cartItem.product.id)
                            }
                        )
                        Divider()
                    }

                )
            }
        }


        Divider(modifier = Modifier.padding(bottom = 16.dp))

            val total = items.sumOf {
                try {
                    it.product.price.toDouble() * it.quantity
                } catch (e: NumberFormatException) {
                    0.0
                }
            }

            Divider(modifier = Modifier.padding(bottom = 16.dp))

            Column(
                modifier = Modifier
                .align(Alignment.BottomCenter).background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.padding(bottom = 16.dp)
                        .width(screenWith*0.92f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = promoCode,
                        onValueChange = { promoCode = it },
                        placeholder = { Text("Enter your promo code") },
                        modifier = Modifier.weight(1f),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedBorderColor = Color.LightGray,
                            focusedBorderColor = Color.LightGray
                        )
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    IconButton(
                        onClick = { /* Handle promo code */ },
                        modifier = Modifier
                            .background(Color.Black, RoundedCornerShape(4.dp))
                            .size(54.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = "Apply",
                            tint = Color.White
                        )
                    }
                }
                Row(
                    modifier = Modifier.width(screenWith*0.92f),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Total:",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = colorResource(id = R.color.desOB),
                        modifier = Modifier
                    )
                    Text(
                        text = "$${String.format("%.2f", total)}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier
                    )
                }
                Box(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .width(screenWith*0.92f)
                        .background(Color.White)
                ) {
                    Button(
                        onClick = { /* Implement checkout */ },
                        modifier = Modifier
                            .width(screenWith*0.92f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF242424)),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Checkout", color = Color.White, fontSize = 16.sp)
                    }
                }
            }
        }
    }

@Composable
fun CartItemRow(
    cartItem: CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.weight(1f).width(screenWidth*0.9f)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                AsyncImage(
                    model = cartItem.product.image,
                    contentDescription = null,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text(
                        text = cartItem.product.name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$${cartItem.product.price}",
                        color = Color.Gray
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(onClick = {
                            if (cartItem.quantity > 1) onQuantityChange(cartItem.quantity - 1)
                        }) {
                            Text("-", fontSize = 24.sp)
                        }

                        Text(
                            text = cartItem.quantity.toString(),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(5.dp)
                        )

                        IconButton(onClick = { onQuantityChange(cartItem.quantity + 1) }) {
                            Text("+", fontSize = 24.sp)
                        }


                    }
                }
            }
            IconButton(onClick = onRemove) {
                Image(
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = "Remove",
                    modifier = Modifier.size(24.dp),
                )
            }
        }
    }
}