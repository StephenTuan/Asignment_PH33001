package com.example.assignment_ph33001.ScreenBottom

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import coil.compose.rememberAsyncImagePainter
import com.example.assignment_ph33001.R
import com.example.assignment_ph33001.ui.theme.Assignment_PH33001Theme
import com.example.assignment_ph33001.ui.theme.NunitoSans
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class DetailProducts : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productId = intent.getIntExtra("PRODUCT_ID", 0)
        val productName = intent.getStringExtra("PRODUCT_NAME") ?: ""
        val productImage = intent.getStringExtra("PRODUCT_IMAGE") ?: ""
        val productPrice = intent.getStringExtra("PRODUCT_PRICE") ?: "0.0"
        val productRate = intent.getStringExtra("PRODUCT_RATE") ?: ""
        val productDescription = intent.getStringExtra("PRODUCT_DESCRIPTION") ?: ""
        val productReview = intent.getStringExtra("PRODUCT_REVIEW") ?: ""

        setContent {
            Assignment_PH33001Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    DetailContent(
                        productId = productId.toString(),
                        productName = productName,
                        productImage = productImage,
                        productPrice = productPrice.toString(),
                        productRate = productRate,
                        productDescription = productDescription,
                        productReview = productReview,
                        navController = null
                    )
                }
            }
        }
    }
}

@SuppressLint("ContextCastToActivity")
@Composable
fun DetailContent(
    productId: String,
    productImage: String,
    productName: String,
    productPrice: String,
    productRate: String,
    productDescription: String,
    productReview: String, navController: NavController? = null
) {
    val activity = LocalContext.current as? ComponentActivity
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    var quantity by remember { mutableStateOf(1) }

    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color.White

    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor)
    }

    Column(modifier = Modifier.fillMaxSize()
        .background(Color.White)
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
            ){
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(40.dp)
                        .shadow(8.dp, shape = CircleShape) // Đổ bóng nhẹ
                        .background(Color.White, shape = CircleShape), // Nền trắng, bo tròn
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(
                        onClick = {
                            navController?.popBackStack() ?: activity?.finish()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.Black, // Màu đen
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            Box(
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxHeight()
//                    .background(Color.Gray)
                    .shadow(5.dp, shape = MaterialTheme.shapes.medium)
                    .clip(RoundedCornerShape(bottomStart = 35.dp))
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

        //TTSP

        Column(
            modifier = Modifier.fillMaxWidth()
                .height(screenHeight * 0.4f)
                .background(Color.Blue)
        )
        {
            Text(
                text = productName,
                color = Color.Black,
                fontSize = 24.sp,
                fontFamily = NunitoSans,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$ $productPrice",
                    fontFamily = NunitoSans,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(
                        onClick = { if (quantity > 1) quantity-- },
                        modifier = Modifier.size(24.dp),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                    ) {
                        Text("-", fontSize = 19.sp, color = Color.Black)
                    }
                    Text(
                        text = quantity.toString(),
                        modifier = Modifier.padding(horizontal = 12.dp),
                        fontSize = 18.sp
                    )
                    Button(
                        onClick = { quantity++ },
                        modifier = Modifier.size(24.dp),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(0.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray)
                    ) {
                        Text("+", fontSize = 19.sp, color = Color.Black)
                    }
                }
            }
            Row(
                modifier = Modifier
                    .width(screenWidth*0.55f),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "⭐ $productRate",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
                Text(
                    text = "( $productReview )",
                    fontSize = 16.sp,
                    fontFamily = NunitoSans,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.desOB)
                )
            }
            Text(
                text = productDescription,
                fontSize = 16.sp,
                fontFamily = NunitoSans,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                color = colorResource(id = R.color.desOB)
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .height(screenHeight * 0.1f)
                .background(Color.Red)
        ) {



        }
    }
}
