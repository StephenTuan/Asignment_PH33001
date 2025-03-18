package com.example.assignment_ph33001

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.assignment_ph33001.ui.theme.Assignment_PH33001Theme

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
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Ảnh Logo
        Row(
            modifier = Modifier.fillMaxWidth(),
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

        // TextField nhập email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        // TextField nhập password
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Button đăng nhập
        Button(
            onClick = { /* Xử lý đăng nhập */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text(text = "Login", fontSize = 18.sp)
        }
    }
}

