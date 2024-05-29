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

package com.example.Healsenior.ui

import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.example.Healsenior.login.LoginScreen
import com.example.Healsenior.login.LoginViewModel
import com.example.Healsenior.login.checkLoginState
import com.example.Healsenior.page.MainCommunityScreen
import com.example.Healsenior.page.MyPageScreen
import com.example.Healsenior.recordScreen.RecordScreen
import com.example.Healsenior.ui.navigation.ModalNavigationDrawerContent
import com.example.Healsenior.ui.navigation.PermanentNavigationDrawerContent
import com.example.Healsenior.ui.navigation.ReplyBottomNavigationBar
import com.example.Healsenior.ui.navigation.ReplyNavigationActions
import com.example.Healsenior.ui.navigation.ReplyNavigationRail
import com.example.Healsenior.ui.navigation.ReplyRoute
import com.example.Healsenior.ui.navigation.ReplyTopLevelDestination
import com.example.Healsenior.ui.utils.DevicePosture
import com.example.Healsenior.ui.utils.ReplyNavigationContentPosition
import com.example.Healsenior.ui.utils.ReplyNavigationType
import com.example.Healsenior.ui.utils.isBookPosture
import com.example.Healsenior.ui.utils.isSeparating
import com.example.Healsenior.workoutScreen.WorkOutScreen
import kotlinx.coroutines.launch


@Composable
fun ReplyApp(
    windowSize: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    loginViewModel: LoginViewModel,
    googleSignInLauncher : ActivityResultLauncher<Intent>,
) {
    /**
     * This will help us select type of navigation and content type depending on window size and
     * fold state of the device.
     */
    val navigationType: ReplyNavigationType

    /**
     * We are using display's folding features to map the device postures a fold is in.
     * In the state of folding device If it's half fold in BookPosture we want to avoid content
     * at the crease/hinge
     */
    val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()

    val foldingDevicePosture = when {
        isBookPosture(foldingFeature) ->
            DevicePosture.BookPosture(foldingFeature.bounds)

        isSeparating(foldingFeature) ->
            DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

        else -> DevicePosture.NormalPosture
    }

    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = ReplyNavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = if (foldingDevicePosture is DevicePosture.BookPosture) {
                ReplyNavigationType.NAVIGATION_RAIL
            } else {
                ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
            }
        }
        else -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
        }
    }

    /**
     * Content inside Navigation Rail/Drawer can also be positioned at top, bottom or center for
     * ergonomics and reachability depending upon the height of the device.
     */
    val navigationContentPosition = when (windowSize.heightSizeClass) {
        WindowHeightSizeClass.Compact -> {
            ReplyNavigationContentPosition.TOP
        }
        WindowHeightSizeClass.Medium,
        WindowHeightSizeClass.Expanded -> {
            ReplyNavigationContentPosition.CENTER
        }
        else -> {
            ReplyNavigationContentPosition.TOP
        }
    }


    //로그인 상태 확인
    val context = LocalContext.current
    var isLoggedIn by remember { mutableStateOf(checkLoginState(context)) }

    if(isLoggedIn){
        ReplyNavigationWrapper(
            navigationType = navigationType,
            navigationContentPosition = navigationContentPosition,
            loginViewModel = loginViewModel
        )
    }
    else{
        LoginScreen(
            onGoogleSignInClick = {
                val signInIntent = loginViewModel.getGoogleSignInIntent()
                googleSignInLauncher.launch(signInIntent)
            }
        )
    }
}

@Composable
private fun ReplyNavigationWrapper(
    navigationType: ReplyNavigationType,
    navigationContentPosition: ReplyNavigationContentPosition,
    loginViewModel: LoginViewModel
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        ReplyNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route ?: ReplyRoute.Workout

    if (navigationType == ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        // TODO check on custom width of PermanentNavigationDrawer: b/232495216
        PermanentNavigationDrawer(drawerContent = {
            PermanentNavigationDrawerContent(
                selectedDestination = selectedDestination,
                navigationContentPosition = navigationContentPosition,
                navigateToTopLevelDestination = navigationActions::navigateTo,
            )
        }) {
            ReplyAppContent(
                navigationType = navigationType,
                navigationContentPosition = navigationContentPosition,
                navController = navController,
                selectedDestination = selectedDestination,
                navigateToTopLevelDestination = navigationActions::navigateTo,
                loginViewModel = loginViewModel
            )
        }
    } else {
        ModalNavigationDrawer(
            drawerContent = {
                ModalNavigationDrawerContent(
                    selectedDestination = selectedDestination,
                    navigationContentPosition = navigationContentPosition,
                    navigateToTopLevelDestination = navigationActions::navigateTo,
                    onDrawerClicked = {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            },
            drawerState = drawerState
        ) {
            ReplyAppContent(
                navigationType = navigationType,
                navigationContentPosition = navigationContentPosition,
                navController = navController,
                selectedDestination = selectedDestination,
                navigateToTopLevelDestination = navigationActions::navigateTo,
                loginViewModel = loginViewModel,
            ) {
                scope.launch {
                    drawerState.open()
                }
            }
        }
    }
}

@Composable
fun ReplyAppContent(
    modifier: Modifier = Modifier,
    navigationType: ReplyNavigationType,
    navigationContentPosition: ReplyNavigationContentPosition,
    navController: NavHostController,
    selectedDestination: String,
    loginViewModel: LoginViewModel,
    navigateToTopLevelDestination: (ReplyTopLevelDestination) -> Unit,
    onDrawerClicked: () -> Unit = {},
) {
    Row(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(visible = navigationType == ReplyNavigationType.NAVIGATION_RAIL) {
            ReplyNavigationRail(
                selectedDestination = selectedDestination,
                navigationContentPosition = navigationContentPosition,
                navigateToTopLevelDestination = navigateToTopLevelDestination,
                onDrawerClicked = onDrawerClicked,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
        ) {
            ReplyNavHost(
                navController = navController,
                modifier = Modifier.weight(1f),
                loginViewModel = loginViewModel
            )
            AnimatedVisibility(visible = navigationType == ReplyNavigationType.BOTTOM_NAVIGATION) {
                ReplyBottomNavigationBar(
                    selectedDestination = selectedDestination,
                    navigateToTopLevelDestination = navigateToTopLevelDestination
                )
            }
        }
    }
}

@Composable
private fun ReplyNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewModel: LoginViewModel
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ReplyRoute.Workout,
    ) {
        composable(ReplyRoute.Workout) {
            WorkOutScreen()
        }
        composable(ReplyRoute.Record) {
            RecordScreen(loginViewModel=loginViewModel)
        }
        composable(ReplyRoute.Community) {
            MainCommunityScreen()
        }
        composable(ReplyRoute.Mypage) {
            MyPageScreen(loginViewModel=loginViewModel)
        }
    }
}