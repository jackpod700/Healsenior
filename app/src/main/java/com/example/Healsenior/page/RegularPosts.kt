package com.example.Healsenior.page

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.Healsenior.data.Post
import com.example.Healsenior.data.UpdatePostLike
import com.example.Healsenior.data.UpdatePostView


// 데이터 클래스를 사용하여 게시글 정보를 담습니다.

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegularPosts(navController: NavHostController, posts: List<Post>) {
    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(posts) { post ->
                PostCard(
                    post = post,
                    onClick = {
                        UpdatePostView(post.pid)
                        navController.navigate("postDetail/${post.title}")
                    },
                    onLikeClick = {
                        UpdatePostLike(post.pid)
                        post.like++  // 좋아요 수 업데이트
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


