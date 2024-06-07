package com.example.Healsenior._navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.Healsenior.data.User
import com.example.Healsenior.login.LoginViewModel
import com.example.Healsenior.recordScreen.RecordDetailScreen
import com.example.Healsenior.recordScreen.RecordMainScreen
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun RecordScreenNav(loginViewModel: LoginViewModel) {
    val uid = loginViewModel.auth.uid
    val user = remember { mutableStateOf<User?>(null) }

    val now = LocalDate.now()
    val yearStr = now.format(DateTimeFormatter.ofPattern("yyyy"))
    val monthStr = now.format(DateTimeFormatter.ofPattern("MM"))
    val year = remember { mutableIntStateOf(yearStr.toInt()) }
    val month = remember { mutableIntStateOf(monthStr.toInt()) }

    val workoutDayArr = remember{ mutableSetOf<Int>() }
    val selectedDay = remember { mutableIntStateOf(0) }

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "RecordScreenMain",
        enterTransition = {
            EnterTransition.None
        },
        exitTransition = {
            ExitTransition.None
        }
    ) {
        composable("RecordScreenMain") {
            RecordMainScreen(navController, uid!!, user, workoutDayArr, year, month, selectedDay)
        }
        composable("RecordScreenDetail") {
            RecordDetailScreen(navController, user.value!!, year, month, selectedDay)
        }
    }
}