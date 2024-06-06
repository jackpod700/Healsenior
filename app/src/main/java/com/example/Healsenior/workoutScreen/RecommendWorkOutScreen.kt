package com.example.Healsenior.workoutScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.Healsenior._component.SmallTopBar
import com.example.Healsenior.data.GetRoutineAll
import com.example.Healsenior.data.Routine
import com.example.Healsenior.workoutScreen.workoutUtil.goalItemStr
import com.example.Healsenior.workoutScreen.workoutUtil.placeItemStr

@Preview
@Composable
fun RecommendWorkOutScreen(
    navController: NavHostController,
    routine: Routine,
) {
    var routinelist1 = remember{ mutableListOf<Routine>() }
    val isCallBackEnd = remember { mutableStateOf(false) }

    GetRoutineAll { routinelist ->
        routinelist1.clear()
        routinelist1 += routinelist
        isCallBackEnd.value = true
    }

    if (isCallBackEnd.value) {
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .fillMaxSize()
                .background(color = Color(0xFFEAEAEA))
        ) {
            SmallTopBar(navController, "루틴 추천")
            RecommendWorkOutScreenContent(navController, routine, routinelist1)
        }
    }
}

@Composable
fun RecommendWorkOutScreenContent(
    navController: NavHostController,
    routine: Routine,
    routinelist: MutableList<Routine>
) {
    val selectedItem = remember{ mutableIntStateOf(0) }

    Column {
        ShowCurrentRoutineBox(routine)
        PlaceNavigation(selectedItem)
        ShowAllRoutineInSpecificPlace(navController, selectedItem, routinelist)
    }
}

@Composable
fun ShowCurrentRoutineBox(routine: Routine) {
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
            text = routine.name,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 20.dp)
        )
        Text(
            text = "현재 진행중인 루틴이에요!",
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 20.dp)
        )
    }
}

@Composable
fun PlaceNavigation(selectedItem: MutableIntState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(10.dp),
            )
            .background(
                color = Color(0xFFD9D9D9),
                shape = RoundedCornerShape(10.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
                .padding(start = 8.dp, top = 8.dp, bottom = 8.dp)
                .showContent(selectedItem.intValue == 0) {
                    Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                }
                .showContent(selectedItem.intValue != 0) {
                    Modifier
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(10.dp)
                        )
                }
                .clickable {
                    selectedItem.intValue = 0
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "집",
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
                .padding(top = 8.dp, bottom = 8.dp)
                .showContent(selectedItem.intValue == 1) {
                    Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                }
                .showContent(selectedItem.intValue != 1) {
                    Modifier
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(10.dp)
                        )
                }
                .clickable {
                    selectedItem.intValue = 1
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "헬스장",
                fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .weight(1f)
                .padding(end = 8.dp, top = 8.dp, bottom = 8.dp)
                .showContent(selectedItem.intValue == 2) {
                    Modifier
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(10.dp)
                        )
                }
                .showContent(selectedItem.intValue != 2) {
                    Modifier
                        .background(
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(10.dp)
                        )
                }
                .clickable {
                    selectedItem.intValue = 2
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "야외",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun ShowAllRoutineInSpecificPlace(
    navController: NavHostController,
    selectedItem: MutableIntState,
    routinelist: MutableList<Routine>
) {
    LazyColumn(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 40.dp)
    ) {
        items(goalItemStr.size) { index->
            Text(
                text = goalItemStr[index],
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            LazyRow {
                items(routinelist.size) {index2 ->
                    if (routinelist[index2].place == placeItemStr[selectedItem.intValue]
                        && routinelist[index2].goal == goalItemStr[index]) {
                        Box(
                            modifier = Modifier
                                .padding(top = 5.dp, end = 20.dp, bottom = 40.dp)
                                .width(300.dp)
                                .height(200.dp)
                                .border(
                                    width = 2.dp,
                                    color = Color(0xFF95BDFA),
                                    shape = RoundedCornerShape(15.dp),
                                )
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(15.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(20.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .weight(1f)
                                ) {
                                    Text(
                                        text = routinelist[index2].summary,
                                        fontSize = 15.sp
                                    )
                                    Text(
                                        text = routinelist[index2].name,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = routinelist[index2].description,
                                        color = Color.Gray,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Row {
                                    Row(
                                        modifier = Modifier
                                            .clickable {
                                                navController.navigate("RoutineDescriptionScreen")
                                            },
                                        horizontalArrangement = Arrangement.End
                                    ) {
                                        Text(
                                            text = "자세히 보기",
                                            color = Color.Gray,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Right,
                                            modifier = Modifier
                                                .weight(1f)
                                        )
                                        IconButton(
                                            onClick = {
                                                navController.navigate("RoutineDescriptionScreen")
                                            },
                                            modifier = Modifier
                                                .width(20.dp)
                                                .height(20.dp)
                                        ) {
                                            Icon(
                                                Icons.Default.ChevronRight,
                                                contentDescription = "",
                                                tint = Color.Gray,
                                                modifier = Modifier
                                                    .width(20.dp)
                                                    .height(20.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

fun Modifier.showContent(condition : Boolean, modifier : Modifier.() -> Modifier): Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}