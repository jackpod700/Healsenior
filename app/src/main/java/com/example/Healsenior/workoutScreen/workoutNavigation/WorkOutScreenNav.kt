package com.example.Healsenior.workoutScreen.workoutNavigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Healsenior.workoutScreen.RecommendWorkOutScreen
import com.example.Healsenior.workoutScreen.RoutineData
import com.example.Healsenior.workoutScreen.RoutineDescriptionScreen
import com.example.Healsenior.workoutScreen.TodayWorkOutScreen
import com.example.Healsenior.workoutScreen.WorkOutData
import com.example.Healsenior.workoutScreen.WorkOutMainScreen
import com.example.Healsenior.workoutScreen.WorkOutProgressScreen

@Composable
fun WorkOutScreenNav(
    dayCount: MutableIntState,
    workOutData: MutableState<List<WorkOutData>>,
    routineData: MutableState<RoutineData>
) {
    val navController = rememberNavController()
    val isRoutineEnd = remember { mutableStateOf(true) }

    NavHost(
        navController = navController,
        startDestination = "WorkOutMainScreen"
    ) {
        composable("WorkOutMainScreen") {
            WorkOutMainScreen(navController, dayCount.intValue, workOutData.value, routineData.value)
        }
        composable("RecommendWorkOutScreen") {
            RecommendWorkOutScreen(navController)
        }
        composable("TodayWorkOutScreen") {
            isRoutineEnd.value = false
            TodayWorkOutScreen(navController, workOutData.value, routineData.value)
        }
        composable("RoutineDescriptionScreen") {
            RoutineDescriptionScreen(navController)
        }
        composable("WorkOutProgressScreen") {
            isRoutineEnd.value = true
            WorkOutProgressScreen(navController, workOutData.value)
        }
    }
}