package com.example.Healsenior.recordScreen

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Calendar


val weekband = arrayOf("일", "월", "화", "수", "목", "금", "토")
val monthToDate = arrayOf(29, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

fun DateToString(year: Int, month: Int, date: Int, joinStr: String): String {
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
fun DayOfWeek(year: Int, month: Int, date: Int): Int {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    val strToDate = dateFormat.parse(DateToString(year, month, date, "-"))
    val calendar = Calendar.getInstance()
    calendar.time = strToDate!!
    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    return dayOfWeek
}

fun GetMonthIdx(year: Int, month: Int): Int {
    if ((year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)) && month == 2)
        return 0
    else
        return month
}