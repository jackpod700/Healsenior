package com.example.Healsenior.workoutScreen.workoutData

import com.example.Healsenior.workoutScreen.RoutineData
import com.example.Healsenior.workoutScreen.WorkOutData

fun getWorkOutData(
    dayCount: Int,
    routineData: RoutineData
) : List<WorkOutData> {
    val workOutData = listOf(
        WorkOutData (
        1,
        "0",
        8,
        1,
        120,
        "0",
        "0"
        ),
        WorkOutData (
            2,
            "0",
            5,
            1,
            100,
            "0",
            "0"
        ),
        WorkOutData (
            3,
            "0",
            4,
            1,
            200,
            "0",
            "0"
        ),
    )

    return workOutData
}

fun getCurrentRoutineData() : RoutineData {
    val routineData = RoutineData(
        1,
        "근육량 증가 추천 플랜(입문)",
        mapOf(1 to 1, 2 to 2, 3 to 3),
        "헬스장",
        "0",
        "설명",
        90,
        1
    )

    return routineData
}

fun getDayCount() : Int {
    val dayCount = 1

    return dayCount
}