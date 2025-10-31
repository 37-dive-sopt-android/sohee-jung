package com.sopt.dive.presentation.my.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.MainTabRoute
import com.sopt.dive.presentation.my.MyRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToMy(navOptions: NavOptions? = null) {
    navigate(My, navOptions)
}

fun NavGraphBuilder.myGraph(
    paddingValues: PaddingValues,
    onNavigateToSignIn: () -> Unit
) {
    composable<My> {
        MyRoute(
            paddingValues = paddingValues,
            onNavigateToSignIn = onNavigateToSignIn
        )
    }
}

@Serializable
data object My : MainTabRoute
