package com.example.Healsenior.workoutScreen.workoutUtil

val placeItemStr = arrayOf("집", "헬스장", "야외")
val goalItemStr = arrayOf("근육량 증가", "스트레칭")

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