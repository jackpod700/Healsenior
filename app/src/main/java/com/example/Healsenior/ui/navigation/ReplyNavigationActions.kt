/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.Healsenior.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.QuestionAnswer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.example.Healsenior.R

object ReplyRoute {
    const val Workout = "Workout"
    const val Record = "Record"
    const val Community = "Community"
    const val Mypage = "Mypage"
}

data class ReplyTopLevelDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)

class ReplyNavigationActions(private val navController: NavHostController) {

    fun navigateTo(destination: ReplyTopLevelDestination) {
        navController.navigate(destination.route) {
            // Pop up to the start destination of the graph to
            // avoid building up a large stack of destinations
            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when
            // reselecting the same item
            launchSingleTop = true
            // Restore state when reselecting a previously selected item
            restoreState = true
        }
    }
}

val TOP_LEVEL_DESTINATIONS = listOf(
    ReplyTopLevelDestination(
        route = ReplyRoute.Workout,
        selectedIcon = Icons.Default.DirectionsRun,
        unselectedIcon = Icons.Default.DirectionsRun,
        iconTextId = R.string.tab_inbox
    ),
    ReplyTopLevelDestination(
        route = ReplyRoute.Record,
        selectedIcon = Icons.Default.CalendarMonth,
        unselectedIcon = Icons.Default.CalendarMonth,
        iconTextId = R.string.tab_article
    ),
    ReplyTopLevelDestination(
        route = ReplyRoute.Community,
        selectedIcon = Icons.Default.QuestionAnswer,
        unselectedIcon = Icons.Default.QuestionAnswer,
        iconTextId = R.string.tab_inbox
    ),
    ReplyTopLevelDestination(
        route = ReplyRoute.Mypage,
        selectedIcon = Icons.Default.AccountCircle,
        unselectedIcon = Icons.Default.AccountCircle,
        iconTextId = R.string.tab_article
    )

)
