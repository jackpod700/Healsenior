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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.Healsenior._component.BigTopBar
import com.example.Healsenior._component.Tag_Button
import com.example.Healsenior.data.GetUser
import com.example.Healsenior.data.User
import com.example.Healsenior.recordScreen.Calendar.WorkOutCalendar

@Preview
@Composable
fun RecordMainScreen(
    navController: NavHostController,
    uid: String,
    user: MutableState<User?>,
    workoutDayArr: MutableSet<Int>,
    year: MutableIntState,
    month: MutableIntState,
    selectedDay: MutableIntState,

    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BigTopBar("기록")
        RecordScreenContent(navController, uid, user, workoutDayArr, year, month, selectedDay)
    }
}

@Composable
fun RecordScreenContent(
    navController: NavHostController,
    uid: String,
    user: MutableState<User?>,
    workoutDayArr: MutableSet<Int>,
    year: MutableIntState,
    month: MutableIntState,
    selectedDay: MutableIntState
) {
    val isCallBackEnd = remember { mutableStateOf(false) }

    GetUser(uid) { getUser ->
        if (getUser != null) {
            user.value = getUser
            isCallBackEnd.value = true
        }
    }

    if (isCallBackEnd.value) {
        val key = user.value!!.recordMap.keys
        workoutDayArr.clear()

        for (dateStr in key) {
            val y = dateStr.substring(0, 4).toInt()
            val m = dateStr.substring(5, 7).toInt()
            val d = dateStr.substring(8, 10).toInt()

            if (y == year.intValue && m == month.intValue)
                workoutDayArr.add(d)
        }

        Column(
            modifier = Modifier.padding(
                start = 20.dp, top = 30.dp, end = 20.dp
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
                WorkOutCalendar(workoutDayArr, year, month, selectedDay)
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
}