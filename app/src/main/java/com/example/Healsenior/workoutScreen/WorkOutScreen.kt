package com.example.Healsenior.workoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun WorkOutScreen() {
    val routineName = remember { mutableStateOf("근육량 증가 추천 플랜(입문)") }
    val routineDay = remember { mutableIntStateOf(1) }
    val workoutCnt = remember { mutableIntStateOf(9) }
    val setCnt = remember { mutableIntStateOf(34) }
    val totalCalorieInRoutine = remember { mutableIntStateOf(520) }
    val timeNeededInRoutine = remember { mutableIntStateOf(90) }
    val difficulty = remember { mutableStateOf("상") }
    val workoutBodyPart = remember { mutableListOf("가슴, 삼두, 코어") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        WorkOutScreenHeader()
        WorkOutScreenContent(routineName, routineDay, workoutCnt,
            setCnt, totalCalorieInRoutine, timeNeededInRoutine,
            difficulty, workoutBodyPart)
    }
}

@Composable
fun WorkOutScreenHeader() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(color = Color(0xFF95BDFA)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    )
    {
        Row {
            Text(
                text = "운동",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
        }
        Row {
            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .width(60.dp)
                    .height(60.dp)
                    .padding(end = 10.dp)
            ) {
                Icon(
                    Icons.Outlined.Notifications,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                )
            }
        }
    }
}

@Composable
fun WorkOutScreenContent(
    routineName: MutableState<String>,
    routineDay: MutableIntState,
    workoutCnt: MutableIntState,
    setCnt: MutableIntState,
    totalCalorieInRoutine: MutableIntState,
    timeNeededInRoutine: MutableIntState,
    difficulty: MutableState<String>,
    workoutBodyPart: MutableList<String>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .background(color = Color(0xFFD9D9D9))
    ) {
        Text(
            text = routineName.value,
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
            ShowRoutineDetail(routineName, routineDay, workoutCnt,
                setCnt, totalCalorieInRoutine, timeNeededInRoutine,
                difficulty, workoutBodyPart)
        }
    }
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 30.dp, end = 20.dp, bottom = 10.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(start = 20.dp, end = 20.dp, top = 5.dp)
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(10.dp),
                )
                .background(
                    color = Color(0xFF5B9DFF),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {
                    //ShowWorkOutList()
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "운동 루틴 더보기",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ShowRoutineDetail(
    routineName: MutableState<String>,
    routineDay: MutableIntState,
    workoutCnt: MutableIntState,
    setCnt: MutableIntState,
    totalCalorieInRoutine: MutableIntState,
    timeNeededInRoutine: MutableIntState,
    difficulty: MutableState<String>,
    workoutBodyPart: MutableList<String>
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
        var str = ""

        for (bodyPart in workoutBodyPart) {
            str += "$bodyPart,"
        }

        str = removeLastNchars(str, 1).toString()

        Text(
            text = "Day ${routineDay.intValue}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
        )
        Text(
            text = str,
            color = Color.Gray,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(start = 30.dp, top = 10.dp, bottom = 10.dp)
        )
    }
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 10.dp)
    ) {
        Row {
            TextBox("${workoutCnt.intValue}", "운동 종류")
            TextBox("${setCnt.intValue}", "세트수")
            TextBox("${totalCalorieInRoutine.intValue}", "소모칼로리")
        }
        Row(
            modifier = Modifier.padding(top = 5.dp)
        ) {
            val hour = timeNeededInRoutine.intValue / 60
            val minute = timeNeededInRoutine.intValue % 60

            TextBox("$hour:$minute", "소요시간")
            TextBox(difficulty.value, "난이도")
        }
    }
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(10.dp),
                )
                .background(
                    color = Color(0xFF5B9DFF),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {
                    //ShowWorkOutList()
                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "추천 루틴 시작하기",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TextBox(topText : String, bottomText : String) {
    Column(
        modifier = Modifier.padding(start = 25.dp, end = 20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = topText,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = bottomText,
            fontSize = 15.sp,
            modifier = Modifier.padding(top = 5.dp)
        )
    }
}

fun removeLastNchars(str: String?, n: Int): String? {
    return if (str == null || str.length < n) {
        str
    } else str.substring(0, str.length - n)
}