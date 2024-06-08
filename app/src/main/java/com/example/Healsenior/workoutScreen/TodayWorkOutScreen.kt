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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Healsenior._component.GetPointDialog
import com.example.Healsenior._component.SmallTopBar
import com.example.Healsenior._component.Tag_Button
import com.example.Healsenior._component.Tag_Dialog
import com.example.Healsenior.data.GetRoutineDailyAll
import com.example.Healsenior.data.RoutineDaily
import com.example.Healsenior.data.UpdateUser
import com.example.Healsenior.data.User
import com.example.Healsenior.data.Workout
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Preview
@Composable
fun TodayWorkOutScreen(
    navController: NavHostController,
    user: User,
    workout: MutableList<Workout>,
    isRoutineEnd: MutableState<Boolean>,
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
    ) {
        SmallTopBar(navController, "오늘의 운동")
        TodayWorkOutScreenContent(navController, user, workout, isRoutineEnd)
    }
}

@Composable
fun TodayWorkOutScreenContent(
    navController: NavHostController,
    user: User,
    workout: MutableList<Workout>,
    isRoutineEnd: MutableState<Boolean>,
) {
    val isExpanded = remember { mutableStateOf(false) }

    TodayWorkOutListHeader(workout, isExpanded)
    TodayWorkOutListContent(navController, user, workout, isRoutineEnd, isExpanded)
}

@Composable
fun TodayWorkOutListHeader(
    workout: MutableList<Workout>,
    isExpanded: MutableState<Boolean>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "총 ${workout.size}개 | ${workout.sumOf { it.set }}세트",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            modifier = Modifier
                .padding(start = 10.dp)
        )
    }
}

//Todo : 확장 기능 구현하기
@Composable
fun TodayWorkOutListContent(
    navController: NavHostController,
    user: User,
    workout: MutableList<Workout>,
    isRoutineEnd: MutableState<Boolean>,
    isExpanded: MutableState<Boolean>
) {
    LazyColumn(
        modifier = Modifier
            .height(600.dp)
    ) {
        items(workout.size) { index ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.White)
                    .drawBehind {

                        val strokeWidth = 2 * density
                        val y = size.height - strokeWidth / 2

                        drawLine(
                            Color.Gray,
                            Offset(0f, y),
                            Offset(size.width, y),
                            strokeWidth
                        )
                    }
            ) {
                Row(
                    modifier = Modifier
                        .padding(start = 10.dp, top = 10.dp, bottom = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(60.dp)
                            .background(color = Color(0xFFD9D9D9))
                            .border(
                                width = 2.dp,
                                shape = RectangleShape,
                                color = Color(0xFF95BDFA)
                            ),
                    ) {
                        Icon(
                            Icons.Outlined.PlayCircle,
                            contentDescription = "",
                            tint = Color.Black,
                            modifier = Modifier
                                .width(80.dp)
                                .height(60.dp)
                                .padding(horizontal = 10.dp, vertical = 5.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp)
                    ) {
                        Text(
                            text = workout[index].name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = workout[index].summary,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
    ) {
        if (isRoutineEnd.value) {
            val showDialog = remember { mutableStateOf(false) }
            val showPointDialog = remember { mutableStateOf(false) }

            Tag_Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                "운동 완료",
                true
            ) {
                showDialog.value = true
            }

            if (showDialog.value) {
                Tag_Dialog(
                    onDismissRequest = { showDialog.value = false },
                    onConfirmation = {
                        showDialog.value = false
                        showPointDialog.value = true
                    },
                    mainTextStr = "운동을 완료하시겠습니까?"
                )
            }

            if (showPointDialog.value) {
                val routinedailylist = remember { mutableListOf<RoutineDaily>() }
                val isCallbackEnd = remember { mutableStateOf(false) }

                GetRoutineDailyAll(user.rid) { getRoutinedailylist ->
                    routinedailylist.clear()
                    routinedailylist += getRoutinedailylist
                    isCallbackEnd.value = true
                }

                if (isCallbackEnd.value) {
                    GetPointDialog(
                        onDismissRequest = {
                            val now = LocalDate.now()
                            val yearStr = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
                            val updateDayCnt =
                                if (user.dayCount == routinedailylist.size)
                                    1
                                else
                                    user.dayCount + 1

                            val updateRecordMap =
                                user.recordMap + mapOf(yearStr to mapOf(user.rid to user.dayCount))
                            val updateUser = User(
                                user.uid,
                                user.name,
                                user.rid,
                                updateDayCnt,
                                user.rank,
                                user.point + 250,
                                user.workoutHour,
                                user.calorieSum,
                                user.setSum,
                                updateRecordMap
                            )

                            UpdateUser(updateUser)
                            isRoutineEnd.value = false
                            showPointDialog.value = false
                            navController.navigate("WorkOutScreenMain")
                        },
                        "오늘의 운동 보상으로 +250P가 적립되었어요!"
                    )
                }
            }
        }
        else {
            Tag_Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                "시작하기",
                true
            ) {
                navController.navigate("WorkOutProgressScreen")
            }
        }
    }
}