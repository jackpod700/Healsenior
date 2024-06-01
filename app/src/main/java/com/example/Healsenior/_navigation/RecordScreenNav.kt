package com.example.Healsenior._navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Healsenior.data.User
import com.example.Healsenior.recordScreen.RecordDetailScreen
import com.example.Healsenior.recordScreen.RecordMainScreen

@Composable
fun RecordScreenNav(
    user: User,
    workoutDayArr: MutableSet<Int>,
    year: MutableIntState,
    month: MutableIntState,
    selectedDay: MutableIntState
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "RecordScreenMain"
    ) {
        composable("RecordScreenMain") {
            RecordMainScreen(navController, user, workoutDayArr, year, month, selectedDay)
        }
        composable("RecordScreenDetail") {
            RecordDetailScreen(navController, user, workoutDayArr, year, month, selectedDay)
        }
    }
}