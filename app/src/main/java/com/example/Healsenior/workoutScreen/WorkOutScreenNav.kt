package com.example.Healsenior.workoutScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun WorkOutScreenNav(
    workOutData: WorkOutData,
    routineData: RoutineData
) {
    val navController = rememberNavController()
    val isRoutineEnd = remember { mutableStateOf(true) }

    NavHost(
        navController = navController,
        startDestination = "WorkOutScreen"
    ) {
        composable("WorkOutMainScreen") {
            WorkOutMainScreen(navController)
        }
        composable("RecommendWorkOutScreen") {
            RecommendWorkOutScreen(navController)
        }
        composable("TodayWorkOutScreen") {
            TodayWorkOutScreen(navController)
        }
        composable("RoutineDescriptionScreen") {
            RoutineDescriptionScreen(navController)
        }
        composable("WorkOutProgressScreen") {
            WorkOutProgressScreen(navController)
        }
    }
}