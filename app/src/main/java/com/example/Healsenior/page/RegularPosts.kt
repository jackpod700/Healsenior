package com.example.Healsenior.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
data class Post(
    val title: String,
    val author: String,
    val date: String,
    val content: String,  // 게시글 본문 내용 추가
    val likes: Int,
    val comments: Int,
    val views: Int
)

// 임시 데이터 목록
val posts = listOf(
    Post("게시글 제목", "작성자", "2023.11.05", "여기는 게시글의 본문 내용입니다. 많은 정보와 함께 다양한 사람들의 이야기를 담고 있습니다...", 12, 2, 37),
    Post("다른 게시글 제목", "다른 작성자", "2023.11.04", "이 게시글은 다른 내용을 포함하고 있으며, 여러 주제에 대한 흥미로운 정보를 제공합니다...", 26, 14, 127),
    // 추가 게시글
)

@Composable
fun RegularPosts(navController: NavHostController) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        posts.forEach { post ->
            PostCard(post) {
                navController.navigate("postDetail/${post.title}")
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun PostCard(post: Post, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(post.title, style = MaterialTheme.typography.titleLarge)
            Text("${post.author} - ${post.date}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(post.content, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Filled.Favorite, contentDescription = "Likes", tint = Color.Red)
                Text("${post.likes} 좋아요", modifier = Modifier.padding(start = 4.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Filled.Comment, contentDescription = "Comments", tint = Color.Gray)
                Text("${post.comments} 댓글", modifier = Modifier.padding(start = 4.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Filled.Visibility, contentDescription = "Views", tint = Color.Gray)
                Text("${post.views} 조회", modifier = Modifier.padding(start = 4.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegularPosts() {
    val navController = rememberNavController()
    RegularPosts(navController)
}