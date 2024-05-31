package com.example.Healsenior.workoutScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Healsenior._component.Button
import com.example.Healsenior.data.Routine
import com.example.Healsenior.data.RoutineDaily
import com.example.Healsenior.data.Workout

@Composable
fun ShowWorkOutDetail(
    navController: NavHostController,
    routine: Routine,
    routineDaily: RoutineDaily,
    workout: MutableList<Workout>
) {
    ShowWorkOutDetailHeader(routine, routineDaily)
    ShowWorkOutDetailContent(navController, routineDaily, workout)
}

@Composable
fun ShowWorkOutDetailHeader(routine: Routine, routineDaily: RoutineDaily) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 15.dp)
            .drawBehind {

                val strokeWidth = 2 * density
                val y = size.height - strokeWidth / 2

                drawLine(
                    Color(0xFF95BDFA),
                    Offset(0f, y),
                    Offset(size.width, y),
                    strokeWidth
                )
            }
    ){
        Text(
            text = "Day ${routine.name}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
        )
        Text(
            text = routineDaily.workoutPart,
            color = Color.Gray,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 30.dp, top = 10.dp, bottom = 10.dp)
        )
    }
}

@Composable
fun ShowWorkOutDetailContent(
    navController: NavHostController,
    routineDaily: RoutineDaily,
    workout: MutableList<Workout>
) {
    ShowWorkOutInformation(routineDaily, workout)
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            navController,
            "TodayWorkOutScreen",
            "추천 루틴 시작하기",
            true
        )
    }
}

@Composable
fun ShowWorkOutInformation(
    routineDaily: RoutineDaily,
    workout: MutableList<Workout>
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 10.dp)
    ) {
        Row {
            Column(
                modifier = Modifier.padding(start = 25.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${routineDaily.workoutList.size}",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "운동 종류",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Column(
                modifier = Modifier.padding(start = 25.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${workout.sumOf { it.set }}",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "세트수",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Column(
                modifier = Modifier.padding(start = 25.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${workout.sumOf { it.calorie }}",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "소모칼로리",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
        Row(
            modifier = Modifier.padding(top = 5.dp)
        ) {
            val hour = routineDaily.time / 60
            val minute = routineDaily.time % 60

            Column(
                modifier = Modifier.padding(start = 25.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$hour:$minute",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "소요시간",
                    fontSize = 15.sp,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
            Column(
                modifier = Modifier.padding(start = 25.dp, end = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = routineDaily.difficulty,
                    fontSize = 30.sp,
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