package com.example.assignment_ph33001.ScreenBottom

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.assignment_ph33001.R
import com.example.assignment_ph33001.model.Category
import com.example.assignment_ph33001.model.FavoriteViewModel
import com.example.assignment_ph33001.model.Product
import com.example.assignment_ph33001.repository.ProductRepository
import com.example.assignment_ph33001.ui.theme.GelasioMedium
import com.example.assignment_ph33001.ui.theme.NunitoSans

@Composable
fun HomeScreenContent(navController: NavHostController,favoriteViewModel: FavoriteViewModel) {

    val categories = remember { mutableStateOf(emptyList<Category>()) }
    val selectedCategory = remember { mutableStateOf<Category?>(null) }

    LaunchedEffect(Unit) {
        ProductRepository.loadCategories { loadedCategories ->
            categories.value = loadedCategories
            selectedCategory.value = loadedCategories.firstOrNull()
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White)
    ) {
        TopBar(isHomeScreen = true, isFavoriteScreen = false)

        Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
            if (categories.value.isEmpty()) {
                Text(text = "Loading",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center)
            } else {
                CategoryList(categories.value, selectedCategory)
                Spacer(modifier = Modifier.height(10.dp))
                ProductList(selectedCategory.value?.products ?: emptyList(), navController,
                   favoriteViewModel)
            }
        }
    }
}

@Composable
fun TopBar(isHomeScreen: Boolean, isFavoriteScreen: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(start = 8.dp, top = 20.dp, end = 8.dp, bottom = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_search_24),
            contentDescription = "Search Icon",
            modifier = Modifier.size(30.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            when {
                isHomeScreen -> {
                    Image(
                        painter = painterResource(id = R.drawable.apple),
                        contentDescription = "",
                        modifier = Modifier.size(24.dp)
                    )
//                    Text(
//                        text = " Apple",
//                        color = colorResource(id = R.color.title1),
//                        fontSize = 24.sp,
//                        textAlign = TextAlign.Center,
//                        fontFamily = GelasioMedium,
//                        fontWeight = FontWeight.Medium
//                    )
                    Text(
                        text = " STORE",
                        color = colorResource(id = R.color.backgroundButtonOb),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = GelasioMedium,
                        fontWeight = FontWeight.Medium
                    )
                }

                isFavoriteScreen -> {
                    Text(
                        text = "Favorite",
                        color = colorResource(id = R.color.backgroundButtonOb),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = GelasioMedium,
                        fontWeight = FontWeight.Medium
                    )
                }

                else -> {
                    Text(
                        text = "Profile",
                        color = colorResource(id = R.color.backgroundButtonOb),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = GelasioMedium,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
        when {
            isHomeScreen -> {
                Image(
                    painter = painterResource(id = R.drawable.outline_shopping_cart_24),
                    contentDescription = "Cart Icon",
                    modifier = Modifier.size(30.dp)
                )
            }

            isFavoriteScreen -> {
                Image(
                    painter = painterResource(id = R.drawable.outline_shopping_cart_24),
                    contentDescription = "Cart Icon",
                    modifier = Modifier.size(30.dp)
                )
            }

            else -> {
                Image(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Cart Icon",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(Color(0xFF808080))
                )
            }
        }
    }
}

@Composable
fun CategoryList(categories: List<Category>, selectedCategory: MutableState<Category?>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState())
            .padding(top = 8.dp, start = 8.dp, end = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        categories.forEach { category ->
            val isSelected = category == selectedCategory.value
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { selectedCategory.value = category }
                    .padding(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = getCategoryIconResId(category.icon)),
                    contentDescription = category.name,
                    modifier = Modifier.size(48.dp),
                    tint = if (isSelected) Color.Black else Color.LightGray
                )
                Text(
                    text = category.name,
                    modifier = Modifier.padding(top = 5.dp),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = if (isSelected) Color.Black else Color.LightGray,
                )
            }
        }
    }
}

fun getCategoryIconResId(iconName: String): Int {
    return when (iconName) {
        "iphone" -> R.drawable.iphone
        "ipad" -> R.drawable.ipad
        "macmini" -> R.drawable.macmini
        "macstudio" -> R.drawable.macstudio
        "macdisplay" -> R.drawable.macdisplay
        "airpods" -> R.drawable.airpods
        else -> R.drawable.loading // Icon mặc định nếu không tìm thấy
    }
}

@Composable
fun ProductList(
    products: List<Product>,
    navController: NavHostController,
    favoriteViewModel: FavoriteViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductItem(
                product, navController, favoriteViewModel)
        }
    }
}

@Composable
fun ProductItem(product: Product, navController: NavHostController, favoriteViewModel: FavoriteViewModel) {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val intent = Intent(context, DetailProducts::class.java).apply {
                    putExtra("PRODUCT_ID", product.id)
                    putExtra("PRODUCT_NAME", product.name)
                    putExtra("PRODUCT_IMAGE", product.image)
                    putExtra("PRODUCT_PRICE", product.price.toString())
                    putExtra("PRODUCT_RATE", product.rate.toString())
                    putExtra("PRODUCT_DESCRIPTION", product.description)
                    putExtra("PRODUCT_REVIEW", product.review)
                }
                context.startActivity(intent)
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(top = 5.dp, end = 5.dp)) {
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier.fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                onLoading = { /* Optional loading state */ },
                onError = { /* Optional error state */ }
            )

            Image(
                painter = painterResource(id = R.drawable.shoppingbag),
                contentDescription = "Cart Icon",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(5.dp))
                    .size(24.dp)
                    .background(Color.LightGray)
                    .clickable {
                        try {
                            favoriteViewModel.addToFavorites(product)
                            Toast.makeText(context, "Đã thêm vào yêu thích", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(context, "Thêm vào yêu thích thất bại", Toast.LENGTH_SHORT).show()
                        }
                    }
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = product.name,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = NunitoSans,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.title1)
            )
            Text(
                text = "$ ${product.price}",
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = NunitoSans,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
