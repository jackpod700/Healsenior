package com.example.Healsenior.page


import MyPageScreen
import PostDetailScreen
import android.util.Base64
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.Healsenior.data.GetPostId
import com.example.Healsenior.data.Post
import com.google.gson.Gson


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPage() {
    val navController = rememberNavController()

    Scaffold { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "my_page",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("my_page") { MyPageScreen(navController) }
            composable("member_info") { MemberInfoScreen(navController) }
            composable("point_history") { PointHistoryScreen(navController) }
            composable("post_list") { MyPostListScreen(navController) }
            composable(
                "post_detail/{postJson}",
                arguments = listOf(navArgument("postJson") { type = NavType.StringType })
            ) { backStackEntry ->
                val postJson = backStackEntry.arguments?.getString("postJson")
                val postJsonDecoded = String(
                    Base64.decode(
                        postJson,
                        Base64.URL_SAFE or Base64.NO_PADDING or Base64.NO_WRAP
                    ), Charsets.UTF_8
                )
                val post = Gson().fromJson(postJsonDecoded, Post::class.java)
                PostDetailScreen(post)
            }
            composable("product_list_screen") { ProductListScreen(navController) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyPage()
}

