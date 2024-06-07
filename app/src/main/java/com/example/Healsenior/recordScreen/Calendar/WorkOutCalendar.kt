package com.example.Healsenior.recordScreen.Calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Healsenior.data.User

@Preview
@Composable
fun WorkOutCalendar(
    workoutDayArr: MutableSet<Int>,
    year: MutableIntState,
    month: MutableIntState,
    selectedDay: MutableIntState
) {
    Column(
        modifier = Modifier
            .padding(start = 35.dp, end = 35.dp, top = 30.dp, bottom = 30.dp)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Top
    ) {
        CalendarHeader(year, month)
        CalendarContent(workoutDayArr, year, month, selectedDay)
    }
}