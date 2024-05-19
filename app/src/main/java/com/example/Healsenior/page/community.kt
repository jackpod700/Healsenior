package com.example.Healsenior.page

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val tabs = listOf("인기글", "게시글", "랭킹")
    var selectedTabIndex by remember { mutableStateOf(1) } // Default to second tab initially

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(tabs[selectedTabIndex], color = Color.Black) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF87CEEB), // 하늘색 계열 배경색
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White,
                    actionIconContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .background(Color.White) // Set the background color here
        ) {
            CustomTabRow(selectedTabIndex, tabs) {
                selectedTabIndex = it
            }
            when (selectedTabIndex) {
                0 -> PopularPosts(posts2)
                1 -> RegularPosts()
                2 -> RankingPosts()
            }
        }
    }
}

@Composable
fun CustomTabRow(selectedTabIndex: Int, tabs: List<String>, onTabSelected: (Int) -> Unit) {
    TabRow(
        selectedTabIndex = selectedTabIndex,
        indicator = {
            SecondaryIndicator(
                Modifier.tabIndicatorOffset(it[selectedTabIndex]),
                height = 3.dp,
                color = Color(0xFF00BFFF)
            )
        },
        divider = {}
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                text = { Text(title, style = MaterialTheme.typography.bodyLarge) },
                selected = selectedTabIndex == index,
                onClick = { onTabSelected(index) },
                selectedContentColor = Color(0xFF00BFFF),
                unselectedContentColor = Color.Gray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMainScreen() {
    MainScreen()
}
