package com.example.assignment_ph33001.ScreenBottom

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.assignment_ph33001.R
import com.example.assignment_ph33001.model.CartViewModel
import com.example.assignment_ph33001.model.FavoriteViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import java.net.URLDecoder


class HomeScreen() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

@Composable
fun AppContent() {
    val systemUiController = rememberSystemUiController()
    val statusBarColor = Color.White
    val favoriteViewModel: FavoriteViewModel = viewModel()  // Fixed: Properly initialize ViewModel

    SideEffect {
        systemUiController.setStatusBarColor(color = statusBarColor)
    }

    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            val cartViewModel: CartViewModel = viewModel()
            NavigationGraph(
                navController = navController,
                favoriteViewModel = favoriteViewModel, cartViewModel = cartViewModel)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("home", "Home", R.drawable.home),
        BottomNavItem("favorite", "Favorite", R.drawable.bookmark),
        BottomNavItem("notification", "Notification", R.drawable.notification),
        BottomNavItem("profile", "Profile", R.drawable.profile)

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
fun NavigationGraph(
    navController: NavHostController, favoriteViewModel: FavoriteViewModel
    , cartViewModel: CartViewModel,  // Fix: Change Nothing? to CartViewModel
    modifier: Modifier = Modifier
) {

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreenContent(navController = navController,favoriteViewModel = favoriteViewModel) }
        composable("favorite") { FavoriteScreenContent(favoriteViewModel = favoriteViewModel,navController = navController) }
        composable("notification") { NotificationScreenContent() }
        composable("profile") { ProfileScreenContent(navController = navController) }
        composable("cart_screen") {
            CartScreen(cartViewModel = cartViewModel)
        }

        composable(
            "product_detail/" +
                    "{productId}/" +
                    "{productName}/" +
                    "{productImage}/" +
                    "{productPrice}/" +
                    "{productRate}/" +
                    "{productDescription}/" +
                    "{productReview}",
            arguments = listOf(
                navArgument("productId") { type = NavType.StringType },
                navArgument("productName") { type = NavType.StringType },
                navArgument("productImage") { type = NavType.StringType },
                navArgument("productPrice") { type = NavType.StringType },
                navArgument("productRate") { type = NavType.StringType },
                navArgument("productDescription") { type = NavType.StringType },
                navArgument("productReview") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId")?.toIntOrNull() ?: 0
            val productName = backStackEntry.arguments?.getString("productName") ?: ""
            val productImage = URLDecoder.decode(
                backStackEntry.arguments?.getString("productImage") ?: "", "UTF-8"
            )
            val productPrice = backStackEntry.arguments?.getString("productPrice")?.toFloatOrNull() ?: 0f
            val productRate = backStackEntry.arguments?.getString("productRate") ?.toFloatOrNull() ?: 0f
            val productDescription = backStackEntry.arguments?.getString("productDescription") ?: ""
            val productReview = backStackEntry.arguments?.getString("productReview") ?: ""

        }
    }
}



