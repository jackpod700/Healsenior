package com.example.Healsenior.recordScreen.Calendar

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalendarHeader(year: MutableIntState, month: MutableIntState) {
    Text(
        text = "날짜를 선택해주세요.",
        fontWeight = FontWeight.Bold,
        fontSize = 23.sp
    )
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(
                text = year.intValue.toString() + "월 " + month.intValue.toString() + "일",
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
        Row {
            IconButton(
                onClick = {
                    month.intValue--

                    if (month.intValue == 0)
                    {
                        month.intValue = 12
                        year.intValue--
                    }
                },
                modifier = Modifier.offset(x = 10.dp)
            ) {
                Icon(
                    Icons.Default.ChevronLeft,
                    contentDescription = "",
                    tint = Color(0xFF5897FC),
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
            IconButton(
                onClick = {
                    month.intValue++

                    if (month.intValue == 13)
                    {
                        month.intValue = 1
                        year.intValue++
                    }
                },
                modifier = Modifier.offset(x = 10.dp)
            ) {
                Icon(
                    Icons.Default.ChevronRight,
                    contentDescription = "",
                    tint = Color(0xFF5897FC),
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}