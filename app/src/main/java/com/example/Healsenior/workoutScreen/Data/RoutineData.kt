package com.example.Healsenior.workoutScreen

data class RoutineData(
    val rid: Int,
    val routineName: String,
    val workOutList: String,
    val place: String,
    val goal: String,
    val description: String,
    val time: Int,
    val difficulty: Int
)