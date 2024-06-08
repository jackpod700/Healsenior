package com.example.Healsenior.recordScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Healsenior.data.Routine
import com.example.Healsenior.data.RoutineDaily
import com.example.Healsenior.data.Workout

@Composable
fun ShowDetailRecordContent(
    routine: Routine,
    routineDaily: RoutineDaily,
    workout: MutableList<Workout>
) {
    LazyColumn(
        modifier = Modifier
            .height(400.dp)
    ) {
        items(routineDaily.workoutList.size) {index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.White)
                    .drawBehind {

                        val strokeWidth = 2 * density
                        val y = size.height - strokeWidth / 2

                        drawLine(
                            Color.Gray,
                            Offset(0f, y),
                            Offset(size.width, y),
                            strokeWidth
                        )
                    }
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(60.dp)
                            .background(color = Color(0xFFD9D9D9))
                            .border(
                                width = 2.dp,
                                shape = RectangleShape,
                                color = Color(0xFF95BDFA)
                            ),
                    ) {
                        Icon(
                            Icons.Outlined.PlayCircle,
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = Modifier
                                .width(80.dp)
                                .height(60.dp)
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = workout[index].name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                        Text(
                            text = workout[index].summary,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .border(
                width = 2.dp,
                color = Color(0xFF95BDFA),
                shape = RoundedCornerShape(15.dp),
            )
            .background(
                color = Color.White,
                shape = RoundedCornerShape(15.dp)
            ),
    ) {
        ShowRoutineSummary(routine, routineDaily, workout)
    }
}

@Composable
fun ShowRoutineSummary(
    routine: Routine,
    routineDaily: RoutineDaily,
    workout: MutableList<Workout>
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 10.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(top = 5.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(bottom = 5.dp)
            ) {
                ShowRoutineSummaryHeader(routine, routineDaily)
                ShowRoutineSummaryContent(routineDaily, workout)
            }
        }
    }
}

@Composable
fun ShowRoutineSummaryHeader(
    routine: Routine,
    routineDaily: RoutineDaily
) {
    Text(
        text = routine.name,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    )
    Row(
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        Text(
            text = "Day ${routineDaily.day}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = routineDaily.workoutPart,
            fontSize = 15.sp,
            modifier = Modifier
                .padding(start = 20.dp)
        )
    }
}

@Composable
fun ShowRoutineSummaryContent(
    routineDaily: RoutineDaily,
    workout: MutableList<Workout>
) {
    Column {
        Row {
            Column(
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 5.dp
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${routineDaily.workoutList.size}",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "운동 종류",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Column(
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 5.dp
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${workout.sumOf { it.set }}",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "세트 수",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Column(
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 5.dp
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${workout.sumOf { it.calorie }}",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "소모 칼로리",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
        Row {
            Column(
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 5.dp
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${routineDaily.time / 60}:${routineDaily.time % 60}",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "소요시간",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Column(
                modifier = Modifier.padding(
                    start = 15.dp,
                    end = 15.dp,
                    top = 5.dp
                ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = routineDaily.difficulty,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "난이도",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}