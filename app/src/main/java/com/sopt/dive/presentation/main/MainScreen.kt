package com.sopt.dive.presentation.main

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.sopt.dive.core.designsystem.LocalAppSnackbarHostState
import com.sopt.dive.presentation.main.component.MainBottomBar
import kotlinx.collections.immutable.toImmutableList

@Composable
fun MainScreen(
    navigator: MainNavigator = rememberMainNavigator()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val currentTab = navigator.currentTab
    val showBottomBar = navigator.showBottomBar()

    CompositionLocalProvider(
        LocalAppSnackbarHostState provides snackbarHostState
    ) {
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                MainBottomBar(
                    visible = showBottomBar,
                    tabs = MainNavTab.entries.toImmutableList(),
                    currentTab = currentTab,
                    onTabClicked = { selectedTab -> navigator.navigate(tab = selectedTab) }
                )
            }
        ) { innerPadding ->
            MainNavHost(
                navigator = navigator,
                paddingValues = innerPadding
            )
        }
    }
}
