package com.example.assignment_ph33001.ScreenBottom

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.assignment_ph33001.R

@Composable
fun ProfileScreenContent(navController: NavController) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBar(isHomeScreen = false, isFavoriteScreen = false, navController = null)
        Row(
            modifier = Modifier.padding(top = 10.dp, bottom = 15.dp).width(screenWidth*0.9f).height(screenHeight*0.1f)
//                .background(Color.Yellow),
            ,verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(painter = painterResource(id = R.drawable.avataprofile), contentDescription = "Avata",
                modifier = Modifier.size(75.dp).clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(start = 12.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Anh Tuan", fontWeight = FontWeight.Bold, fontSize = 22.sp
                )
                Text(text = "nguyenanhtuan@gmail.com", fontSize = 16.sp, color = colorResource(id = R.color.desOB)
                )
            }
        }

        Card(
            modifier = Modifier.padding(top = 15.dp).width(screenWidth*0.9f).height(screenHeight*0.1f)
                .shadow(8.dp, shape = RoundedCornerShape(5.dp)).background(Color.White)
                .padding(start = 12.dp, end = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.background(Color.White),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "My orders", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "Already have 10 orders", fontSize = 14.sp, color = colorResource(id = R.color.desOB))
                }
                Image(painter = painterResource(id = R.drawable.next), contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Card(
            modifier = Modifier.padding(top = 15.dp).width(screenWidth*0.9f).height(screenHeight*0.1f)
                .shadow(8.dp, shape = RoundedCornerShape(5.dp)).background(Color.White)
                .padding(start = 12.dp, end = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.background(Color.White),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Shipping Addresses", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "03 Addresses", fontSize = 14.sp, color = colorResource(id = R.color.desOB))
                }
                Image(painter = painterResource(id = R.drawable.next), contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Card(
            modifier = Modifier.padding(top = 15.dp).width(screenWidth*0.9f).height(screenHeight*0.1f)
                .shadow(8.dp, shape = RoundedCornerShape(5.dp)).background(Color.White)
                .padding(start = 12.dp, end = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.background(Color.White),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Payment Method", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "You have 2 cards", fontSize = 14.sp, color = colorResource(id = R.color.desOB))
                }
                Image(painter = painterResource(id = R.drawable.next), contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Card(
            modifier = Modifier.padding(top = 15.dp).width(screenWidth*0.9f).height(screenHeight*0.1f)
                .shadow(8.dp, shape = RoundedCornerShape(5.dp)).background(Color.White)
                .padding(start = 12.dp, end = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.background(Color.White),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "My reviews", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "Reviews for 5 items", fontSize = 14.sp, color = colorResource(id = R.color.desOB))
                }
                Image(painter = painterResource(id = R.drawable.next), contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
        Card(
            modifier = Modifier.padding(top = 15.dp).width(screenWidth*0.9f).height(screenHeight*0.1f)
                .shadow(8.dp, shape = RoundedCornerShape(5.dp)).background(Color.White)
                .padding(start = 12.dp, end = 12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxSize()
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.background(Color.White),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Setting", fontWeight = FontWeight.Bold, fontSize = 20.sp)
                    Text(text = "Notification, Password, FAQ, Contact", fontSize = 14.sp, color = colorResource(id = R.color.desOB))
                }
                Image(painter = painterResource(id = R.drawable.next), contentDescription = "Next",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}