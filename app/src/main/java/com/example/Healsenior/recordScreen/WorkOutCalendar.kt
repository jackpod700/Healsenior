package com.example.Healsenior.recordScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val str = arrayOf("일", "월", "화", "수", "목", "금", "토")

@Preview
@Composable
fun WorkOutCalendar() {
    Column(
        modifier = Modifier
            .padding(start = 35.dp, end = 35.dp, top = 20.dp, bottom = 30.dp)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Top
    ) {
        CalendarHeader()
        CalendarContent()
        CalendarFooter()
    }
}

@Composable
fun CalendarHeader() {
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
        ShowMonth()
        ChangeMonth()
    }
}
@Composable
fun CalendarContent() {
    ShowCalendar()
}
@Composable
fun CalendarFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ShowCurrentLocation()
        ShowRefreshButton()
    }
}

@Composable
fun ShowMonth() {
    Row {
        Text(
            text = "2023년 11월",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}
@Composable
fun ChangeMonth() {
    Row {
        IconButton(
            onClick = {

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

@Composable
fun ShowCalendar() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        ShowWeekBand()
        ShowCalendarBody()
    }
}

@Composable
fun ShowCurrentLocation() {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true },
        modifier = Modifier
            .width(150.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "대한민국/서울 (UDT)",
                fontSize = 11.sp
            )
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
        }
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("대한민국/서울 (UDT)") },
            onClick = { /* Handle edit! */ }
        )
        DropdownMenuItem(
            text = { Text("대한민국/서울 (UDT)") },
            onClick = { /* Handle settings! */ }
        )
        DropdownMenuItem(
            text = { Text("대한민국/서울 (UDT)") },
            onClick = { /* Handle send feedback! */ }
        )
    }
}
@Composable
fun ShowRefreshButton() {
    IconButton(
        onClick = {

        },
        modifier = Modifier.offset(x = 10.dp)
    ) {
            Icon(
                Icons.Default.Refresh,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier
                    .scale(scaleX = -1f, scaleY = 1f)
            )
    }
}

@Composable
fun ShowWeekBand() {
    Row {
        for (i in 0..6) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(1.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = str[i],
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
@Composable
fun ShowCalendarBody() {
    Column {
        Row(modifier = Modifier.padding(top = 10.dp)) {
            for (i in 0..6) {
                if (i <= 3) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                    }

                    continue
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(1.dp)
                            .background(
                                color = Color(0xFF98E5EB),
                                shape = CircleShape
                            )
                    ) {
                        Text(
                            text = (i - 3).toString(),
                        )
                    }
                }
            }
        }

        for (i in 1..4) {
            Row(modifier = Modifier.padding(top = 10.dp)) {
                for (j in 4 + (i - 1) * 7..10 + (i - 1) * 7) {
                    if (j > 30) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                        }

                        break
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(1.dp)
                                .background(
                                    color = Color(0xFF98E5EB),
                                    shape = CircleShape
                                )
                        ) {
                            Text(
                                text = j.toString(),
                            )
                        }
                    }
                }
            }
        }
    }
}