package com.example.Healsenior.recordScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WorkOutCalendar() {
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
fun Box1() {
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
fun Box2() {
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