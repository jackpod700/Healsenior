import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.automirrored.filled.TextSnippet
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.PointOfSale
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.Healsenior.login.LoginViewModel
import com.example.Healsenior.login.logout

@Composable
fun MyPageScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
    val currentUser by loginViewModel.currentUser.observeAsState()

    LaunchedEffect(Unit) {
        val currentUid = loginViewModel.getCurrentUserUid()
        if (currentUid != null) {
            loginViewModel.fetchCurrentUser()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF95BDFA))
                .padding(vertical = 16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.padding(start = 15.dp),
                text = "마이페이지",
                fontSize = 28.sp,
                color = Color.White,
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
                if (currentUser != null) {
                    Text(text = currentUser!!.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                    Text(text = "2000.04.29. / 남성", fontSize = 14.sp, color = Color.Gray) // 여기에 사용자 생년월일과 성별 정보를 추가할 수 있습니다.
                } else {
                    Text(text = "사용자 정보 불러오는 중...", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Divider(color = Color.Gray, thickness = 1.dp)

        // Menu Items
        MenuItem(iconRes = Icons.Default.AccountCircle, text = "회원정보 변경") {
            navController.navigate("member_info")
        }
        MenuItem(iconRes = Icons.Default.PointOfSale, text = "포인트 내역") {
            navController.navigate("point_history")
        }
        MenuItem(iconRes = Icons.AutoMirrored.Filled.TextSnippet, text = "작성글 보기") {
            navController.navigate("post_list")
        }
        LogoutRow()
    }
}

@Composable
fun MenuItem(iconRes: ImageVector, text: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable(onClick = onClick)
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = iconRes,
            contentDescription = text,
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, fontSize = 18.sp)
    }
}

@Composable
fun LogoutRow() {
    val context = LocalContext.current // Context를 미리 가져오기

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(Color.White, RoundedCornerShape(8.dp))
            .clickable(onClick = { logout(context) }) // 여기서 context 사용
            .padding(vertical = 16.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = Icons.AutoMirrored.Filled.Logout,
            contentDescription = "로그아웃",
            modifier = Modifier.size(28.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = "로그아웃", fontSize = 18.sp)
    }
}
