package com.example.hiltretro.presentation.coin_List.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    text: String,
    style: TextStyle = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    color: Color = Color.Unspecified,
    textAlign: TextAlign? = null,
    modifier: Modifier = Modifier,
    fontStyle: FontStyle = FontStyle.Normal,
    overflow: TextOverflow = TextOverflow.Visible
) {
    Text(
        text = text,
        style = style.copy(fontStyle = fontStyle),
        color = color,
        textAlign = textAlign,
        modifier = modifier,
        overflow = overflow
    )
}