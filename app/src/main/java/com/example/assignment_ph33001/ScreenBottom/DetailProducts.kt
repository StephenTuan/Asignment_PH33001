package com.example.assignment_ph33001.ScreenBottom

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.draw.clip

@Composable
fun DetailProduct(productId: String?, productImage: String?, productName: String?, productPrice: String?) {

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Text(text = "Chi tiết sản phẩm: ID = $productId")

        productImage?.let {
            Image(
                painter = rememberAsyncImagePainter(it),
                contentDescription = "Product Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp))
            )
        }

        Text(text = "$productName")
        Text(text = "$productPrice")
    }

}

//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import coil.compose.AsyncImage
//import com.example.assignment_ph33001.LoginContent
//import com.example.assignment_ph33001.R
//import com.example.assignment_ph33001.model.Product
//import com.example.assignment_ph33001.ui.theme.Assignment_PH33001Theme
//import com.example.assignment_ph33001.ui.theme.NunitoSans
//
//@Composable
//fun DetailProduct(product: com.example.assignment_ph33001.model.Product){
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        shape = RoundedCornerShape(8.dp),
//        elevation = CardDefaults.cardElevation(4.dp),
//        colors = CardDefaults.cardColors(containerColor = Color.White)
//    ) {
//        Box(modifier = Modifier.fillMaxSize().padding(top = 5.dp, end = 5.dp)) {
//            // Hình ảnh full size
//            AsyncImage(
//                model = product.image,
//                contentDescription = product.name,
//                modifier = Modifier.fillMaxSize(),
//                contentScale = ContentScale.Crop
//            )
//
//            // Icon giỏ hàng ở góc phải dưới
//            Image(
//                painter = painterResource(id = R.drawable.shoppingbag),
//                contentDescription = "Cart Icon",
//
//                modifier = Modifier
//                    .align(Alignment.TopEnd)
//                    .clip(RoundedCornerShape(5.dp))
//                    .size(24.dp)
//                    .background(Color.LightGray) // Tạo nền tròn cho icon
//            )
//        }
//
//        // Tên và giá bên dưới hình ảnh
//        Column(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//            horizontalAlignment = Alignment.Start
//        ) {
//            Text(text = product.name,
//                fontSize = 16.sp,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//                fontFamily = NunitoSans,
//                fontWeight = FontWeight.Medium,
//                color = colorResource(id = R.color.title1)
//            )
//            Text(text = "$ ${product.price}",
//                fontSize = 16.sp,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis,
//                fontFamily = NunitoSans,
//                fontWeight = FontWeight.Bold,
//                color = Color.Black)
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun DetailPreview() {
//    Assignment_PH33001Theme {
//        DetailProduct()
//    }
//}
