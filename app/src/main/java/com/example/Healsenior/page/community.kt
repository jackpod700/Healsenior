package com.example.Healsenior.page

import PostDetailScreen
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.Healsenior.data.GetPostAll
import com.example.Healsenior.data.Post
import java.text.SimpleDateFormat
import java.util.Date


@SuppressLint("SimpleDateFormat")
fun parseDate(dateStr: String): Date {
    val format = SimpleDateFormat("yyyy.MM.dd")
    return format.parse(dateStr)
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainCommunityScreen() {
    val navController = rememberNavController()
    var posts by remember { mutableStateOf<List<Post>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    LaunchedEffect(Unit) {
        GetPostAll { postList ->
            posts = postList
            isLoading = false
        }
    }

    LaunchedEffect(currentDestination) {
        if (currentDestination == "regularPosts" || currentDestination == "popularPosts") {
            isLoading = true
            GetPostAll { postList ->
                posts = postList
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            if (currentDestination != null) {
                if (currentDestination != "writePost" && !currentDestination.startsWith("postDetail")) {
                    CommunityTopBar()
                }
            }
        },
        floatingActionButton = {
            if (currentDestination == "regularPosts" || currentDestination == "popularPosts") {
                FloatingActionButton(
                    onClick = { navController.navigate("writePost") },
                    containerColor = Color(0xFF87CEEB),
                    contentColor = Color.White
                ) {
                    Icon(Icons.Default.Add, contentDescription = "작성하기")
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(bottom = 0.dp)
                .background(Color.White)
                .fillMaxSize()
        ) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                CommunityScreen(navController, posts)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommunityTopBar() {

    Column {
        TopAppBar(
            title = { Text(text = "커뮤니티", fontSize = 28.sp) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF95BDFA), // Sky blue background color
                titleContentColor = Color.White,
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White
            )
        )
//        HorizontalDivider(thickness = 1.dp, color = Color.Black)
    }
}

@Composable
fun AppNavGraph(navController: NavHostController, startDestination: String, posts: List<Post>) {
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
        composable("writePost") {
            PostScreen(navController = navController)
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


@Composable
fun CommunityScreen(navController: NavHostController, posts: List<Post>) {
    val tabs = listOf("인기글", "게시글", "랭킹")
    var selectedTabIndex by remember { mutableStateOf(1) } // Default to second tab initially
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination?.route

    LaunchedEffect(currentDestination) {
        selectedTabIndex = when (currentDestination) {
            "popularPosts" -> 0
            "regularPosts" -> 1
            "rankingPosts" -> 2
            else -> 1
        }
    }


    Column {
        if (currentDestination != null) {
            if (currentDestination != "writePost" && !currentDestination.startsWith("postDetail")) {
                CustomTabRow(selectedTabIndex, tabs) { index ->
                    selectedTabIndex = index
                    when (index) {
                        0 -> navController.navigate("popularPosts") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        1 -> navController.navigate("regularPosts") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                        2 -> navController.navigate("rankingPosts") {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        }
        Box(modifier = Modifier.fillMaxSize()) {
            AppNavGraph(navController = navController, startDestination = "regularPosts", posts = posts)
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
//        HorizontalDivider(thickness = 1.dp, color = Color.Black)
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
