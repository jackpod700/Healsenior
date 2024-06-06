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
import com.example.Healsenior.data.GetRoutine
import com.example.Healsenior.data.GetRoutineDaily
import com.example.Healsenior.data.GetWorkout
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
    val value = user.recordMap[key]!!.entries
    val rid = value.first().key
    val day = value.first().value

    var routine1 = remember { mutableStateOf<Routine?>(null) }
    var routineDaily1 = remember { mutableStateOf<RoutineDaily?>(null) }
    val workout1 = remember { mutableListOf<Workout>() }

    val isCallbackEnd1 = remember { mutableStateOf(false) }
    val isCallbackEnd2 = remember { mutableStateOf(false) }
    val isCallbackEnd3 = remember { mutableStateOf(false) }

    GetRoutine(rid) { routine ->
        if (routine != null) {
            routine1.value = routine
            isCallbackEnd1.value = true
        }
    }

    GetRoutineDaily(rid, day) { routineDaily ->
        if (routineDaily != null) {
            routineDaily1.value = routineDaily
            isCallbackEnd2.value = true
        }
    }

    for (wid in routineDaily1.value!!.workoutList) {
        GetWorkout(wid) { workout ->
            if (workout != null) {
                workout1.add(workout)
                isCallbackEnd3.value = true
            }
        }
    }

    if (isCallbackEnd1.value && isCallbackEnd2.value && isCallbackEnd3.value) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .background(color = Color(0xFFEAEAEA))
        ) {
            SmallTopBar(navController, "운동 기록 보기")
            RecordDetailScreenContent(key, routine1.value!!, routineDaily1.value!!, workout1)
        }
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