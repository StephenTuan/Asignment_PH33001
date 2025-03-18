package com.example.assignment_ph33001

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment_ph33001.ui.theme.Assignment_PH33001Theme
import com.example.assignment_ph33001.ui.theme.GelasioMedium

class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Assignment_PH33001Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LoginContent()
                }
            }
        }
    }
}

@Composable
fun LoginContent() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Ảnh Logo
            Row(
                modifier = Modifier.padding(top = 60.dp, bottom = 30.dp).fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.rectangle), // Thay bằng ảnh của bạn
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(bottom = 16.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ggroup), // Thay bằng ảnh của bạn
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(bottom = 16.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.rectangle), // Thay bằng ảnh của bạn
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(70.dp)
                        .padding(bottom = 16.dp)
                )
            }

            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Hello!",
                    fontSize = 30.sp,
                    fontFamily = GelasioMedium,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.title1)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "WELCOME BACK",
                    fontSize = 30.sp,
                    fontFamily = GelasioMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(bottom =50.dp, top = 10.dp),
                    color = colorResource(id = R.color.title2)
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 30.dp, top = 10.dp)
                    .shadow(5.dp, shape = MaterialTheme.shapes.medium),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(start = 16.dp, top = 25.dp, end = 5.dp)
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Email") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(

                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                }

                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 25.dp, bottom = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        modifier = Modifier.padding(bottom = 35.dp),
                        text = "Forgot Password",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium

                    )

                    Button(
                        onClick = {},
                        modifier = Modifier
                            .padding(start = 30.dp, end = 30.dp)
                            .height(45.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.backgroundButtonOb)),
                        shape = MaterialTheme.shapes.small
                    )
                    {
                        Text(
                            text = "Log in",
                            fontSize = 17.sp,
                            fontFamily = GelasioMedium,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }

                    Text(
                        modifier = Modifier.padding(top = 30.dp),
                        text = "SIGN UP",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

        }

    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewLoginScreen() {
    Assignment_PH33001Theme {
        LoginContent()
    }
}


