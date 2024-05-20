package com.example.Healsenior.workoutScreen

import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun RecommendWorkOutScreen() {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
    ) {
        RecommendWorkOutScreenHeader()
        RecommendWorkOutScreenContent()
    }
}

@Composable
fun RecommendWorkOutScreenHeader() {
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
                text = "루틴 추천",
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
fun RecommendWorkOutScreenContent() {
    var selectedItem = remember{ mutableIntStateOf(0) }

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
                text = "근육량 증가 추천 플랜(입문)",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
            Text(
                text = "현재 진행중인 루틴이에요!",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
        }
        Row(
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
                    color = Color(0xFFD9D9D9),
                    shape = RoundedCornerShape(10.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                    .showContent(selectedItem.intValue == 0) {
                        Modifier
                            .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                    }
                    .showContent(selectedItem.intValue != 0) {
                        Modifier
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(10.dp)
                            )
                    }
                    .clickable {
                        selectedItem.intValue = 0
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "집",
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(top = 8.dp, bottom = 8.dp)
                    .showContent(selectedItem.intValue == 1) {
                        Modifier
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            )
                    }
                    .showContent(selectedItem.intValue != 1) {
                        Modifier
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(10.dp)
                            )
                    }
                    .clickable {
                        selectedItem.intValue = 1
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "헬스장",
                    fontWeight = FontWeight.Bold
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(1f)
                    .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                    .showContent(selectedItem.intValue == 2) {
                        Modifier
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(10.dp)
                            )
                    }
                    .showContent(selectedItem.intValue != 2) {
                        Modifier
                            .background(
                                color = Color(0xFFD9D9D9),
                                shape = RoundedCornerShape(10.dp)
                            )
                    }
                    .clickable {
                        selectedItem.intValue = 2
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "야외",
                    fontWeight = FontWeight.Bold
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 40.dp)
        ) {
            items(5) {
                Text(
                    text = "근육량 증가",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                LazyRow {
                    items(5) {
                        Box(
                            modifier = Modifier
                                .padding(top = 5.dp, end = 20.dp, bottom = 40.dp)
                                .width(300.dp)
                                .height(200.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFF95BDFA),
                                    shape = RoundedCornerShape(15.dp),
                                )
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(20.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = "근성장을 위한 초급 플랜",
                                        fontSize = 15.sp
                                    )
                                    Text(
                                        text = "근육량 증가 추천 플랜 - 초급",
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = "근육에 조금씩...",
                                        color = Color.Gray,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Row {
                                    Row(
                                        modifier = Modifier
                                            .clickable() {

                                            },
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Text(
                                            text = "자세히 보기",
                                            color = Color.Gray,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Right,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        IconButton(
                                            onClick = {

                                            },
                                            modifier = Modifier
                                                .width(20.dp)
                                                .height(20.dp)
                                        ) {
                                            Icon(
                                                Icons.Default.ChevronRight,
                                                contentDescription = "",
                                                tint = Color.Gray,
                                                modifier = Modifier
                                                    .width(20.dp)
                                                    .height(20.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun Modifier.showContent(condition : Boolean, modifier : Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}