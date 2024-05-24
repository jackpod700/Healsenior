package com.example.Healsenior.workoutScreen.Data

import com.example.Healsenior.workoutScreen.RoutineData
import com.example.Healsenior.workoutScreen.WorkOutData

fun GetWorkOutData() : WorkOutData {
    val workOutData = WorkOutData(
        1,
        "0",
        1,
        1,
        1,
        "0",
        "0"
    )

    return workOutData
}

fun GetRoutineData() : RoutineData {
    val routineData = RoutineData(
        1,
        "0",
        "0",
        "0",
        "0",
        "0",
        1,
        1
    )

    return routineData
}