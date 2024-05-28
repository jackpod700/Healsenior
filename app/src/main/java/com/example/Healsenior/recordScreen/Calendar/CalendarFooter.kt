package com.example.Healsenior.recordScreen.Calendar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CalendarFooter() {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ShowCurrentLocation()
        ShowRefreshButton()
    }
}

@Composable
fun ShowCurrentLocation() {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true },
        modifier = Modifier
            .width(150.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "대한민국/서울 (UDT)",
                fontSize = 11.sp
            )
            Icon(
                Icons.Default.ArrowDropDown,
                contentDescription = "",
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
        }
    }
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text("대한민국/서울 (UDT)") },
            onClick = { /* Handle edit! */ }
        )
        DropdownMenuItem(
            text = { Text("대한민국/서울 (UDT)") },
            onClick = { /* Handle settings! */ }
        )
        DropdownMenuItem(
            text = { Text("대한민국/서울 (UDT)") },
            onClick = { /* Handle send feedback! */ }
        )
    }
}

@Composable
fun ShowRefreshButton() {
    IconButton(
        onClick = {

        },
        modifier = Modifier.offset(x = 10.dp)
    ) {
        Icon(
            Icons.Default.Refresh,
            contentDescription = "",
            tint = Color.Gray,
            modifier = Modifier
                .scale(scaleX = -1f, scaleY = 1f)
        )
    }
}