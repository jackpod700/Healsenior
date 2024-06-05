package com.example.Healsenior.workoutScreen

import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player.REPEAT_MODE_ALL
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import com.example.Healsenior.R
import com.example.Healsenior._component.SmallTopBar
import com.example.Healsenior.data.Workout
import com.example.Healsenior.workoutScreen.workoutComponent.WorkOutScreenTimeBar

@Preview
@Composable
fun WorkOutProgressScreen(
    navController: NavHostController,
    workout: MutableList<Workout>
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
    ) {
        SmallTopBar(navController, "운동 진행")
        WorkOutProgressScreenContent(navController, workout)
    }
}

@Composable
fun WorkOutProgressScreenContent(navController: NavHostController, workout: MutableList<Workout>) {
    val isStopped = remember { mutableStateOf(false) }
    val workOutIdx = remember { mutableIntStateOf(0) }
    val btnStr = remember { mutableStateOf("일시정지") }
    val btnStr2 = remember { mutableStateOf("다음 운동") }

    val hour = remember { mutableIntStateOf(0) }
    val minute = remember { mutableIntStateOf(0) }
    val second = remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 40.dp, end = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        WorkOutScreenTimeBar(isStopped, hour, minute, second)
        ShowWorkOutVideoContent(workout, workOutIdx)
        ShowWorkOutDesciption(workout, workOutIdx.intValue)
        ShowWorkOutList(workout, workOutIdx.intValue)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier
                    .height(70.dp)
                    .weight(1f)
                    .padding(start = 20.dp, end = 40.dp, bottom = 20.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .background(
                        color = Color(0xFF5B9DFF),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        isStopped.value = !isStopped.value

                        if (isStopped.value)
                            btnStr.value = "계속하기"
                        else
                            btnStr.value = "일시정지"
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = btnStr.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
            Column(
                modifier = Modifier
                    .height(70.dp)
                    .weight(1f)
                    .padding(start = 40.dp, end = 20.dp, bottom = 20.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(10.dp),
                    )
                    .background(
                        color = Color(0xFF5B9DFF),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable {
                        if (workOutIdx.intValue + 1 < workout.size)
                            workOutIdx.intValue++
                        else if (workOutIdx.intValue + 1 == workout.size)
                            btnStr2.value = "운동 종료"
                        else
                            navController.navigateUp()
                    },
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = btnStr2.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Composable
fun ShowWorkOutVideoContent(workout: MutableList<Workout>, workOutIdx: MutableIntState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(top = 20.dp)
            .border(
                width = 2.dp,
                color = Color(0xFF95BDFA),
                shape = RoundedCornerShape(15.dp),
            )
            .background(
                color = Color(0xFFD9D9D9),
                shape = RoundedCornerShape(15.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val context = LocalContext.current
        val exoplayer = remember {
            ExoPlayer.Builder(context)
                .build()
                .also { exoPlayer ->
                    exoPlayer.prepare()
                    exoPlayer.playWhenReady = false
                    exoPlayer.repeatMode = REPEAT_MODE_ALL
                }
        }

        val mediaItem = MediaItem.fromUri("android.resource://com.example.Healsenior/${
            if (workOutIdx.intValue == 0)
                R.raw.air_running
            else
                R.raw.pull_up_out
        
        }")
        exoplayer.setMediaItem(mediaItem)
        exoplayer.prepare()
        exoplayer.play()

        AndroidView(
            factory = {
                PlayerView(it).apply {
                    player = exoplayer
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        DisposableEffect(Unit) {
            onDispose {
                exoplayer.release()
            }
        }
    }
}

@Composable
fun ShowWorkOutDesciption(workout: MutableList<Workout>, workOutIdx: Int) {
    var splitIndex = 0

    for (i in 0..<workout[workOutIdx].description.length) {
        if (workout[workOutIdx].description[i] == ':') {
            splitIndex = i
            break
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(top = 20.dp)
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
        item {
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            Color(0xFFFF9900)
                        ),
                    ) {
                        append(workout[workOutIdx].description.substring(0, splitIndex + 1))
                    }
                    append(workout[workOutIdx].description.substring(splitIndex + 1))
                },
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(10.dp)
            )
        }
    }
}

@Composable
fun ShowWorkOutList(workout: MutableList<Workout>, workOutIdx: Int) {
    ShowWorkOutListHeader(workout, workOutIdx)
    ShowWorkOutListContent(workout, workOutIdx)
}

@Composable
fun ShowWorkOutListHeader(workout: MutableList<Workout>, workOutIdx: Int) {
    Text(
        text = workout[workOutIdx].name,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
    )
}

@Composable
fun ShowWorkOutListContent(workout: MutableList<Workout>, workOutIdx: Int) {
    LazyColumn (
        modifier = Modifier
            .height(150.dp)
    ) {
        items(workout[workOutIdx].set) { index ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(top = 5.dp)
                    .border(
                        width = 2.dp,
                        color = Color(0xFF95BDFA),
                        shape = RoundedCornerShape(10.dp),
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(10.dp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${index + 1}세트",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1f)
                )
                Text(
                    text = "45kg     /     ${workout[workOutIdx].reps[index]}회",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .weight(1f)
                )
            }
        }
    }
}