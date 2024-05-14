package com.example.Healsenior.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.Healsenior.recordScreen.RecordScreenContent
import com.example.Healsenior.recordScreen.RecordScreenHeader


@Composable
fun RecordScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFEAEAEA))
            .statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        RecordScreenHeader()
        RecordScreenContent()
    }
}