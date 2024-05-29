package com.example.Healsenior.workoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.filled.ZoomOut
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Healsenior._component.Button
import com.example.Healsenior._component.SmallTopBar

@Preview
@Composable
fun TodayWorkOutScreen(
    navController: NavHostController,
    workOutData: List<WorkOutData>,
    routineData: RoutineData
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
    ) {
        SmallTopBar(navController, "오늘의 운동")
        TodayWorkOutScreenContent(navController, workOutData, routineData)
    }
}

@Composable
fun TodayWorkOutScreenContent(
    navController: NavHostController,
    workOutData: List<WorkOutData>,
    routineData: RoutineData
) {
    val isExpanded = remember { mutableStateOf(false) }

    TodayWorkOutListHeader(workOutData, isExpanded)
    TodayWorkOutListContent(navController, workOutData, isExpanded)
}

@Composable
fun TodayWorkOutListHeader(
    workOutData: List<WorkOutData>,
    isExpanded: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "총 ${workOutData.size}개 | ${workOutData.sumOf { it.setCount }}세트",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .padding(start = 10.dp)
        )
        IconButton(
            onClick = {
                isExpanded.value = !isExpanded.value
            },
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .padding(end = 10.dp)
        ) {
            Icon(
                if (isExpanded.value)
                    Icons.Default.ZoomOut
                else
                    Icons.Default.ZoomIn,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            )
        }
    }
}

//Todo : 확장 기능 구현하기
@Composable
fun TodayWorkOutListContent(
    navController: NavHostController,
    workOutData: List<WorkOutData>,
    isExpanded: MutableState<Boolean>
) {
    LazyColumn(
        modifier = Modifier
            .height(550.dp)
    ) {
        items(workOutData.size) {
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
                            .padding(start = 10.dp)
                    ) {
                        Text(
                            text = "시티드 케이블 로우",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "45kg x 12회 x 2세트",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                        Text(
                            text = "55kg x 8회 x 2세트",
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                    Text(
                        text = "등 - 수평 당기기 운동",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp
                    )
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            navController,
            "WorkOutProgressScreen",
            "시작하기",
            true
        )
    }
}
