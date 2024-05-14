package com.example.Healsenior.recordScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val str = arrayOf("근육량 증가 추천 루틴 - 초급", "1. 시티드 케이블 로우", "45kg x 12회 x 2세트", "55kg x 8회 x 2세트")

@Composable
fun WorkOutRecord() {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                //기록 상세 화면
            },
        verticalArrangement = Arrangement.Top
    ) {
        Row {
            Text(
                text = str[0],
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
            text = str[1],
            fontSize = 16.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Text(
            text = str[2],
            fontSize = 13.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
        Text(
            text = str[3],
            fontSize = 13.sp,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )
    }
}