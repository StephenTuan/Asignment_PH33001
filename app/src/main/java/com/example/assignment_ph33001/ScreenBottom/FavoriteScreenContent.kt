package com.example.assignment_ph33001.ScreenBottom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.assignment_ph33001.R
import com.example.assignment_ph33001.model.FavoriteViewModel
import com.example.assignment_ph33001.model.Product

@Composable
fun FavoriteScreenContent(favoriteViewModel: FavoriteViewModel) {

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(isHomeScreen = false, isFavoriteScreen = true)
        LazyColumn(
            modifier = Modifier.background(Color.White)
        ) {
            items(favoriteViewModel.favoriteProducts) { product ->
                FavoriteProductItem(product)
            }
        }
    }
}
@Composable
fun FavoriteProductItem(product: Product) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Card(
        modifier = Modifier
            .padding(start = 10.dp, end = 10.dp, top = 12.dp)
            .fillMaxWidth()
            .background(Color.White, shape = RoundedCornerShape(0.dp))
            .drawBehind {
                drawLine(
                    color = Color.LightGray,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx()
                )
            }
            .padding(bottom = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight*0.15f)
                .background(Color.White, shape = RoundedCornerShape(0.dp))
        ) {
            Row{
                AsyncImage(
                    model = product.image,
                    contentDescription = product.name,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(RoundedCornerShape(0.dp)),  // Add this to ensure sharp corners
//                        .border(
//                            width = 1.dp,
//                            color = Color.LightGray,
//                            shape = RoundedCornerShape(0.dp)  // Add this to ensure sharp corners
//                        ),
                    contentScale = ContentScale.Crop,
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column(
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = product.name,
                        color = Color.Gray,
                        fontSize = 18.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "$ ${product.price}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Column(
                modifier = Modifier.padding(8.dp).height(screenHeight*0.2f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.trash),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),
//                    colorFilter = ColorFilter.tint(Color.Red)
                )
                Image(
                    painter = painterResource(id = R.drawable.cartfavorite),
                    contentDescription = "",
                    modifier = Modifier.size(24.dp),

                )
            }
        }
    }
}