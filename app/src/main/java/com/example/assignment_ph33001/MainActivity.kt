package com.example.assignment_ph33001

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment_ph33001.ui.theme.Assignment_PH33001Theme
import com.example.assignment_ph33001.ui.theme.GelasioMedium
import com.example.assignment_ph33001.ui.theme.NunitoSans

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Assignment_PH33001Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    onBoarding(modifier = Modifier.padding(innerPadding),
                        onNavigate = {
                            startActivity(Intent(this, LoginScreen::class.java))
                        }

                    )
                }
            }
        }
    }
}

@Composable
fun onBoarding(modifier: Modifier = Modifier, onNavigate: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {

        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp, top = 80.dp),
//                .background(Color.Green),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "APPLE STORE",
                    fontSize = 34.sp,
                    fontFamily = GelasioMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
            Column(
                modifier = Modifier
                    .padding(bottom = 100.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "WORLD OF TECHNOLOGY",
                    fontSize = 26.sp,
                    fontFamily = GelasioMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom = 26.dp, top = 10.dp),
                    color = Color.White
                )
            }
            Box(modifier = Modifier.size(width = 200.dp, height = 180.dp)) {

            }
            Text(
                text = "The best way to buy the products you like ",
                fontSize = 17.sp,
                fontFamily = NunitoSans,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 40.dp, end = 40.dp),
                color = Color.White
            )
//            val screenHeight = LocalConfiguration.current.screenHeightDp.dp
//            val bottomPadding = screenHeight * 0.25f // 25% chiều cao màn hình

            Button(
                onClick = onNavigate,
                modifier = Modifier.padding(top = 30.dp)
                    .size(width = 160.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.backgroundButtonOb)),
                shape = MaterialTheme.shapes.small
            )
            {
                Text(
                    text = "Get Started",
                    fontSize = 17.sp,
                    fontFamily = GelasioMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OnBoardingPreview() {
    Assignment_PH33001Theme {
        onBoarding(onNavigate = {})
    }
}
