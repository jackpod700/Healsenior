package com.example.Healsenior.recordScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.MutableIntState
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

val workoutDay: Array<Boolean> = Array(32) { false }

@Preview
@Composable
fun WorkOutCalendar(yearM: MutableIntState, monthM: MutableIntState, dateM: MutableIntState) {
    Column(
        modifier = Modifier
            .padding(start = 35.dp, end = 35.dp, top = 20.dp, bottom = 30.dp)
            .background(color = Color.White),
        verticalArrangement = Arrangement.Top
    ) {
        CalendarHeader(yearM, monthM)
        CalendarContent(yearM, monthM, dateM)
        CalendarFooter()
    }
}

@Composable
fun CalendarHeader(yearM: MutableIntState, monthM: MutableIntState) {
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
        ShowMonth(yearM, monthM)
        ChangeMonth(yearM, monthM)
    }
}
@Composable
fun CalendarContent(yearM: MutableIntState, monthM: MutableIntState, dateM: MutableIntState) {
    ShowCalendar(yearM, monthM, dateM)
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
fun ShowMonth(yearM: MutableIntState, monthM: MutableIntState) {
    Row {
        Text(
            text = yearM.intValue.toString() + "월 " + monthM.intValue.toString() + "일",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}
@Composable
fun ChangeMonth(yearM: MutableIntState, monthM: MutableIntState) {
    Row {
        IconButton(
            onClick = {
                monthM.intValue--

                if (monthM.intValue == 0)
                {
                    monthM.intValue = 12
                    yearM.intValue--
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
                monthM.intValue++

                if (monthM.intValue == 13)
                {
                    monthM.intValue = 1
                    yearM.intValue++
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

@Composable
fun ShowCalendar(yearM: MutableIntState, monthM: MutableIntState, dateM: MutableIntState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    ) {
        ShowWeekBand()
        ShowCalendarBody(yearM, monthM, dateM)
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
                    text = weekband[i],
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}
@Composable
fun ShowCalendarBody(yearM: MutableIntState, monthM: MutableIntState, dateM: MutableIntState) {
    val dayOfWeek = DayOfWeek(yearM.intValue, monthM.intValue, 1)
    var day = 1
    val dayCntOfMonth = monthToDate[GetMonthIdx(yearM.intValue, monthM.intValue)]

    workoutDay[1] = true
    workoutDay[10] = true
    workoutDay[15] = true
    workoutDay[30] = true

    Column{
        for (i in 1..6) {
            Row{
                for (j in 1..7) {
                    if (day > dayCntOfMonth || (i == 1 && j < dayOfWeek)) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .weight(1f)
                        ) {
                            ShowEmptyCricleBox()
                        }
                    }
                    else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .weight(1f)
                        ) {
                            ShowCricleBox(day++, dateM)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowEmptyCricleBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
    }
}
@Composable
fun ShowCricleBox(day:Int, selectedDay: MutableIntState) {
    val isSelect = (day == selectedDay.intValue)

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp)
                .isWorkOutDataExist(workoutDay[day] && isSelect) {
                    Modifier
                        .background(
                            color = Color(0xFF5B9DFF),
                            shape = CircleShape,
                        )
                }
                .isWorkOutDataExist(workoutDay[day] && !isSelect) {
                    Modifier
                        .background(
                            color = Color(0xFFE3F5F5),
                            shape = CircleShape
                        )
                        .clickable {
                            selectedDay.intValue = day
                        }
                }
                .isWorkOutDataExist(!workoutDay[day]) {
                    Modifier.background(
                        color = Color.White,
                        shape = CircleShape
                    )
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = day.toString(),
            )
        }
    }
}

fun Modifier.isWorkOutDataExist(condition : Boolean, modifier : Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}