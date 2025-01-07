package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GreetingText()
        }
    }
}

@Composable
fun GreetingText() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // First column: Image and text
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val image = painterResource(R.drawable.hq720)

            Image(
                painter = image,
                contentDescription = "Sample image",
                modifier = Modifier
                    .fillMaxWidth(0.5f) // Adjust width for better visual balance
                    .aspectRatio(1f)
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "Netrang Rane",

                fontWeight = FontWeight.SemiBold,
                fontSize = 35.sp,
                color = Color.Black
            )
            Text(
                text = "Android dev",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color = Color.Black
            )
        }

        // Spacer for gap between the two columns
        Spacer(modifier = Modifier.height(32.dp))

        // Second column: Rows with icons and information
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(100.dp,0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val image = painterResource(R.drawable.hq720)
                Image(
                    modifier = Modifier
                        .size(70.dp)
                        .padding(10.dp),
                    painter = image,
                    contentDescription = "Phone icon"
                )
                Text(text = "8554545554")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val image = painterResource(R.drawable.hq720)
                Image(
                    modifier = Modifier
                        .size(70.dp)
                        .padding(10.dp),
                    painter = image,
                    contentDescription = "Username icon"
                )
                Text(text = "_netrang25")
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val image = painterResource(R.drawable.hq720)
                Image(
                    modifier = Modifier
                        .size(70.dp)
                        .padding(10.dp),
                    painter = image,
                    contentDescription = "Email icon"
                )
                Text(text = "netrang@gmail.com")
            }
        }
    }
}
