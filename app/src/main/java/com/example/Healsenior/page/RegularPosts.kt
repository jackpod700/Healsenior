package com.example.Healsenior.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

// 데이터 클래스를 사용하여 게시글 정보를 담습니다.

@Composable
fun RegularPosts(navController: NavHostController, posts: List<Post>) {
    LazyColumn(
        modifier = Modifier.padding(16.dp)
    ) {
        items(posts) { post ->
            PostCard(post) {
                navController.navigate("postDetail/${post.title}")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}


