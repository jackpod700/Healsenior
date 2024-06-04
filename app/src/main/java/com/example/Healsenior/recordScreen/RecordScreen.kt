package com.example.Healsenior.recordScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.Healsenior._component.BigTopBar
import com.example.Healsenior._component.Tag_Button
import com.example.Healsenior._navigation.RecordScreenNav
import com.example.Healsenior.data.GetUser
import com.example.Healsenior.data.User
import com.example.Healsenior.login.LoginViewModel
import com.example.Healsenior.recordScreen.Calendar.WorkOutCalendar
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun RecordScreen(loginViewModel: LoginViewModel) {
    val uid = loginViewModel.auth.uid
    var user1: User? = null

    GetUser(uid!!) { user ->
        if (user != null) {
            user1 = user
        } else {
            println("No user found or error occurred")
        }
    }

    val now = LocalDate.now()
    val yearStr = now.format(DateTimeFormatter.ofPattern("yyyy"))
    val monthStr = now.format(DateTimeFormatter.ofPattern("MM"))
    val year = remember { mutableIntStateOf(yearStr.toInt()) }
    val month = remember { mutableIntStateOf(monthStr.toInt()) }


    val key = user1!!.recordMap.keys
    val workoutDayArr = mutableSetOf<Int>()

    for (dateStr in key) {
        val y = dateStr.substring(0, 4).toInt()
        val m = dateStr.substring(5, 7).toInt()
        val d = dateStr.substring(8, 10).toInt()

        if (y == year.intValue && m == month.intValue)
            workoutDayArr.add(d)
    }

    val selectedDay = remember {
        mutableIntStateOf(
            if (workoutDayArr.isNotEmpty())
                workoutDayArr.last()
            else
                0
        )
    }

    if (user1 != null)
        RecordScreenNav(user1!!, workoutDayArr, year, month, selectedDay)
}

@Preview
@Composable
fun RecordMainScreen(
    navController: NavHostController,
    user: User,
    workoutDayArr: MutableSet<Int>,
    year: MutableIntState,
    month: MutableIntState,
    selectedDay: MutableIntState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BigTopBar("기록")
        RecordScreenContent(navController, user, workoutDayArr, year, month, selectedDay)
    }
}

@Composable
fun RecordScreenContent(
    navController: NavHostController,
    user: User,
    workoutDayArr: MutableSet<Int>,
    year: MutableIntState,
    month: MutableIntState,
    selectedDay: MutableIntState
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 30.dp, end = 20.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(450.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFF95BDFA),
                    shape = RoundedCornerShape(15.dp),
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp)
                ),
        ) {
            WorkOutCalendar(user, workoutDayArr, year, month, selectedDay)
        }
        Column(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 60.dp)
        ) {
            Tag_Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                "운동 기록 보기",
                workoutDayArr.contains(selectedDay.intValue)
            ) {
               navController.navigate("RecordScreenDetail")
            }
        }
    }
}