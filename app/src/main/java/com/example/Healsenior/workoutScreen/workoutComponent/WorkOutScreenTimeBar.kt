package com.example.Healsenior.workoutScreen.workoutComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.Healsenior.workoutScreen.workoutUtil.timeToStr
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@Composable
fun WorkOutScreenTimeBar(isStopped: MutableState<Boolean>) {
    val hour = remember { mutableIntStateOf(0) }
    val minute = remember { mutableIntStateOf(0) }
    val second = remember { mutableIntStateOf(0) }

    LaunchedEffect (isStopped.value) {
        while (!isStopped.value) {
            second.intValue++

            if (second.intValue == 60) {
                second.intValue = 0
                minute.intValue++
            }

            if (minute.intValue == 60) {
                minute.intValue = 0
                hour.intValue++
            }

            delay(1000)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
            .padding(horizontal = 80.dp)
            .background(
                color = Color(0xFFD9D9D9),
                shape = RoundedCornerShape(15.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = timeToStr(hour.intValue, minute.intValue, second.intValue),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
    }
}