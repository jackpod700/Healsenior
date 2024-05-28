package com.example.Healsenior.recordScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.Healsenior.data.RoutineDaily
import com.example.Healsenior.data.Workout

@Composable
fun ShowDetailRecordHeader(
    date: String,
    routineDaily: RoutineDaily,
    workout: MutableList<Workout>,
    isZoomed: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "총 ${routineDaily.workoutList.size}개 | ${workout.sumOf { it.set }}세트 / $date",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .padding(start = 10.dp)
        )
        IconButton(
            onClick = {
                isZoomed.value = !isZoomed.value
            },
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .padding(end = 10.dp)
        ) {
            if (isZoomed.value) {
                Icon(
                    Icons.Default.ZoomOut,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                )
            }
            else {
                Icon(
                    Icons.Default.ZoomIn,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                )
            }
        }
    }
}