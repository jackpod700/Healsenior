package com.example.Healsenior._navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Healsenior.data.Routine
import com.example.Healsenior.data.RoutineDaily
import com.example.Healsenior.data.User
import com.example.Healsenior.data.Workout
import com.example.Healsenior.login.LoginViewModel
import com.example.Healsenior.workoutScreen.RecommendWorkOutScreen
import com.example.Healsenior.workoutScreen.RoutineDescriptionScreen
import com.example.Healsenior.workoutScreen.TodayWorkOutScreen
import com.example.Healsenior.workoutScreen.WorkOutMainScreen
import com.example.Healsenior.workoutScreen.WorkOutProgressScreen

@Composable
fun WorkOutScreenNav(loginViewModel: LoginViewModel) {
    val uid = loginViewModel.auth.uid
    val user = remember { mutableStateOf<User?>(null) }
    val routine = remember { mutableStateOf<Routine?>(null) }
    val routineDaily = remember { mutableStateOf<RoutineDaily?>(null) }
    val workout = remember { mutableListOf<Workout>() }

    val navController = rememberNavController()

    val isRoutineEnd = remember { mutableStateOf(false) }
    val selectedRoutine = remember { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = "WorkOutScreenMain",
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable("WorkOutScreenMain") {
            isRoutineEnd.value = false
            WorkOutMainScreen(navController, uid, user, routine, routineDaily, workout)
        }
        composable("RecommendWorkOutScreen") {
            RecommendWorkOutScreen(navController, routine.value!!, selectedRoutine)
        }
        composable("TodayWorkOutScreen") {
            TodayWorkOutScreen(navController, user.value!!, workout, isRoutineEnd)
        }
        composable("RoutineDescriptionScreen") {
           RoutineDescriptionScreen(navController, user.value!!, routine.value!!, selectedRoutine)
        }
        composable("WorkOutProgressScreen") {
            isRoutineEnd.value = true
            WorkOutProgressScreen(navController, workout)
        }
    }
}