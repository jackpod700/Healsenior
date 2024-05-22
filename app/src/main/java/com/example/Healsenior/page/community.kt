package com.example.Healsenior.page

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController


val posts = listOf(
    Post("게시글 제목", "작성자", "2023.11.05", "여기는 게시글의 본문 내용입니다. 많은 정보와 함께 다양한 사람들의 이야기를 담고 있습니다...", 12, 2, 37),
    Post("다른 게시글 제목", "다른 작성자", "2023.11.04", "이 게시글은 다른 내용을 포함하고 있으며, 여러 주제에 대한 흥미로운 정보를 제공합니다...", 26, 14, 127),
    Post("인기있는 낚시채널", "바다[프로필 공개]", "2023.10.26", "얼마 전 바다 낚시하고 왔는데 대박 조과를...", 764, 167, 983),
    Post("투자가", "루카스", "2023.11.02", "20kg 감자 성공! 167cm 80kg 바이오엔지니어 40대 여성입니다...", 526, 96, 751),
    Post("필수", "테크", "2023.09.02", "부디 이 글을 읽고 난 후, 적절하게 필요한 정보를...", 300, 50, 400)
)

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCommunityScreen() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            CommunityTopBar()
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White)
                .fillMaxSize()
        ) {
            CommunityScreen(navController)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityTopBar() {
    val tabs = listOf("인기글", "게시글", "랭킹")
    var selectedTabIndex by remember { mutableStateOf(1) } // Default to the second tab initially

    Column {
        TopAppBar(
            title = { Text(tabs[selectedTabIndex], color = Color.Black) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF87CEEB), // Sky blue background color
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            )
        )
        HorizontalDivider(thickness = 1.dp, color = Color.Black)
    }
}

@Composable
fun AppNavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("regularPosts") {
            RegularPosts(navController = navController, posts = posts)
        }
        composable("popularPosts") {
            PopularPosts(navController = navController, posts = posts)
        }
        composable("rankingPosts") {
            RankingPosts()
        }
        composable("postDetail/{postId}") { backStackEntry ->
            val postId = backStackEntry.arguments?.getString("postId")
            val post = posts.find { it.title == postId }
            if (post != null) {
                PostDetailScreen(post)
            } else {
                Text("Post not found")
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityScreen(navController: NavHostController) {
    val tabs = listOf("인기글", "게시글", "랭킹")
    var selectedTabIndex by remember { mutableStateOf(1) } // Default to second tab initially

    Column {
        CustomTabRow(selectedTabIndex, tabs) { index ->
            selectedTabIndex = index
            when (index) {
                0 -> navController.navigate("popularPosts")
                1 -> navController.navigate("regularPosts")
                2 -> navController.navigate("rankingPosts")
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            when (selectedTabIndex) {
                0 -> AppNavGraph(navController = navController, startDestination = "popularPosts")
                1 -> AppNavGraph(navController = navController, startDestination = "regularPosts")
                2 -> AppNavGraph(navController = navController, startDestination = "rankingPosts")
            }
        }
    }
}

@Composable
fun CustomTabRow(selectedTabIndex: Int, tabs: List<String>, onTabSelected: (Int) -> Unit) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFE0E0E0))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, title ->
                TabItem(
                    title = title,
                    isSelected = selectedTabIndex == index,
                    onClick = { onTabSelected(index) },
                    shape = if (selectedTabIndex == index) RoundedCornerShape(12.dp) else RoundedCornerShape(12.dp)
                )
            }
        }
        HorizontalDivider(thickness = 1.dp, color = Color.Black)
    }
}

@Composable
fun TabItem(title: String, isSelected: Boolean, onClick: () -> Unit, shape: Shape) {
    val backgroundColor = if (isSelected) Color.White else Color(0xFFB0BEC5)
    val textColor = if (isSelected) Color.Black else Color.White
    val fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal

    Box(
        modifier = Modifier
            .padding(8.dp)
            .background(color = backgroundColor, shape = shape)
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = textColor,
            fontWeight = fontWeight,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainCommunityScreen()
}
