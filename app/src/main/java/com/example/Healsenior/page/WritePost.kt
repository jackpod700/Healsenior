package com.example.Healsenior.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.Healsenior.data.Post
import com.example.Healsenior.data.writeNewPost
import com.example.Healsenior.login.LoginViewModel
import java.util.Date

@Composable
fun PostScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var isSubmitting by remember { mutableStateOf(false) }
    val currentUser = loginViewModel.currentUser.observeAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF95BDFA))
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Text(text = "X", color = Color.White, fontSize = 24.sp)
            }
            Text(
                text = "글 작성",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            OutlinedButton(
                onClick = {
                    if (title.isNotEmpty() && content.isNotEmpty() && currentUser != null) {
                        isSubmitting = true
                        val newPost = Post(
                            pid = 0, // 이 값은 서버에서 할당될 것입니다.
                            uid = currentUser.uid,
                            author = currentUser.name,
                            title = title,
                            content = content,
                            like = 0,
                            comments = 0,
                            view = 0,
                            date = Date()
                        )
                        writeNewPost(newPost) { success ->
                            isSubmitting = false
                            if (success) {
                                navController.popBackStack() // 작성 후 이전 화면으로 돌아갑니다.
                            } else {
                                // Handle the error
                            }
                        }
                    }
                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = Color(0xFF6CA6CD) // 탑바보다 약간 진한 하늘색
                ),
                modifier = Modifier
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(
                        color = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                } else {
                    Text(text = "등록", color = Color.White, fontSize = 16.sp)
                }
            }
        }

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize()
        ) {
            BasicTextField(
                value = title,
                onValueChange = { title = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .background(Color.White),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    Column {
                        if (title.isEmpty()) {
                            Text(
                                text = "제목을 입력해주세요.",
                                style = TextStyle(fontSize = 18.sp, color = Color.Gray)
                            )
                        }
                        innerTextField()
                    }
                }
            )
            HorizontalDivider(thickness = 1.dp, color = Color.Gray)

            BasicTextField(
                value = content,
                onValueChange = { content = it },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp)
                    .background(Color.White),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
                decorationBox = { innerTextField ->
                    Column {
                        if (content.isEmpty()) {
                            Text(
                                text = "내용을 입력해주세요.",
                                style = TextStyle(fontSize = 18.sp, color = Color.Gray)
                            )
                        }
                        innerTextField()
                    }
                }
            )
            HorizontalDivider(thickness = 1.dp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PostScreenPreview() {
    val navController = rememberNavController()
    PostScreen(navController = navController)
}



