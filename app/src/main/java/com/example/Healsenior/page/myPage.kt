package com.example.Healsenior.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.TextSnippet
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Healsenior.data.*
import com.example.Healsenior.login.LoginViewModel

@Composable
fun MyPageScreen(
    loginViewModel: LoginViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF87CEEB))
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "마이페이지",
                fontSize = 20.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }

        // Profile Section
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = Icons.Default.AccountBox,
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(60.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = "터미네이터02", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                Text(text = "2000.04.29. / 남성", fontSize = 14.sp, color = Color.Gray)
            }
        }

        Divider(color = Color.Gray, thickness = 1.dp)

        // Menu Items
        MenuItem(iconRes = Icons.Default.AccountCircle, text = "회원정보 변경")
        MenuItem(iconRes = Icons.Default.PointOfSale, text = "포인트 내역")
        MenuItem(iconRes = Icons.AutoMirrored.Filled.TextSnippet, text = "작성글 보기")
        MenuItem(iconRes = Icons.AutoMirrored.Filled.Logout, text = "로그아웃")
        val uid = loginViewModel.auth.currentUser!!.uid
        var routinedailylist1:List<RoutineDaily>?=null
        Button(onClick = { GetRoutineDailyAll("gym1"){ routinedailylist->
            if (routinedailylist != null) {
                routinedailylist1=routinedailylist
                println(routinedailylist1)
            } else {
                println("No user found or error occurred")
            }
        } }) {
            Text(text = "회원정보 변경")
        }
    }
}

@Composable
fun MenuItem(iconRes: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp) // 간격을 줄였습니다.
            .background(Color.White, RoundedCornerShape(8.dp))
            .padding(vertical = 16.dp, horizontal = 16.dp), // 패딩을 키웠습니다.
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = iconRes,
            contentDescription = text,
            modifier = Modifier.size(28.dp) // 아이콘 크기를 키웠습니다.
        )
        Spacer(modifier = Modifier.width(12.dp)) // 간격을 줄였습니다.
        Text(text = text, fontSize = 18.sp) // 글씨 크기를 키웠습니다.
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview(loginViewModel: LoginViewModel) {
    MyPageScreen(loginViewModel)
}
