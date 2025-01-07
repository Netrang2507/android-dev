package com.example.hiltretro.presentation.coin_detail.components.yag.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hiltretro.data.remote.dto.TeamMember


@Composable
fun TeamListItem (
    teamMember: TeamMember,
    modifier: Modifier
){
    Column(
        modifier=Modifier
            .padding(4.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = teamMember.name,

            style = TextStyle(
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp
        )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = teamMember.position,
            style =TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            ) ,
            fontStyle = FontStyle.Italic
        )
    }
}