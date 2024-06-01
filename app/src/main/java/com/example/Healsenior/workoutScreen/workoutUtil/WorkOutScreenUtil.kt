package com.example.Healsenior.workoutScreen.workoutUtil

val itemStr = arrayOf("집", "헬스장", "야외")

fun difficultyToString(difficulty: Int): String {
    return if (difficulty == 1)
        "상"
    else if (difficulty == 2)
        "중"
    else
        "하"
}

fun timeToStr(hour: Int, minute: Int, second: Int): String {
    var timeStr = ""

    if (hour < 10)
        timeStr += "0"

    timeStr += "$hour:"

    if (minute < 10)
        timeStr += "0"

    timeStr += "$minute:"

    if (second < 10)
        timeStr += "0"

    timeStr += "$second"

    return timeStr
}