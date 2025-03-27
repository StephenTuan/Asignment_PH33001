package com.example.assignment_ph33001.ScreenBottom

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreenContent() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        TopBar(isHomeScreen = false)

        Column(modifier = Modifier.fillMaxSize().background(Color.White)) {

        }
    }
}