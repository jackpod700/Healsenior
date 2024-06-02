package com.example.Healsenior._component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun Tag_Dialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    mainTextStr: String
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(250.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFF95BDFA),
                    shape = RoundedCornerShape(15.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = mainTextStr,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 40.dp)
                )
                Row(
                    modifier = Modifier
                        .weight(1f),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(30.dp)
                            .border(
                                width = 2.dp,
                                color = Color(0xFF95BDFA),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .background(
                                color = Color.LightGray,
                                shape = RoundedCornerShape(15.dp)
                            ),
                        onClick = { onDismissRequest() }
                    ) {
                        Text(
                            text = "취소",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.Gray
                        )
                    }
                    TextButton(
                        modifier = Modifier
                            .weight(1f)
                            .padding(30.dp)
                            .border(
                                width = 2.dp,
                                color = Color(0xFF95BDFA),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .background(
                                color = Color.LightGray,
                                shape = RoundedCornerShape(15.dp)
                            ),
                        onClick = { onConfirmation() }
                    ) {
                        Text(
                            text = "확인",
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF95BDFA)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GetPointDialog(
    onDismissRequest: () -> Unit,
    mainTextStr: String
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Box(
            modifier = Modifier
                .width(300.dp)
                .height(250.dp)
                .border(
                    width = 2.dp,
                    color = Color(0xFF95BDFA),
                    shape = RoundedCornerShape(15.dp)
                )
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = mainTextStr,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}