package com.example.assignment_ph33001

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.assignment_ph33001.model.Category
import com.example.assignment_ph33001.repository.ProductRepository
import java.io.InputStream


class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeContent(this)
        }
    }
}

@Composable
fun HomeContent(context: Context) {
    val navController = rememberNavController()

    println("HomeContent() được gọi lại")

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) } // Thanh điều hướng dưới
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationGraph(navController, context)
        }

    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        BottomNavItem("home", "Home", Icons.Default.Home),
        BottomNavItem("search", "Search", Icons.Default.Search),
        BottomNavItem("profile", "Profile", Icons.Default.Person),
        BottomNavItem("settings", "Settings", Icons.Default.Settings)
    )

    NavigationBar(containerColor = Color.White) {
        val currentRoute = currentRoute(navController)
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) }
            )
        }
    }
}

// Data class để lưu thông tin của từng tab
data class BottomNavItem(val route: String, val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)

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
fun HomeScreenContent(context: Context) {
    val categories = remember { mutableStateOf(emptyList<Category>()) }
    val selectedCategory = remember { mutableStateOf<Category?>(null) }

    try {
        val loadedCategories = ProductRepository.loadCategories(context)
        categories.value = loadedCategories
        selectedCategory.value = loadedCategories.firstOrNull()
    } catch (e: Exception) {
        e.printStackTrace() // Log lỗi để kiểm tra
    }

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        if (categories.value.isEmpty()) {
            Text(text = "Không có danh mục nào", modifier = Modifier.padding(16.dp))
        } else {
            CategoryList(categories.value, selectedCategory)
            Spacer(modifier = Modifier.height(10.dp))
            ProductList(selectedCategory.value?.products ?: emptyList())
        }
    }
    val jsonString = loadJSONFromRaw(context, R.raw.products)
    println("JSON từ file: $jsonString")
}

fun loadJSONFromRaw(context: Context, rawResId: Int): String? {
    return try {
        val inputStream: InputStream = context.resources.openRawResource(rawResId)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        String(buffer, Charsets.UTF_8)
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun CategoryList(categories: List<Category>, selectedCategory: MutableState<Category?>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(8.dp)
    ) {
        categories.forEach { category ->
            val isSelected = category == selectedCategory.value
            Text(
                text = category.name,
                fontSize = 16.sp,
                color = if (isSelected) Color.White else Color.Black,
                modifier = Modifier
                    .padding(8.dp)
                    .background(
                        if (isSelected) Color.Gray else Color.LightGray,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable { selectedCategory.value = category }
            )
        }
    }
}
@Composable
fun ProductList(products: List<com.example.assignment_ph33001.model.Product>) {
    Column(modifier = Modifier.padding(8.dp)) {
        products.forEach { product ->
            ProductItem(product)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
@Composable
fun ProductItem(product: com.example.assignment_ph33001.model.Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentDescription = product.name,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = product.name, fontSize = 18.sp)
                Text(text = "$${product.price}", fontSize = 16.sp, color = Color.Gray)
            }
        }
    }
}




@Composable
fun SearchScreenContent() {
    CenteredText("Search Screen")
}

@Composable
fun ProfileScreenContent() {
    CenteredText("Profile Screen")
}

@Composable
fun SettingsScreenContent() {
    CenteredText("Settings Screen")
}

@Composable
fun CenteredText(text: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = text, fontSize = 24.sp)
    }
}



