package com.example.assignment_ph33001

import android.content.Intent
import android.os.Bundle
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
import com.example.assignment_ph33001.ui.theme.Assignment_PH33001Theme
import com.example.assignment_ph33001.ui.theme.GelasioMedium

class SignUp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            Assignment_PH33001Theme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    SignUpContent(
                        Navigate1 = { startActivity(Intent(this, LoginScreen::class.java)) }
                    )
                }
            }
        }
    }
}

@Composable
fun SignUpContent(Navigate1: () -> Unit) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordCf by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordCfVisible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White) // ✅ Đặt màu nền
            .systemBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Ảnh Logo
        Row(
            modifier = Modifier
                .padding(top = 60.dp, bottom = 30.dp)
                .fillMaxWidth(),
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
                text = "WELCOME",
                fontSize = 30.sp,
                fontFamily = GelasioMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(bottom = 30.dp),
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
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

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
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        val image = if (passwordVisible)
                            painterResource(id = R.drawable.baseline_visibility_24)
                        else
                            painterResource(id = R.drawable.baseline_visibility_off_24)

                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Image(
                                painter = image,
                                contentDescription = "Toggle password visibility"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                OutlinedTextField(
                    value = passwordCf,
                    onValueChange = { passwordCf = it },
                    label = { Text("Confirm Password") },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    visualTransformation = if (passwordCfVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        val image = if (passwordCfVisible)
                            painterResource(id = R.drawable.baseline_visibility_24)
                        else
                            painterResource(id = R.drawable.baseline_visibility_off_24)

                        IconButton(onClick = { passwordCfVisible = !passwordCfVisible }) {
                            Image(
                                painter = image,
                                contentDescription = "Toggle password visibility"
                            )
                        }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

            }

            Button(
                onClick = { Navigate1() },
                modifier = Modifier
                    .padding(start = 30.dp, end = 30.dp, top = 20.dp)
                    .height(45.dp)
                    .fillMaxWidth()
                    .shadow(8.dp, shape = MaterialTheme.shapes.medium),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.backgroundButtonOb)),
                shape = MaterialTheme.shapes.small
            ) {
                Text(
                    text = "SIGN UP",
                    fontSize = 17.sp,
                    fontFamily = GelasioMedium,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, bottom = 40.dp)
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Already have account?",
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.xamdeu),
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "SIGN IN",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    color = colorResource(id = R.color.backgroundButtonOb),
                    modifier = Modifier.clickable { Navigate1() }
                )
            }

        }
    }

}




