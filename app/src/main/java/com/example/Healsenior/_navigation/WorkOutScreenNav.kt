package com.example.Healsenior._navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Healsenior.data.Routine
import com.example.Healsenior.data.RoutineDaily
import com.example.Healsenior.data.Workout
import com.example.Healsenior.workoutScreen.RecommendWorkOutScreen
import com.example.Healsenior.workoutScreen.RoutineDescriptionScreen
import com.example.Healsenior.workoutScreen.TodayWorkOutScreen
import com.example.Healsenior.workoutScreen.WorkOutMainScreen
import com.example.Healsenior.workoutScreen.WorkOutProgressScreen

@Composable
fun WorkOutScreenNav(
    routine: Routine,
    routineDaily: RoutineDaily,
    workout: MutableList<Workout>,
) {
    val navController = rememberNavController()
    val isRoutineEnd = remember { mutableStateOf(true) }
    val selectedRoutine = remember { mutableIntStateOf(0) }

    NavHost(
        navController = navController,
        startDestination = "WorkOutScreenMain"
    ) {
        composable("WorkOutScreenMain") {
            WorkOutMainScreen(navController, routine, routineDaily, workout)
        }
        composable("RecommendWorkOutScreen") {
            RecommendWorkOutScreen(navController, routine)
        }
        composable("TodayWorkOutScreen") {
            isRoutineEnd.value = false
            TodayWorkOutScreen(navController, workout, isRoutineEnd)
        }
        composable("RoutineDescriptionScreen") {
            RoutineDescriptionScreen(navController, selectedRoutine)
        }
        composable("WorkOutProgressScreen") {
            isRoutineEnd.value = true
            WorkOutProgressScreen(navController, workout)
        }
    }
}