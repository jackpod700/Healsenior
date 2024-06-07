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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.Healsenior.data.Post


import java.text.SimpleDateFormat
import java.util.*

fun formatDateToKorean(date: Date): String {
    val formatter = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREAN)
    return formatter.format(date)
}

@Composable
fun PostCard(post: Post, onClick: () -> Unit, onLikeClick: () -> Unit) {
    var likes by remember { mutableStateOf(post.like) }
    val formattedDate = formatDateToKorean(post.date)

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
            Text("${post.author} - $formattedDate", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            Spacer(modifier = Modifier.height(4.dp))
            Text(post.content, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    Icons.Filled.Favorite,
                    contentDescription = "Likes",
                    tint = Color.Red,
                    modifier = Modifier.clickable {
                        onLikeClick()
                        likes++  // 좋아요 상태 업데이트
                    }
                )
                Text("$likes 좋아요", modifier = Modifier.padding(start = 4.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.AutoMirrored.Filled.Comment, contentDescription = "Comments", tint = Color.Gray)
                Text("${post.comments} 댓글", modifier = Modifier.padding(start = 4.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Filled.Visibility, contentDescription = "Views", tint = Color.Gray)
                Text("${post.view} 조회", modifier = Modifier.padding(start = 4.dp))
            }
        }
    }
}

