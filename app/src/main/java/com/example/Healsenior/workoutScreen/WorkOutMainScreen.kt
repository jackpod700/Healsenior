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
import androidx.compose.runtime.MutableState
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
import com.example.Healsenior.data.GetRoutine
import com.example.Healsenior.data.GetUser
import com.example.Healsenior.data.Routine
import com.example.Healsenior.data.RoutineDaily
import com.example.Healsenior.data.User
import com.example.Healsenior.data.Workout

@Preview
@Composable
fun WorkOutMainScreen(
    navController: NavHostController,
    uid: String?,
    user: MutableState<User?>,
    routine: MutableState<Routine?>,
    routineDaily: MutableState<RoutineDaily?>,
    workout: MutableList<Workout>,
) {
    GetUser(uid!!) { getUser ->
        if (getUser != null)
            user.value = getUser
    }

    if (user.value != null) {
        GetRoutine(user.value!!.rid) { getRoutine ->
            if (getRoutine != null)
                routine.value = getRoutine
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        BigTopBar("운동")

        if (user.value != null && routine.value != null) {
            WorkOutScreenContent(
                navController,
                user.value!!,
                routine.value!!,
                routineDaily,
                workout
            )
        }
    }
}

@Composable
fun WorkOutScreenContent(
    navController: NavHostController,
    user: User,
    routine: Routine,
    routineDaily: MutableState<RoutineDaily?>,
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