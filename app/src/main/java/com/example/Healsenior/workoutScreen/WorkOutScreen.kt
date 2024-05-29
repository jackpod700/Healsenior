package com.example.Healsenior.workoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Healsenior._component.BigTopBar
import com.example.Healsenior._component.Button
import com.example.Healsenior.workoutScreen.workoutComponent.WorkOutScreenBigTextBox
import com.example.Healsenior.workoutScreen.workoutData.getCurrentRoutineData
import com.example.Healsenior.workoutScreen.workoutData.getDayCount
import com.example.Healsenior.workoutScreen.workoutData.getWorkOutData
import com.example.Healsenior._navigation.WorkOutScreenNav
import com.example.Healsenior.workoutScreen.workoutUtil.difficultyToString

@Composable
fun WorkOutScreen() {
    val dayCount = remember { mutableIntStateOf(getDayCount()) }
    val routineData = remember { mutableStateOf(getCurrentRoutineData()) }
    val workOutData = remember { mutableStateOf(getWorkOutData(dayCount.intValue, routineData.value)) }
    WorkOutScreenNav(dayCount, workOutData, routineData)
}

@Preview
@Composable
fun WorkOutMainScreen(
    navController: NavHostController,
    dayCount: Int,
    workOutData: List<WorkOutData>,
    routineData: RoutineData
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BigTopBar("운동")
        WorkOutScreenContent(navController, dayCount, workOutData, routineData)
    }
}

@Composable
fun WorkOutScreenContent(
    navController: NavHostController,
    dayCount: Int,
    workOutData: List<WorkOutData>,
    routineData: RoutineData
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .background(color = Color(0xFFD9D9D9))
    ) {
        Text(
            text = routineData.routineName,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 20.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(start = 20.dp, end = 20.dp)
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
            ShowWorkOutDetail(navController, dayCount, workOutData, routineData)
        }
    }
    Column(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, top = 30.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            navController,
            "RecommendWorkOutScreen",
            "운동 루틴 더보기",
            true
        )
    }
}

@Composable
fun ShowWorkOutDetail(
    navController: NavHostController,
    dayCount: Int,
    workOutData: List<WorkOutData>,
    routineData: RoutineData
) {
    ShowWorkOutDetailHeader(dayCount)
    ShowWorkOutDetailContent(navController, workOutData, routineData)
}

@Composable
fun ShowWorkOutDetailHeader(
    dayCount: Int
) {
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
            text = "Day $dayCount",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
        )
        Text(
            text = "가슴, 삼두, 코어",
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
    workOutData: List<WorkOutData>,
    routineData: RoutineData
) {
    ShowWorkOutInformation(workOutData, routineData)
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
    workOutData: List<WorkOutData>,
    routineData: RoutineData
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 10.dp)
    ) {
        Row {
            WorkOutScreenBigTextBox("${routineData.workOutList.size}", "운동 종류")
            WorkOutScreenBigTextBox("${workOutData.sumOf { it.setCount }}", "세트수")
            WorkOutScreenBigTextBox("${workOutData.sumOf { it.calorie }}", "소모칼로리")
        }
        Row(
            modifier = Modifier.padding(top = 5.dp)
        ) {
            val hour = routineData.time / 60
            val minute = routineData.time % 60

            WorkOutScreenBigTextBox("$hour:$minute", "소요시간")
            WorkOutScreenBigTextBox(difficultyToString(routineData.difficulty), "난이도")
        }
    }
}