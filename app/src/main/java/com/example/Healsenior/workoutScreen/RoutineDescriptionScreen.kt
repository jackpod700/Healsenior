package com.example.Healsenior.workoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun RoutineDescriptionScreen() {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
    ) {
        RoutineDescriptionScreenHeader()
        RoutineDescriptionScreenContent()
    }
}

@Composable
fun RoutineDescriptionScreenHeader() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .background(color = Color(0xFF95BDFA)),
    )
    {
        Row {
            IconButton(
                onClick = {

                },
                modifier = Modifier
                    .width(40.dp)
                    .height(40.dp)
            ) {
                Icon(
                    Icons.Default.ChevronLeft,
                    contentDescription = "",
                    tint = Color.Black,
                    modifier = Modifier
                        .width(30.dp)
                        .height(30.dp)
                )
            }
        }
        Row {
            Text(
                text = "자세히 보기",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
        }
    }
}

@Composable
fun RoutineDescriptionScreenContent() {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                .border(
                    width = 2.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(10.dp),
                )
                .background(
                    color = Color(0xFF5B9DFF),
                    shape = RoundedCornerShape(10.dp)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "근육량 증가 추천 루틴 - 초급",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
            Text(
                text = "헬린이를 위한 초급 루틴",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .padding(top = 50.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
                )
        ) {
            ShowRoutineDescription()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(start = 40.dp, end = 40.dp, top = 20.dp)
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

                },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "루틴 선택하기",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ShowRoutineDescription(
    //descriptionList : List<String>
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text(
            text = "루틴 핵심",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = "헬스 초보자/입문자 이용자들을 위한 초급 루틴이에요!...",
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 5.dp)
        )
        Text(
            text = "루틴 정보",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 35.dp)
        )
    }
    LazyColumn(
        modifier = Modifier
            .padding(start = 20.dp)
    ) {
        items(5) { index ->
            Box(
                modifier = Modifier
                    .padding(top = 5.dp)
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
            ) {
                Column(
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                ) {
                    Row {
                        Text(
                            text = "Day ${index + 1}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "가슴, 삼두, 코어",
                            fontSize = 15.sp,
                            modifier = Modifier
                                .padding(start = 20.dp)
                        )
                    }
                    Row {
                        for (i in 1..3) {
                            Column(
                                modifier = Modifier.padding(
                                    start = 25.dp,
                                    end = 20.dp,
                                    top = 10.dp
                                ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "9",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "운동 종류",
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                        }
                    }
                    Row {
                        for (i in 1..2) {
                            Column(
                                modifier = Modifier.padding(
                                    start = 25.dp,
                                    end = 20.dp,
                                    top = 10.dp
                                ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "9",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "운동 종류",
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
