package com.sopt.dive.presentation.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.dive.data.UserInfo
import com.sopt.dive.data.UserPrefs

@Composable
fun SplashRoute(
    onNavigateToHome: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    paddingValues: PaddingValues
){
    val appCtx = LocalContext.current.applicationContext
    val prefs = remember { UserPrefs(appCtx) }
    val profile by prefs.profileFlow.collectAsStateWithLifecycle(initialValue = UserInfo.EMPTY)

    LaunchedEffect(Unit) {
        if (profile.isLoggedIn) onNavigateToHome() else onNavigateToSignIn()
    }
    SplashScreen(
        paddingValues = paddingValues
    )
}

@Composable
fun SplashScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
    ) {
        Text(
            text = "스플래시 화면입니다"
        )
    }
}
