package com.example.assignment_ph33001.ScreenBottom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.assignment_ph33001.ui.theme.GelasioMedium

@Composable
fun DetailProducts(productId: String?, productImage: String?, productName: String?, productPrice: String?) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    var quantity by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.5f)
        ) {
            Box(
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxHeight()
            )
            Box(
                modifier = Modifier
                    .weight(0.8f)
                    .fillMaxHeight()
                    .background(Color.Gray)
            ) {
                productImage?.let {
                    Image(
                        painter = rememberAsyncImagePainter(it),
                        contentDescription = "Product Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(bottomStart = 35.dp))
                    )
                }
            }
        }

        // PHẦN THÔNG TIN SẢN PHẨM
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.5f)
                .padding(16.dp)
        ) {
            Text(
                text = "$productName",
                color = Color.Black,
                fontSize = 24.sp,
//                fontFamily = GelasioMedium,
                fontWeight = FontWeight.Medium,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "$ $productPrice",
                    color = Color.Black,
                    fontSize = 30.sp,
//                    fontFamily = GelasioMedium,
                )

                Row(
                    modifier = Modifier
//                        .border(2.dp, Color.Magenta, RoundedCornerShape(4.dp))
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { if (quantity > 1) quantity-- },
                        modifier = Modifier.size(24.dp),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                    ) {
                        Text("-",
                            fontSize = 19.sp,
                            color = Color.Black
                        )
                    }

                    Text(
                        text = String.format("%02d", quantity),
                        modifier = Modifier
                            .padding(horizontal = 12.dp)
                            .padding(2.dp),
                        fontSize = 18.sp
                    )

                    Button(
                        onClick = { quantity++ },
                        modifier = Modifier.size(24.dp),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                    ) {
                        Text("+",
                            fontSize = 19.sp,
                            color = Color.Black
                        )
                    }
                }
            }
        }
    }
}

