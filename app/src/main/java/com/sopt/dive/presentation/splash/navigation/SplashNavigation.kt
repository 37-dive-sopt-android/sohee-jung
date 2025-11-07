package com.sopt.dive.presentation.splash.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.splash.SplashRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    navigate(Splash, navOptions)
}

fun NavGraphBuilder.splashGraph(
    onNavigateToHome: () -> Unit,
    onNavigateToSignIn: () -> Unit,
    paddingValues: PaddingValues
) {
    composable<Splash> {
        SplashRoute(
            onNavigateToHome = onNavigateToHome,
            onNavigateToSignIn = onNavigateToSignIn,
            paddingValues = paddingValues
        )
    }
}

@Serializable
data object Splash : Route
