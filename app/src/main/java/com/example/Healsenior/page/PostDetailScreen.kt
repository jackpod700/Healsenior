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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.Healsenior.data.Comment
import com.example.Healsenior.data.GetCommentAll
import com.example.Healsenior.data.Post
import com.example.Healsenior.data.writeNewComment
import com.example.Healsenior.login.LoginViewModel
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.imePadding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PostDetailScreen(post: Post, loginViewModel: LoginViewModel = viewModel()) {
    var currentPost by remember { mutableStateOf(post) }
    val comments = remember { mutableStateListOf<Comment>() }
    val currentUser = loginViewModel.currentUser.observeAsState().value

    // 화면이 처음 구성될 때 댓글을 가져옵니다.
    LaunchedEffect(currentPost.pid) {
        GetCommentAll(currentPost.pid) { fetchedComments ->
            comments.clear()
            comments.addAll(fetchedComments)
        }
    }

    val commentTextState = remember { mutableStateOf(TextFieldValue()) }
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(16.dp),
            ) {
                OutlinedTextField(
                    value = commentTextState.value,
                    onValueChange = { commentTextState.value = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text(text = "댓글을 입력하세요...") }
                )
                IconButton(
                    onClick = {
                        println("버튼 클릭됨") // 로그 추가
                        if (commentTextState.value.text.isNotBlank()) {
                            val newComment = currentUser?.name?.let {
                                Comment(currentPost.pid, 0,
                                    it, commentTextState.value.text, Date())
                            }
                            println("새 댓글: $newComment") // 로그 추가
                            if (newComment != null) {
                                writeNewComment(newComment,
                                    onSuccess = {
                                        println("댓글 추가 성공") // 로그 추가
                                        comments.add(newComment)
                                        commentTextState.value = TextFieldValue() // 댓글 입력란 초기화
                                        keyboardController?.hide() // 키보드 숨기기
                                        // 댓글 수 갱신을 위해 Post 객체 다시 가져오기
                                        getPost(currentPost.pid) { updatedPost ->
                                            currentPost = updatedPost
                                        }
                                    },
                                    onFailure = { exception ->
                                        println("댓글 추가 실패: $exception") // 로그 추가
                                    }
                                )
                            }
                        } else {
                            println("댓글 내용이 비어 있음") // 로그 추가
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = "Send Comment")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .imeNestedScroll()
                .padding(horizontal = 16.dp)
        ) {
            // 게시글 정보
            PostDetail(currentPost)

            Spacer(modifier = Modifier.height(16.dp))

            // 댓글 목록
            Text(
                text = "댓글",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                items(comments) { comment ->
                    CommentCard(comment)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

fun getPost(pid: Int, callback: (Post) -> Unit) {
    val db = Firebase.firestore
    db.collection("Post").document(pid.toString())
        .get()
        .addOnSuccessListener { document ->
            val post = document.toObject(Post::class.java)
            if (post != null) {
                callback(post)
            }
        }
        .addOnFailureListener { exception ->
            println("Error getting post: $exception")
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
                text = SimpleDateFormat("yyyy.MM.dd / HH:mm", Locale.getDefault()).format(comment.date),
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
