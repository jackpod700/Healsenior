package com.example.Healsenior.page

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.Healsenior.data.Post

data class Comment(
    val author: String,
    val date: String,
    val content: String
)

@Composable
fun PostDetailScreen(post: Post) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 게시글 정보
        PostDetail(post)

        Spacer(modifier = Modifier.height(16.dp))

        // 댓글 목록
        Text(
            text = "댓글",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        val comments = remember { mutableStateListOf(
            Comment("곰탱이", "2023.11.05 / 17:37", "저도 동감입니다...ㅋㅋ 나이 먹으니까 더 힘드네요...사실 PT 받기는 너무 부담이 되어서...그렇다고 혼자도 안하니 ㅠㅠㅎㅎㅎ"),
            Comment("꼰대사장", "2023.11.05 / 18:21", "자영업자인데 일 끝나고 헬스장 가기도 부담되는데 집에서 나름 편하게(?) 할 수 있어서 좋네요. 사실 헬스장 결제해놓고 안가는게 부지기수...파이팅해요!")
        ) }

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(comments) { comment ->
                CommentCard(comment)
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

        // 댓글 입력란
        val commentTextState = remember { mutableStateOf(TextFieldValue()) }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            OutlinedTextField(
                value = commentTextState.value,
                onValueChange = { commentTextState.value = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text(text = "댓글을 입력하세요...") }
            )
            IconButton(
                onClick = {
                    if (commentTextState.value.text.isNotBlank()) {
                        comments.add(Comment("나", "지금", commentTextState.value.text))
                        commentTextState.value = TextFieldValue() // 댓글 입력란 초기화
                    }
                }
            ) {
                Icon(imageVector = Icons.Default.Send, contentDescription = "Send Comment")
            }
        }
    }
}

@Composable
fun PostDetail(post: Post) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = post.title,
            style = MaterialTheme.typography.titleLarge,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${post.author} - ${post.date}",
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = post.content,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(Icons.Filled.Favorite, contentDescription = "Likes", tint = Color.Red)
            Spacer(modifier = Modifier.width(4.dp))
            Text("${post.like} 좋아요", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.AutoMirrored.Filled.Comment, contentDescription = "Comments", tint = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            Text("${post.comments} 댓글", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.width(16.dp))
            Icon(Icons.Filled.Visibility, contentDescription = "Views", tint = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            Text("${post.view} 조회", style = MaterialTheme.typography.bodySmall)
        }
        Spacer(modifier = Modifier.height(16.dp))
        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
    }
}

@Composable
fun CommentCard(comment: Comment) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = comment.author,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = comment.date,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = comment.content,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPostDetailScreen() {
    val post = Post(
        title = "게시글 제목",
        author = "작성자",
        date = parseDate("2023.11.05"),
        content = "여기는 게시글의 본문 내용입니다. 많은 정보와 함께 다양한 사람들의 이야기를 담고 있습니다...",
        like = 12,
        comments = 2,
        view = 37
    )
    PostDetailScreen(post)
}

