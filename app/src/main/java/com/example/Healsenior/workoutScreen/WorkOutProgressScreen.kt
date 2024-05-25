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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Healsenior.workoutScreen.workoutComponent.WorkOutScreenSmallTopBar
import com.example.Healsenior.workoutScreen.workoutComponent.WorkOutScreenTimeBar

@Preview
@Composable
fun WorkOutProgressScreen(
    navController: NavHostController,
    workOutData: List<WorkOutData>
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
    ) {
        WorkOutScreenSmallTopBar(navController, "운동 진행")
        WorkOutProgressScreenContent(workOutData)
    }
}

@Composable
fun WorkOutProgressScreenContent(workOutData: List<WorkOutData>) {
    val isStopped = remember { mutableStateOf(false) }
    val workOutIdx = remember { mutableIntStateOf(0) }
    val btnStr = remember { mutableStateOf("일시정지") }
    val btnStr2 = remember { mutableStateOf("다음 운동") }

    val hour = remember { mutableIntStateOf(0) }
    val minute = remember { mutableIntStateOf(0) }
    val second = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 40.dp, end = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        WorkOutScreenTimeBar(isStopped, hour, minute, second)
        ShowWorkOutVideoContent(workOutData)
        ShowWorkOutDesciption(workOutData)
        ShowWorkOutList(workOutData, workOutIdx.intValue)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .height(70.dp)
                    .weight(1f)
                    .padding(start = 20.dp, end = 40.dp, bottom = 20.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .background(
                        color = Color(0xFF5B9DFF),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable() {
                        isStopped.value = !isStopped.value

                        if (isStopped.value)
                            btnStr.value = "계속하기"
                        else
                            btnStr.value = "일시정지"
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = btnStr.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier
                    .height(70.dp)
                    .weight(1f)
                    .padding(start = 40.dp, end = 20.dp, bottom = 20.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .background(
                        color = Color(0xFF5B9DFF),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable() {
                        if (workOutIdx.intValue + 1 < workOutData.size)
                            workOutIdx.intValue++
                        else
                            btnStr2.value = "운동 종료"
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = btnStr2.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ShowWorkOutVideoContent(workOutData: List<WorkOutData>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(top = 20.dp)
            .border(
                width = 2.dp,
                color = Color(0xFF95BDFA),
                shape = RoundedCornerShape(15.dp),
            )
            .background(
                color = Color(0xFFD9D9D9),
                shape = RoundedCornerShape(15.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            Icons.Outlined.PlayCircle,
            contentDescription = "",
            tint = Color.Black,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
        )
    }
}

@Composable
fun ShowWorkOutDesciption(workOutData: List<WorkOutData>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(top = 20.dp)
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
        item {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            Color(0xFFFF9900)
                        ),
                    ) {
                        append("척추 부상")
                    }
                    append(": 척추는 케이블 로우 동작 중 상체를 뒤로" +
                            "당길 때 중요한 역할을 합니다." +
                            "text1text1text1text1text1text1" +
                            "text1text1text1text1text1text1" +
                            "text1text1text1text1text1text1" +
                            "text1text1text1text1text1text1" +
                            "text1text1text1text1text1text1" +
                            "text1text1text1text1text1text1" +
                            "text1text1text1text1text1text1" +
                            "text1text1text1text1text1text1")
                },
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun ShowWorkOutList(workOutData: List<WorkOutData>, workOutIdx: Int) {
    ShowWorkOutListHeader(workOutData)
    ShowWorkOutListContent(workOutData, workOutIdx)
}

@Composable
fun ShowWorkOutListHeader(workOutData: List<WorkOutData>) {
    Text(
        text = "시티드 케이블 로우",
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
    )
}

@Composable
fun ShowWorkOutListContent(
    workOutData: List<WorkOutData>,
    workOutIdx: Int
) {
    LazyColumn (
        modifier = Modifier
            .height(150.dp)
    ) {
        items(workOutData[workOutIdx].setCount) { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(top = 5.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF95BDFA),
                        shape = RoundedCornerShape(10.dp),
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${index + 1}세트",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1f)
                )
                Text(
                    text = "45kg",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1f)
                )
                Text(
                    text = "/",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1f)
                )
                Text(
                    text = "12회",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp)
                        .weight(1f)
                )
            }
        }
    }
}
