package com.example.Healsenior.workoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Healsenior._component.BigTopBar
import com.example.Healsenior._component.Button
import com.example.Healsenior._navigation.WorkOutScreenNav
import com.example.Healsenior.data.Routine
import com.example.Healsenior.data.RoutineDaily
import com.example.Healsenior.data.User
import com.example.Healsenior.data.Workout
import com.example.Healsenior.login.LoginViewModel

@Composable
fun WorkOutScreen(loginViewModel: LoginViewModel) {
    val uid = loginViewModel.auth.currentUser!!.uid

    var user1: User?= null
    /*
        GetUser(uid){user->
            if (user != null) {
                user1=user
            } else {
                println("No user found or error occurred")
            }
        }
    */

    user1 = User(
        "1",
        "nick1",
        "1",
        2,
        12,
        300,
        5,
        200,
        10,
        mapOf("2024.05.01" to mapOf("1" to 1), "2024.04.19" to mapOf("2" to 2), "2024.05.25" to mapOf("3" to 3))
    )

    var routine1: Routine?= null
    /*
        GetRoutine(user1.rid){routine->
            if (routine != null) {
                routine1=routine
                println(routine1)
            } else {
                println("No user found or error occurred")
            }
        }
    */

    routine1 = Routine(
        "1",
        "근육량 증가 추천 플랜(입문)",
        "place",
        "goal",
        "description"
    )

    val routineDaily1: RoutineDaily?
    /*
        GetRoutineDaily(rid, user1.dayCount){routineDaily->
            if (routineDaily != null) {
                routineDaily1=routineDaily
                println(routineDaily1)
            } else {
                println("No user found or error occurred")
            }
        }
    */

    routineDaily1 = RoutineDaily(
        "1",
        2,
        listOf("1", "2", "3", "4", "5"),
        "등, 이두, 코어",
        82,
        "상"
    )

    val workout1: MutableList<Workout> = mutableListOf()
    /*
        for (r in routineDaily1) {
            GetWorkout(r.wid){workout->
                if (workout != null) {
                    workout1.add(workout)
                    println(workout1)
                } else {
                    println("No user found or error occurred")
                }
            }
        }
    */

    workout1.add(
        Workout(
            "1",
            "워밍업 스트레칭",
            2,
            listOf(1, 1, 1),
            1,
            "videolink",
            "척추 부상: 척추는 케이블 로우 ...",
            "5종류의 스트레칭"
        )
    )
    workout1.add(
        Workout(
            "2",
            "시티드 케이블 로우",
            4,
            listOf(1, 1, 1),
            1,
            "videolink",
            "척추 부상: 척추는 케이블 로우 ...",
            "등 - 수평 당기기 운동"
        )
    )
    workout1.add(
        Workout(
            "3",
            "렛 풀 다운",
            4,
            listOf(1, 1, 1),
            1,
            "videolink",
            "척추 부상: 척추는 케이블 로우 ...",
            "등 - 수직 당기기 운동"
        )
    )
    workout1.add(
        Workout(
            "4",
            "원 암 덤벨 로우",
            5,
            listOf(1, 1, 1),
            1,
            "videolink",
            "척추 부상: 척추는 케이블 로우 ...",
            "등 - 수직 당기기 운동"
        )
    )
    workout1.add(
        Workout(
            "5",
            "바벨로우",
            5,
            listOf(1, 1, 1),
            1,
            "videolink",
            "척추 부상: 척추는 케이블 로우 ...",
            "등 - 수직 당기기 운동"
        )
    )

    WorkOutScreenNav(routine1, routineDaily1, workout1)
}

@Preview
@Composable
fun WorkOutMainScreen(
    navController: NavHostController,
    routine: Routine,
    routineDaily: RoutineDaily,
    workout: MutableList<Workout>
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BigTopBar("운동")
        WorkOutScreenContent(navController, routine, routineDaily, workout)
    }
}

@Composable
fun WorkOutScreenContent(
    navController: NavHostController,
    routine: Routine,
    routineDaily: RoutineDaily,
    workout: MutableList<Workout>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(450.dp)
            .background(color = Color(0xFFD9D9D9))
    ) {
        Text(
            text = routine.name,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 20.dp, top = 20.dp, end = 20.dp, bottom = 20.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(start = 20.dp, end = 20.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFF95BDFA),
                    shape = RoundedCornerShape(15.dp),
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp)
                ),
        ) {
            ShowWorkOutDetail(navController, routine, routineDaily, workout)
        }
    }
    Column(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, top = 30.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            navController,
            "RecommendWorkOutScreen",
            "운동 루틴 더보기",
            true
        )
    }
}