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
    val value = user.recordMap[key]!!.entries
    val rid = value.first()
    val day = value.last()

    var routine1: Routine?= null
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

    routine1 = Routine(
        "1",
        "근육량 증가 추천 루틴 - 초급",
        "place",
        "goal",
        "description"
    )

    val routineDaily1: RoutineDaily?
    /*
        GetRoutineDaily(rid, day){routineDaily->
            if (routineDaily != null) {
                routineDaily1=routineDaily
                println(routineDaily1)
            } else {
                println("No user found or error occurred")
            }
        }
    */

    routineDaily1 = RoutineDaily(
        "1",
        2,
        listOf("1", "2", "3", "4", "5"),
        "등, 이두, 코어",
        82,
        "상"
    )

    val workout1: MutableList<Workout> = mutableListOf()
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

    workout1.add(
        Workout(
            "1",
            "워밍업 스트레칭",
            2,
            listOf(1, 1, 1),
            1,
            "videolink",
            "description",
            "5종류의 스트레칭"
        )
    )
    workout1.add(
        Workout(
            "2",
            "시티드 케이블 로우",
            4,
            listOf(1, 1, 1),
            1,
            "videolink",
            "description",
            "등 - 수평 당기기 운동"
        )
    )
    workout1.add(
        Workout(
            "3",
            "렛 풀 다운",
            4,
            listOf(1, 1, 1),
            1,
            "videolink",
            "description",
            "등 - 수직 당기기 운동"
        )
    )
    workout1.add(
        Workout(
            "4",
            "원 암 덤벨 로우",
            5,
            listOf(1, 1, 1),
            1,
            "videolink",
            "description",
            "등 - 수직 당기기 운동"
        )
    )
    workout1.add(
        Workout(
            "5",
            "바벨로우",
            5,
            listOf(1, 1, 1),
            1,
            "videolink",
            "description",
            "등 - 수직 당기기 운동"
        )
    )

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
    ) {
        SmallTopBar(navController, "운동 기록 보기")
        RecordDetailScreenContent(key, routine1, routineDaily1, workout1)
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