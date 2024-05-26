package com.example.Healsenior.workoutScreen.workoutComponent

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WorkOutScreenBigTextBox(topText : String, bottomText : String) {
    Column(
        modifier = Modifier.padding(start = 25.dp, end = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = topText,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = bottomText,
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}