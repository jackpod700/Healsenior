package com.example.Healsenior.data

data class User(
    val UID: Long = 0,
    val Name: String = "",
    val RID: Long = 0,
    val DayCount: Int = 0,
    val Rank: Int = 0,
    val Point: Int = 0,
    val WorkoutHour: Int = 0,
    val CalorieSum: Int = 0,
    val SetSum: Int = 0,
    val RecordMap: Map<String, List<Long>> = emptyMap()
)