package com.example.assignment_ph33001.ScreenBottom

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.assignment_ph33001.R
import com.example.assignment_ph33001.model.Category
import com.example.assignment_ph33001.repository.ProductRepository
import com.google.accompanist.systemuicontroller.rememberSystemUiController


class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent(this)
        }
    }
}

@Composable
fun AppContent(context: Context) {
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color.White

    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor)
    }

    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController, context)
        }

    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("home", "Home", R.drawable.home),
        BottomNavItem("search", "Search", R.drawable.bookmark),
        BottomNavItem("profile", "Profile", R.drawable.notification),
        BottomNavItem("settings", "Settings", R.drawable.profile)
    )

    NavigationBar(containerColor = Color.White) {
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = item.label,
                        modifier = Modifier.size(if (currentRoute == item.route) 28.dp else 24.dp),
                        tint = if (currentRoute == item.route) Color.Black else Color.LightGray

                    )
                },
//                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                colors = NavigationBarItemDefaults.colors
                    (
                    indicatorColor = Color.Transparent
                     )
            )
        }
    }
}

// Data class để lưu thông tin của từng tab
data class BottomNavItem(
    val route: String,
    val label: String,
    @DrawableRes val iconRes: Int)

// Hàm lấy route hiện tại
@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

@Composable
fun NavigationGraph(navController: NavHostController, context: Context) {
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreenContent(context) }
        composable("search") { SearchScreenContent() }
        composable("profile") { ProfileScreenContent() }
        composable("settings") { SettingsScreenContent() }
    }
}

@Composable
fun CenteredText(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text, fontSize = 24.sp)
    }
}



