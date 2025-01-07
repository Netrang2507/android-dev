package com.example.hiltretro.presentation.coin_detail.components.yag.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoinTag (
    tag : String
){
    Box(modifier = Modifier
        .padding(6.dp)
        .border(
            width = 1.dp,
            color = Color(0xFF08FF04),
            shape = RoundedCornerShape(100.dp)
        )
        .padding(10.dp)){
        Text(
            text = tag,
            color = Color(0xFF08FF04),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        )
    }
}