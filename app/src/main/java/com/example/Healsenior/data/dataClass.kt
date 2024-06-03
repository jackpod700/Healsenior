package com.example.Healsenior.data

import java.util.Date

data class User(
    val uid: String = "",
    val name: String = "",
    val rid: String = "",
    val dayCount: Int = 0,
    val rank: Int = 0,
    val point: Int = 0,
    val workoutHour: Int = 0,
    val calorieSum: Int = 0,
    val setSum: Int = 0,
    val recordMap: Map<String, Map<String,Int>> = emptyMap()
)

data class Routine(
    val rid: String = "",
    val name: String = "",
    val place: String = "",
    val goal: String = "",
    val description: String = "",
    val summary: String = "",
)

data class RoutineDaily(
    val rid: String = "",
    val day: Int = 0,
    val workoutList: List<String> = emptyList(),
    val workoutPart: String= "",
    val time: Long = 0, //기준 초
    val difficulty: String = "",
)

data class Workout(
    val wid: String = "",
    val name: String = "",
    val set: Int = 0,
    val reps: List<Int> = emptyList(),
    val calorie: Int = 0,
    val videoName: String = "",
    val description: String = "",
    val summary: String = "",
)

data class Post(
    var pid: Int = 0,
    val uid: String = "",
    val author: String = "",
    val title: String = "",
    val content: String = "",
    val like: Int = 0,
    val comments: Int = 0,
    val view: Int = 0,
    val date: Date = Date(),
)

data class Comment(
    val pid: Int = 0,
    var cid: Int = 0,
    val author: String = "",
    val content: String = "",
    val date: Date = Date(),
)

data class Goods(
    val gid: Int = 0,
    val name: String = "",
    val price: Int = 0,
    val image: String = "",
)