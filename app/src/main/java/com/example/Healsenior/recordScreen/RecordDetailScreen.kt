package com.example.Healsenior.recordScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.Healsenior._component.SmallTopBar
import com.example.Healsenior.data.Routine
import com.example.Healsenior.data.RoutineDaily
import com.example.Healsenior.data.User
import com.example.Healsenior.data.Workout
import com.example.Healsenior.recordScreen.Calendar.dateToString

@Preview
@Composable
fun RecordDetailScreen(
    navController: NavHostController,
    user: User,
    workoutDayArr: MutableSet<Int>,
    year: MutableIntState,
    month: MutableIntState,
    selectedDay: MutableIntState
) {
    val key = dateToString(year.intValue, month.intValue, selectedDay.intValue, ".")
    val rid = user.recordMap[key]

    val routine1: Routine?= null
/*
    GetRoutine(rid){routine->
        if (routine != null) {
            routine1=routine
            println(routine1)
        } else {
            println("No user found or error occurred")
        }
    }
*/

    val routineDaily1: RoutineDaily?=null
/*
    GetRoutineDaily(rid, user.dayCount){routineDaily->
        if (routineDaily != null) {
            routineDaily1=routineDaily
            println(routineDaily1)
        } else {
            println("No user found or error occurred")
        }
    }
*/
    val workout1: MutableList<Workout>?=null
/*
    for (r in routineDaily1) {
        GetWorkout(r.wid){workout->
            if (workout != null) {
                workout1.add(workout)
                println(workout1)
            } else {
                println("No user found or error occurred")
            }
        }
    }
*/

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
    ) {
        SmallTopBar(navController, "운동 기록 보기")
        RecordDetailScreenContent(key, routine1!!, routineDaily1!!, workout1!!)
    }
}

@Composable
fun RecordDetailScreenContent(
    date: String,
    routine: Routine,
    routineDaily: RoutineDaily,
    workout: MutableList<Workout>
) {
    val isZoomed = remember { mutableStateOf(false) }

    Column {
        ShowDetailRecordHeader(date, routineDaily, workout, isZoomed)
        ShowDetailRecordContent(routine, routineDaily, workout)
    }
}