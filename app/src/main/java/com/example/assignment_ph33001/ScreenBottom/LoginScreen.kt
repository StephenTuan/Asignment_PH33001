package com.example.assignment_ph33001.ScreenBottom

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.example.assignment_ph33001.R
import com.example.assignment_ph33001.model.User
import com.example.assignment_ph33001.ui.theme.Assignment_PH33001Theme
import com.example.assignment_ph33001.ui.theme.GelasioMedium
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import java.io.File

class LoginScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Assignment_PH33001Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LoginContent(
                        Navigate1 = {startActivity(Intent(this, SignUp::class.java))},
                        Navigate2 = {startActivity(Intent(this, HomeScreen::class.java))}
                    )
                }
            }
        }
    }
}

@Composable
fun LoginContent(Navigate1: () -> Unit, Navigate2: () -> Unit) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .systemBarsPadding()
                , horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.height(screenHeight * 0.295f)
                    .width(screenWidth * 0.8f)
                    .padding(top = screenHeight * 0.01f)
            ) {
                Row(
                    modifier = Modifier,
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
                        painter = painterResource(id = R.drawable.logologinsignup), // Thay bằng ảnh của bạn
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
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Text(
                        text = "Hello!",
                        fontSize = 30.sp,
                        fontFamily = GelasioMedium,
                        fontWeight = FontWeight.Medium,
                        color = colorResource(id = R.color.title1)
                    )
                    Text(
                        text = "WELCOME BACK",
                        fontSize = 30.sp,
                        fontFamily = GelasioMedium,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(bottom = screenHeight * 0.01f),
                        color = colorResource(id = R.color.title2)
                    )
                }
            }

        val heightcolum = screenHeight * 0.7f
            Column(

                horizontalAlignment = Alignment.Start,
                modifier = Modifier.height(heightcolum)
                    .padding(top = screenHeight * 0.005f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center

            ) {
                val heightCard = heightcolum * 0.8f
                Card(
                    modifier = Modifier
                        .width(screenWidth * 0.95f)
                        .height(heightCard)
                        .shadow(5.dp, shape = MaterialTheme.shapes.medium),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(0.5f)
                                .padding(16.dp)
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
                                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(), // ✅ Hiện dấu chấm khi `passwordVisible = false`
                                modifier = Modifier.fillMaxWidth(),
                                trailingIcon = {
                                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                        Icon(
                                            painter = painterResource(
                                                id = if (passwordVisible) R.drawable.baseline_visibility_off_24
                                                else R.drawable.baseline_visibility_24
                                            ),
                                            contentDescription = if (passwordVisible) "Hide password" else "Show password",
                                            tint = Color.Gray
                                        )
                                    }
                                }
                            )

                        }

                        Spacer(modifier = Modifier.height(heightCard*0.05f))

                        val heightColum2 = heightCard * 0.55f
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(heightColum2),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        bottom = heightColum2 * 0.15f,
                                        top = heightColum2 * 0.15f),
                                text = "Forgot Password",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Button(
                                onClick = {
                                    if (email.isBlank() || password.isBlank()) {
                                        Toast.makeText(context, "Không được để trống email và password", Toast.LENGTH_LONG).show()
                                    } else {
                                        val auth = FirebaseAuth.getInstance()
                                        auth.signInWithEmailAndPassword(email, password)
                                            .addOnCompleteListener { task ->
                                                if (task.isSuccessful) {
                                                    Toast.makeText(context, "Login Successful", Toast.LENGTH_LONG).show()
                                                    Navigate2()
                                                } else {
                                                    Toast.makeText(context, "Login Failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                                                }
                                            }
                                    }
                                },
                                modifier = Modifier
                                    .padding(
                                        start = 30.dp,
                                        end = 30.dp,
                                        bottom = heightColum2 * 0.15f
                                    )
                                    .height(heightColum2 * 0.25f)
                                    .shadow(8.dp, shape = MaterialTheme.shapes.medium)
                                    .fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.backgroundButtonOb)),
                                shape = MaterialTheme.shapes.small
                            ) {
                                Text(
                                    text = "LOGIN",
                                    fontSize = 17.sp,
                                    fontFamily = GelasioMedium,
                                    fontWeight = FontWeight.Medium,
                                    color = Color.White
                                )
                            }
                            Text(
                                modifier = Modifier
                                    .clickable { Navigate1() },
                                text = "SIGN UP",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

            }

        }

    }

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    Assignment_PH33001Theme {
        LoginContent(Navigate1 = {}, Navigate2 = {})
    }
}

