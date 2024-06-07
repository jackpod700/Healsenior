package com.example.Healsenior.recordScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import kotlinx.coroutines.delay

@Preview
@Composable
fun RecordDetailScreen(
    navController: NavHostController,
    user: User,
    year: MutableIntState,
    month: MutableIntState,
    selectedDay: MutableIntState
) {
    val dateStr = dateToString(year.intValue, month.intValue, selectedDay.intValue, ".")

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
    ) {
        SmallTopBar(navController, "운동 기록 보기")
        RecordDetailScreenContent(user, dateStr)
    }
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun RecordDetailScreenContent(user: User, dateStr: String) {
    val isZoomed = remember { mutableStateOf(false) }

    val value = user.recordMap[dateStr]!!.entries
    val rid = value.first().key
    val day = value.first().value

    val routine = remember { mutableStateOf<Routine?>(null) }
    val routineDaily = remember { mutableStateOf<RoutineDaily?>(null) }
    val workout = remember { mutableListOf<Workout>() }

    val isRoutineDailyDataGet = remember { mutableStateOf(false) }
    val isCallbackEnd = remember { mutableStateOf(false) }

    GetRoutine(rid) { getRoutine ->
        if (getRoutine != null)
            routine.value = getRoutine
    }

    GetRoutineDaily(rid, day) { getRoutineDaily ->
        if (getRoutineDaily != null) {
            routineDaily.value = getRoutineDaily
            isRoutineDailyDataGet.value = true
        }
    }

    if (isRoutineDailyDataGet.value) {
        LaunchedEffect(Unit) {
            val li = mutableListOf<Workout>()
            var cnt = 0

            for (wid in routineDaily.value!!.workoutList) {
                GetWorkout(wid) { getWorkout ->
                    if (getWorkout != null) {
                        li.add(getWorkout)
                        cnt++

                        if (cnt == routineDaily.value!!.workoutList.size) {
                            workout.clear()
                            workout += li
                            isCallbackEnd.value = true
                        }
                    }
                }

                delay(20)
            }
        }
    }

    if (isCallbackEnd.value) {
        Column {
            ShowDetailRecordHeader(dateStr, workout, isZoomed)
            ShowDetailRecordContent(routine.value!!, routineDaily.value!!, workout)
        }
    }
}