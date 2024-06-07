package com.example.Healsenior.workoutScreen.workoutUtil

import com.example.Healsenior.R

val placeItemStr = arrayOf("집", "헬스장", "야외")
val goalItemStr = arrayOf("근육 증가 추천 루틴- 초급", "전신 운동 추천 루틴", "생활기능 향상 운동 추천 루틴")
val videoNameMap = mapOf(
    "air_running" to R.raw.air_running,
    "arm_jump" to R.raw.arm_jump,
    "babell_low" to R.raw.babell_low,
    "benchpress" to R.raw.benchpress,
    "biceps_curl" to R.raw.biceps_curl,
    "cable_pushdown_a" to R.raw.cable_pushdown_a,
    "cable_pushdown_b" to R.raw.cable_pushdown_b,
    "cable_sitted_low" to R.raw.cable_sitted_low,
    "chestpress" to R.raw.chestpress,
    "climbing_stairs" to R.raw.climbing_stairs,
    "dumbell_curl" to R.raw.dumbell_curl,
    "dumbell_kickback" to R.raw.dumbell_kickback,
    "dumbellpress" to R.raw.dumbellpress,
    "fly" to R.raw.fly,
    "hack_squat" to R.raw.hack_squat,
    "lat_pulldown" to R.raw.lat_pulldown,
    "leg_curl" to R.raw.leg_curl,
    "leg_extention" to R.raw.leg_extention,
    "militarypress" to R.raw.militarypress,
    "one_arm_dumbel_low" to R.raw.one_arm_dumbel_low,
    "plank" to R.raw.plank,
    "pull_up" to R.raw.pull_up,
    "pull_up_out" to R.raw.pull_up_out,
    "push_up" to R.raw.push_up,
    "shoulderpress" to R.raw.shoulderpress,
    "sit_up" to R.raw.sit_up,
    "spinning" to R.raw.spinning,
    "squat" to R.raw.squat,
    "squat_home" to R.raw.squat_home,
    "stretching" to R.raw.stretching,
    "turn_waist" to R.raw.turn_waist,
    "walk_on_air" to R.raw.walk_on_air,
)

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