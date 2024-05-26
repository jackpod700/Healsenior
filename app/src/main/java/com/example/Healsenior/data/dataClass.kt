package com.example.Healsenior.data

import android.content.ClipDescription
import java.util.Date

data class User(
    val uid: String = "",
    val name: String = "",
    val rid: Long = 0,
    val dayCount: Int = 0,
    val rank: Int = 0,
    val point: Int = 0,
    val workoutHour: Int = 0,
    val calorieSum: Int = 0,
    val setSum: Int = 0,
    val recordMap: Map<Date, List<Long>> = emptyMap()
)

data class Routine(
    val rid: Long = 0,
    val name: String = "",
    val workoutList: Map<Int,List<Long>> = emptyMap(),
    val place: String = "",
    val goal: String = "",
    val description: String = "",
    val time: Long = 0, //기준 초
    val difficulty: Int = 0,
)

data class Workout(
    val wid: Long = 0,
    val name: String = "",
    val set: Int = 0,
    val breakTime: Long = 0, //기준 초
    val calorie: Int = 0,
    val videoName: String = "",
    val description: String = "",
)

data class Post(
    val pid: Long = 0,
    val uid: String = "",
    val title: String = "",
    val content: String = "",
    val like: Int = 0,
    val comments: List<Comment> = emptyList(),
    val view: Int = 0,
    val date: Date = Date(),
)

data class Comment(
    val uid: String = "",
    val content: String = "",
    val date: Date = Date(),
)

data class Goods(
    val gid: Long = 0,
    val name: String = "",
    val description : String = "",
    val price: Int = 0,
    val image: String = "",
)