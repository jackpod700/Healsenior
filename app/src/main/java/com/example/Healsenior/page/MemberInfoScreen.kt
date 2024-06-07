package com.example.Healsenior.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun MemberInfoScreen(navController: NavController) {
    var nickname by remember { mutableStateOf(TextFieldValue("터미네이터02")) }
    var gender by remember { mutableStateOf(TextFieldValue("남성")) }
    var birthdate by remember { mutableStateOf(TextFieldValue("1982.04.29")) }
    var height by remember { mutableStateOf(TextFieldValue("176cm")) }
    var weight by remember { mutableStateOf(TextFieldValue("79kg")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF95BDFA))
                .padding(vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(modifier = Modifier.padding(start = 7.dp), onClick = { navController.popBackStack() }) {
                    Text("<", fontSize = 30.sp, color = Color.White)
                }
                Spacer(modifier = Modifier.width(73.dp))
                Text(
                    text = "회원정보 변경",
                    fontSize = 25.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }

        // Content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // 닉네임
            Text("닉네임", fontSize = 14.sp, color = Color.Gray)
            TextField(
                value = nickname,
                onValueChange = { nickname = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // 성별
            Text("성별", fontSize = 14.sp, color = Color.Gray)
            TextField(
                value = gender,
                onValueChange = { gender = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // 생년월일
            Text("생년월일", fontSize = 14.sp, color = Color.Gray)
            TextField(
                value = birthdate,
                onValueChange = { birthdate = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // 신장
            Text("신장", fontSize = 14.sp, color = Color.Gray)
            TextField(
                value = height,
                onValueChange = { height = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // 몸무게
            Text("몸무게", fontSize = 14.sp, color = Color.Gray)
            TextField(
                value = weight,
                onValueChange = { weight = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            // 변경 확인 버튼
            Button(
                onClick = {
                    // 변경된 정보를 데이터베이스에 저장하는 로직 추가
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF87CEEB))
            ) {
                Text("변경 확인", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}