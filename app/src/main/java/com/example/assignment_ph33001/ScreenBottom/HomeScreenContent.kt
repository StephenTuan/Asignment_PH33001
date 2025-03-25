package com.example.assignment_ph33001.ScreenBottom

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.assignment_ph33001.R
import com.example.assignment_ph33001.model.Category
import com.example.assignment_ph33001.repository.ProductRepository
import com.example.assignment_ph33001.ui.theme.GelasioMedium
import com.example.assignment_ph33001.ui.theme.NunitoSans

@Composable
fun HomeScreenContent(context: Context,navController: NavHostController) {
    val categories = remember { mutableStateOf(emptyList<Category>()) }
    val selectedCategory = remember { mutableStateOf<Category?>(null) }

    LaunchedEffect(Unit) {
        val loadedCategories = ProductRepository.loadCategories(context)
        categories.value = loadedCategories
        selectedCategory.value = loadedCategories.firstOrNull()
    }

    Column(modifier = Modifier.fillMaxSize()
        .background(Color.White)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 8.dp, top = 20.dp, end = 8.dp, bottom = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        )
        {
            Image(
                painter = painterResource(id = R.drawable.baseline_search_24),
                contentDescription = "Search Icon",
                modifier = Modifier
                    .size(30.dp)
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Apple",
                    color = colorResource(id = R.color.xamdeu),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = GelasioMedium,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "STORE",
                    color = colorResource(id = R.color.backgroundButtonOb),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = GelasioMedium,
                    fontWeight = FontWeight.Medium
                )
            }
            Image(
                painter = painterResource(id = R.drawable.outline_shopping_cart_24),
                contentDescription = "Search Icon",
                modifier = Modifier
                    .size(30.dp)
            )
        }

        Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
            if (categories.value.isEmpty()) {
                Text(text = "Không có danh mục nào", modifier = Modifier.padding(16.dp))
            } else {
                CategoryList(categories.value, selectedCategory)
                Spacer(modifier = Modifier.height(10.dp))
                ProductList(selectedCategory.value?.products ?: emptyList(), navController)
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
                    painter = painterResource(
                        id = getCategoryIconResId(category.icon)
                    ),
                    contentDescription = category.name,
                    modifier = Modifier.size(48.dp),
                    tint = if (isSelected) Color.Black else Color.LightGray
                )
                Text(
                    text = category.name,
                    modifier =  Modifier.padding(top = 5.dp),
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
fun ProductList(products: List<com.example.assignment_ph33001.model.Product>, navController: NavHostController) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(products) { product ->
            ProductItem(product, navController)
        }
    }
}

@Composable
fun ProductItem(product: com.example.assignment_ph33001.model.Product,
                navController: NavHostController,) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                val encodedImage = java.net.URLEncoder.encode(product.image, "UTF-8")
                navController.navigate("product_detail/${product.id}/${product.name}/${encodedImage}/${product.price}")
            },
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(top = 5.dp, end = 5.dp)) {
            // Hình ảnh full size
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Icon giỏ hàng ở góc phải dưới
            Image(
                painter = painterResource(id = R.drawable.shoppingbag),
                contentDescription = "Cart Icon",

                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(5.dp))
                    .size(24.dp)
                    .background(Color.LightGray) // Tạo nền tròn cho icon
            )
        }

        // Tên và giá bên dưới hình ảnh
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = product.name,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = NunitoSans,
                fontWeight = FontWeight.Medium,
                color = colorResource(id = R.color.title1))
            Text(text = "$ ${product.price}",
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = NunitoSans,
                fontWeight = FontWeight.Bold,
                color = Color.Black)
        }
    }
}
