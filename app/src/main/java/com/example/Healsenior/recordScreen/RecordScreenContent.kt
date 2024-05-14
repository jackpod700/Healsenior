package com.example.Healsenior.recordScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecordScreenContent(){
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