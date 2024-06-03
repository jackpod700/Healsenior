package com.example.Healsenior.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Store
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController



data class PointHistory(
    val date: String,
    val description: String,
    val points: String
)

@Composable
fun PointHistoryScreen(navController: NavHostController) {

    val pointHistories = remember {
        listOf(
            PointHistory("2023.10.28.", "오늘의 운동 보상", "+250 P"),
            PointHistory("2023.10.26.", "오늘의 운동 보상", "+250 P"),
            PointHistory("2023.10.23.", "출석 이벤트", "+100 P"),
            PointHistory("2023.10.19.", "오늘의 운동 보상", "+250 P"),
            PointHistory("2023.10.15.", "출석 이벤트", "+100 P"),
            // 추가 포인트 이벤트 데이터
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF87CEEB))
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    modifier = Modifier.padding(start = 7.dp),
                    onClick = { navController.popBackStack() }) {
                    Text("<", fontSize = 30.sp, color = Color.Black)
                }
                Spacer(modifier = Modifier.width(85.dp))
                Text(
                    text = "포인트 내역",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    modifier = Modifier.padding(end = 7.dp),
                    onClick = { navController.navigate("product_list_screen") }) {
                    Icon(Icons.Default.Store, contentDescription = "Store", tint = Color.Black,
                        modifier = Modifier.size(35.dp))
                }
            }
        }
        Text(
            text = "사용 가능한 포인트",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        Text(
            text = "6,350 P",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp)
        )

        // 소멸 예정 포인트
        Text(
            text = "소멸 예정 포인트",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
        )
        Text(
            text = "1,600 P (24.09.12.까지)",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp)
        )

        // 전체 건수
        Text(
            text = "전체",
            fontSize = 16.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 16.dp, top = 8.dp)
        )
        Text(
            text = "총 ${pointHistories.size}건",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 16.dp)
        )

        // 포인트 히스토리 목록
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            items(pointHistories) { history ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = history.date,
                        fontSize = 14.sp,
                        color = Color.Gray,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = history.description,
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = history.points,
                        fontSize = 14.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        textAlign = androidx.compose.ui.text.style.TextAlign.End
                    )
                }
            }
        }
    }
}