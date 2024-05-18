package com.example.Healsenior.page

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController


// 임시 데이터를 이용한 예제
val posts2 = listOf(
    Post("인기있는 낚시채널", "바다[프로필 공개]", "2023.10.26", "얼마 전 바다 낚시하고 왔는데 대박 조과를...", 764, 167, 983),
    Post("투자가", "루카스", "2023.11.02", "20kg 감자 성공! 167cm 80kg 바이오엔지니어 40대 여성입니다...", 526, 96, 751),
    Post("필수", "테크", "2023.09.02", "부디 이 글을 읽고 난 후, 적절하게 필요한 정보를...", 300, 50, 400)
)

@Composable
fun PopularPosts(navController: NavHostController, posts: List<Post>) {
    Column(modifier = Modifier.padding(16.dp)) {
        posts.sortedByDescending { it.likes + it.comments + it.views }
            .forEach { post ->
                PopularPostCard(post) {
                    navController.navigate("postDetail/${post.title}")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
    }
}

@Composable
fun PopularPostCard(post: Post, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(post.title, style = MaterialTheme.typography.titleLarge)
            Text("${post.author} - ${post.date}", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Text(post.content, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
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
fun PreviewPopularPosts() {
    val navController = rememberNavController()
    PopularPosts(navController, posts2)
}