package com.example.Healsenior.recordScreen.Calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.Healsenior.data.User

@Composable
fun CalendarContent(
    user: User,
    workoutDayArr: MutableSet<Int>,
    year: MutableIntState,
    month: MutableIntState,
    selectedDay: MutableIntState
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        ShowWeekBand()
        ShowCalendarBody(user, workoutDayArr, year, month, selectedDay)
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
fun ShowCalendarBody(
    user: User,
    workoutDayArr: MutableSet<Int>,
    year: MutableIntState,
    month: MutableIntState,
    selectedDay: MutableIntState
) {
    val dayOfWeek = dayOfWeek(year.intValue, month.intValue)
    var day = 1
    val dayCntOfMonth = monthToDate[getMonthIdx(year.intValue, month.intValue)]

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
                            ShowEmptyCircleBox()
                        }
                    }
                    else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .weight(1f)
                        ) {
                            ShowCircleBox(workoutDayArr, day++, selectedDay)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ShowEmptyCircleBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
    }
}

@Composable
fun ShowCircleBox(workoutDayArr: MutableSet<Int>, day: Int, selectedDay: MutableIntState) {
    val isSelect = (day == selectedDay.intValue)
    val isWorkOutDone = workoutDayArr.contains(day)
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(2.dp)
                .isWorkOutDataExist(isWorkOutDone && isSelect) {
                    Modifier
                        .background(
                            color = Color(0xFF5B9DFF),
                            shape = CircleShape,
                        )
                }
                .isWorkOutDataExist(isWorkOutDone && !isSelect) {
                    Modifier
                        .background(
                            color = Color(0xFFE3F5F5),
                            shape = CircleShape
                        )
                        .clickable (
                            interactionSource = interactionSource,
                            indication = null
                        ) {
                            selectedDay.intValue = day
                        }
                }
                .isWorkOutDataExist(!isWorkOutDone) {
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