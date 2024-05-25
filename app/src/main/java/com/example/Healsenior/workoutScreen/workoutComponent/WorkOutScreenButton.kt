package com.example.Healsenior.workoutScreen.workoutComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun WorkOutScreenButton(
    modifier: Modifier,
    navController: NavHostController,
    navDestStr: String,
    text: String
) {
    Column(
        modifier = Modifier
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp),
            )
            .clip(shape = RoundedCornerShape(10.dp))
            .background(
                color = Color(0xFF5B9DFF)
            )
            .clickable {
                navController.navigate(navDestStr)
            }
            .then(modifier),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}