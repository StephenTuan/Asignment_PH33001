package com.example.assignment_ph33001.ScreenBottom


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.assignment_ph33001.model.CartItem
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import com.example.assignment_ph33001.model.CartViewModel


@Composable
fun CartScreen(cartViewModel: CartViewModel) {
    val items = remember { cartViewModel.cartItems }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {


        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
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
        // Total and Checkout button
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val items = cartViewModel.cartItems
            val total = items.sumOf {
                try {
                    it.product.price.toDouble() * it.quantity
                } catch (e: NumberFormatException) {
                    0.0
                }
            }
            Text(
                text = "Total: $${String.format("%.2f", total)}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = { /* Implement checkout */ },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF242424))
            ) {
                Text("Checkout", color = Color.White)
            }
        }
    }


@Composable
fun CartItemRow(
    cartItem: CartItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = cartItem.product.image,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = cartItem.product.name,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${cartItem.product.price}",
                color = Color.Gray
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (cartItem.quantity > 1) onQuantityChange(cartItem.quantity - 1)
            }) {
                Text("-", fontSize = 24.sp)  // Using Text instead of Icon
            }

            Text(
                text = cartItem.quantity.toString(),
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            IconButton(onClick = { onQuantityChange(cartItem.quantity + 1) }) {
                Text("+", fontSize = 24.sp)  // Using Text instead of Icon
            }

            IconButton(onClick = onRemove) {
                Image(
                    painter = painterResource(id = com.example.assignment_ph33001.R.drawable.trash),  // Fixed resource reference
                    contentDescription = "Remove",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}