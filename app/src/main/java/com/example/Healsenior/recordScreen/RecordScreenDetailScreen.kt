package com.example.Healsenior.recordScreen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ZoomIn
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Healsenior.workoutScreen.RoutineDescriptionScreenContent
import com.example.Healsenior.workoutScreen.RoutineDescriptionScreenHeader
import com.example.Healsenior.workoutScreen.ShowRoutineDetail
import com.example.Healsenior.workoutScreen.TextBox
import com.example.Healsenior.workoutScreen.removeLastNchars

@Preview
@Composable
fun RecordScreenDetailScreen() {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
    ) {
        RecordScreenDetailScreenHeader()
        RecordScreenDetailScreenContent()
    }
}

@Composable
fun RecordScreenDetailScreenHeader() {
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
                text = "운동 기록 보기",
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
fun RecordScreenDetailScreenContent() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "총 8개 | 32세트 / 23.11.14",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .padding(start = 10.dp)
        )
        IconButton(
            onClick = {

            },
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .padding(end = 10.dp)
        ) {
            Icon(
                Icons.Default.ZoomIn,
                contentDescription = "",
                tint = Color.Black,
                modifier = Modifier
                    .width(30.dp)
                    .height(30.dp)
            )
        }
    }
    LazyColumn(
        modifier = Modifier
            .height(300.dp)
    ) {
        items(8) {
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
        ShowDetailRecord()
    }
}

@Composable
fun ShowDetailRecord() {
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
                Text(
                    text = "근육량 증가 추천 루틴 - 초급",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = "Day 1",
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
                                start = 15.dp,
                                end = 15.dp,
                                top = 5.dp
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
                                start = 15.dp,
                                end = 15.dp,
                                top = 5.dp
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
