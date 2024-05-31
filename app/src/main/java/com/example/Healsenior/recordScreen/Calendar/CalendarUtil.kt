package com.example.Healsenior.recordScreen.Calendar

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar


val weekband = arrayOf("일", "월", "화", "수", "목", "금", "토")
val monthToDate = arrayOf(29, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

fun dateToString(year: Int, month: Int, date: Int, joinStr: String): String {
    var ret = ""

    ret += "$year$joinStr"

    if (month < 10)
        ret += "0"

    ret += "$month$joinStr"

    if (date < 10)
        ret += "0"

    ret += "$date"

    return ret
}

@SuppressLint("SimpleDateFormat")
fun dayOfWeek(year: Int, month: Int): Int {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val strToDate = dateFormat.parse(dateToString(year, month, 1, "-"))
    val calendar = Calendar.getInstance()
    calendar.time = strToDate!!
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    return dayOfWeek
}

fun getMonthIdx(year: Int, month: Int): Int {
    return if ((year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) && month == 2)
        0
    else
        month
}