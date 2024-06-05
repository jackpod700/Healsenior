package com.example.Healsenior.page

import android.util.Base64
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Healsenior.data.UpdatePostLike
import com.example.Healsenior.data.UpdatePostView
import com.google.gson.Gson


@Composable
fun MyPostListScreen(navController: NavHostController) {

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
                    text = "작성글 보기",
                    fontSize = 20.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterVertically),
                )
            }
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(posts) { post ->
                PostCard(
                    post = post,
                    onClick = {
                        UpdatePostView(post.pid)
                        val postJson = Gson().toJson(post)
                        val postJsonEncoded = Base64.encodeToString(postJson.toByteArray(Charsets.UTF_8), Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP)
                        navController.navigate("post_detail/$postJsonEncoded")
                    },
                    onLikeClick = {
                        UpdatePostLike(post.pid)
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}
