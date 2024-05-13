package com.example.Healsenior.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Healsenior.R


@Composable
internal fun RecordScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        RecordScreenHeader()
        RecordScreenContent()
    }
}

@Composable
private fun RecordScreenHeader() {
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(60.dp)
        .background(color = Color(0xFF95BDFA))
    )
    {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
               text = "기록",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .padding(start = 20.dp, top = 15.dp)
            )
        }
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End
        ) {
            val image = painterResource(R.drawable.img1)

            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp)
                    .padding(top = 15.dp, end = 15.dp)
            )
        }
    }
}

@Composable
private fun RecordScreenContent(){
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 30.dp, end = 20.dp, bottom = 30.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(2.6f)
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
            WorkOutCalendar()
        }
        Text(
            text = "23.11.14의 운동기록",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color(0xFF95BDFA),
            modifier = Modifier.padding(start = 10.dp, top = 20.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
                .padding(top = 5.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFF5897FC),
                    shape = RoundedCornerShape(15.dp),
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp)
                ),
        ) {
            WorkOutRecord()
        }
    }
}

@Composable
private fun WorkOutCalendar() {
    Column(
        modifier = Modifier.padding(30.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "날짜를 선택해주세요.",
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp
        )
        Row {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "2023년 11월",
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.DarkGray,
                    modifier = Modifier.padding(top = 7.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.End
            )
            {
                Text(
                    text = "<",
                    fontSize = 30.sp,
                    color = Color(0xFF5897FC)
                )
                Text(
                    text = ">",
                    color = Color(0xFF5897FC),
                    fontSize = 30.sp,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
        ) {
            Box1()
            Box2()
        }
        Row(
            modifier = Modifier
                .padding(top = 20.dp, end = 10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "대한민국/서울 (UDT)",
                    fontSize = 12.sp
                )
                Text(
                    text = "▽",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "1",
                    color = Color.Gray,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
private fun WorkOutRecord() {
    Column(
        modifier = Modifier.padding(10.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row {
            Text(
                text = "근육량 증가 추천 루틴 - 초급",
                fontSize = 20.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2.5f),
                textAlign = TextAlign.Left,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = ">",
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 10.dp),
                textAlign = TextAlign.Right,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
        }
        Text(
            text = "1. 시티드 케이블 로우",
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Text(
            text = "45kg x 12회 x 2세트",
            fontSize = 13.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = "55kg x 8회 x 2세트",
            fontSize = 13.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}

@Composable
private fun Box1() {
    Row {
        val str = arrayOf("일", "월", "화", "수", "목", "금", "토")

        for (i in 0..6) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Row(
                    modifier = Modifier
                        .padding(1.dp)
                ) {
                    Text(
                        text = str[i]
                    )
                }
            }
        }
    }
}

@Composable
private fun Box2() {
    Column {
        Row(modifier = Modifier.padding(top = 10.dp)) {
            for (i in 0..6) {
                if (i <= 3) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                    }

                    continue
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(1.dp)
                            .background(
                                color = Color(0xFF98E5EB),
                                shape = CircleShape
                            )
                    ) {
                        Text(
                            text = (i - 3).toString(),
                        )
                    }
                }
            }
        }

        for (i in 1..4) {
            Row(modifier = Modifier.padding(top = 10.dp)) {
                for (j in 4 + (i - 1) * 7..10 + (i - 1) * 7) {
                    if (j > 30) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                        }

                        break
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Row(
                            modifier = Modifier
                                .padding(1.dp)
                                .background(
                                    color = Color(0xFF98E5EB),
                                    shape = CircleShape
                                )
                        ) {
                            Text(
                                text = j.toString(),
                            )
                        }
                    }
                }
            }
        }
    }
}