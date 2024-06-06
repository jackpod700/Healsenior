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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Healsenior._component.BigTopBar
import com.example.Healsenior._component.Tag_Button
import com.example.Healsenior._navigation.WorkOutScreenNav
import com.example.Healsenior.data.GetRoutine
import com.example.Healsenior.data.GetRoutineDaily
import com.example.Healsenior.data.GetUser
import com.example.Healsenior.data.GetWorkout
import com.example.Healsenior.data.Routine
import com.example.Healsenior.data.RoutineDaily
import com.example.Healsenior.data.User
import com.example.Healsenior.data.Workout
import com.example.Healsenior.login.LoginViewModel

@Composable
fun WorkOutScreen(loginViewModel: LoginViewModel) {
    val uid = loginViewModel.auth.uid
    val user1 = remember { mutableStateOf<User?>(null) }
    val routine1 = remember { mutableStateOf<Routine?>(null) }
    val routineDaily1 = remember { mutableStateOf<RoutineDaily?>(null) }
    var workout1 = remember { mutableListOf<Workout>() }

    val isCallbackEnd1 = remember { mutableStateOf(false) }
    val isCallbackEnd2 = remember { mutableStateOf(false) }
    val isCallbackEnd3 = remember { mutableStateOf(false) }
    val isCallbackEnd4 = remember { mutableStateOf(false) }

    GetUser(uid!!) { user ->
        if (user != null) {
            user1.value = user
            isCallbackEnd1.value = true
        }
    }

    if (isCallbackEnd1.value) {
        GetRoutine(user1.value!!.rid) { routine ->
            if (routine != null) {
                routine1.value = routine
                isCallbackEnd2.value = true
            }
        }
    }

    if (isCallbackEnd1.value) {
        GetRoutineDaily(user1.value!!.rid, user1.value!!.dayCount) { routineDaily ->
            if (routineDaily != null) {
                routineDaily1.value = routineDaily
                isCallbackEnd3.value = true
            }
        }
    }

    if (isCallbackEnd3.value) {
        val li = mutableListOf<Workout>()
        var cnt = 0

        for (index in 0..<routineDaily1.value!!.workoutList.size) {
            val wid = routineDaily1.value!!.workoutList[index]

            GetWorkout(wid) { workout ->
                if (workout != null) {
                    li.add(workout)
                    cnt++

                    if (cnt == routineDaily1.value!!.workoutList.size) {
                        workout1.clear()
                        workout1 += li
                        isCallbackEnd4.value = true
                    }
                }
            }
        }
    }

    if (isCallbackEnd1.value && isCallbackEnd2.value && isCallbackEnd3.value && isCallbackEnd4.value)
        WorkOutScreenNav(user1.value!!, routine1.value!!, routineDaily1.value!!, workout1)
}

@Preview
@Composable
fun WorkOutMainScreen(
    navController: NavHostController,
    user: User,
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
        WorkOutScreenContent(navController, user, routine, routineDaily, workout)
    }
}

@Composable
fun WorkOutScreenContent(
    navController: NavHostController,
    user: User,
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
            ShowWorkOutDetail(navController, user, routineDaily, workout)
        }
    }
    Column(
        modifier = Modifier
            .padding(start = 40.dp, end = 40.dp, top = 30.dp)
    ) {
        Tag_Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            "운동 루틴 더보기",
            true
        ) {
            navController.navigate("RecommendWorkOutScreen")
        }
    }
}