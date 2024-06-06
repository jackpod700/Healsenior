package com.example.Healsenior.workoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Healsenior._component.Tag_Button
import com.example.Healsenior._component.SmallTopBar
import com.example.Healsenior.data.GetRoutine
import com.example.Healsenior.data.GetRoutineDailyAll
import com.example.Healsenior.data.GetWorkout
import com.example.Healsenior.data.Routine
import com.example.Healsenior.data.RoutineDaily
import com.example.Healsenior.data.UpdateUser
import com.example.Healsenior.data.User
import com.example.Healsenior.data.Workout
import kotlinx.coroutines.delay
import java.util.Timer
import kotlin.concurrent.schedule

@Preview
@Composable
fun RoutineDescriptionScreen(
    navController: NavHostController,
    user: User,
    currentRoutine: MutableState<String>
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
    ) {
        SmallTopBar(navController, "자세히 보기")
        RoutineDescriptionScreenContent(navController, user, currentRoutine)
    }
}

@Composable
fun RoutineDescriptionScreenContent(
    navController: NavHostController,
    user: User,
    currentRoutine: MutableState<String>
) {
    val routine1 = remember { mutableStateOf<Routine?>(null) }
    var routinedailylist1 = remember { mutableListOf<RoutineDaily>() }
    val isCallbackEnd1 = remember { mutableStateOf(false) }
    val isCallbackEnd2 = remember { mutableStateOf(false) }

    GetRoutine(currentRoutine.value) { routine ->
        if (routine != null) {
            routine1.value = routine
            isCallbackEnd1.value = true
        }
    }

    GetRoutineDailyAll(routine1.value!!.rid) { routinedailylist ->
        routinedailylist1 += routinedailylist
        isCallbackEnd2.value = true
    }

    if (isCallbackEnd1.value && isCallbackEnd2.value) {
        Column {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .background(
                        color = Color(0xFF5B9DFF),
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = routine1.value!!.name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )
                Text(
                    text = routine1.value!!.summary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 20.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(450.dp)
                    .padding(top = 50.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
                    )
            ) {
                ShowRoutineDescription(routine1.value!!, routinedailylist1)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp, top = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Tag_Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    "루틴 선택하기",
                    true
                ) {
                    val update_user = User(
                        user.uid,
                        user.name,
                        routine1.value!!.rid,
                        1,
                        user.rank,
                        user.point,
                        user.workoutHour,
                        user.calorieSum,
                        user.setSum,
                        user.recordMap
                    )

                    Timer().schedule(500) {
                        UpdateUser(update_user)
                    }

                    navController.navigate("WorkOutScreenMain")
                }
            }
        }
    }
}

@Composable
fun ShowRoutineDescription(
    routine: Routine,
    routinedailylist: MutableList<RoutineDaily>
) {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
        Text(
            text = "루틴 핵심",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )
        Text(
            text = routine.description,
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 5.dp)
        )
        Text(
            text = "루틴 정보",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 35.dp)
        )
    }
    LazyColumn(
        modifier = Modifier
            .padding(start = 20.dp)
    ) {
        items(routinedailylist.size) { index ->
            val workout1 = remember { mutableListOf<Workout>() }
            val isCallbackEnd = remember { mutableStateOf(false) }

            for (wid in routinedailylist[index].workoutList) {
                GetWorkout(wid) { workout ->
                    if (workout != null) {
                        workout1.add(workout)
                        isCallbackEnd.value = true
                    }
                }
            }

            if (isCallbackEnd.value) {
                Box(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .drawBehind {

                            val strokeWidth = 2 * density
                            val y = size.height - strokeWidth / 2

                            drawLine(
                                Color(0xFF95BDFA),
                                Offset(0f, y),
                                Offset(size.width, y),
                                strokeWidth
                            )
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(bottom = 5.dp)
                    ) {
                        Row {
                            Text(
                                text = "Day ${index + 1}",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = routinedailylist[index].workoutPart,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .padding(start = 20.dp)
                            )
                        }
                        Row {
                            Column(
                                modifier = Modifier.padding(
                                    start = 25.dp,
                                    end = 20.dp,
                                    top = 10.dp
                                ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${workout1.size}",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "운동 종류",
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                            Column(
                                modifier = Modifier.padding(
                                    start = 25.dp,
                                    end = 20.dp,
                                    top = 10.dp
                                ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${workout1.sumOf { it.set }}",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "세트 수",
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                            Column(
                                modifier = Modifier.padding(
                                    start = 25.dp,
                                    end = 20.dp,
                                    top = 10.dp
                                ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${workout1.sumOf { it.calorie }}",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "소모칼로리",
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                        }
                        Row {
                            Column(
                                modifier = Modifier.padding(
                                    start = 25.dp,
                                    end = 20.dp,
                                    top = 10.dp
                                ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${routinedailylist[index].time / 60}:${routinedailylist[index].time % 60}",
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "소요시간",
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                            Column(
                                modifier = Modifier.padding(
                                    start = 25.dp,
                                    end = 20.dp,
                                    top = 10.dp
                                ),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = routinedailylist[index].difficulty,
                                    fontSize = 25.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                                Text(
                                    text = "난이도",
                                    fontSize = 15.sp,
                                    modifier = Modifier.padding(top = 5.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}