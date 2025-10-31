package com.sopt.dive.presentation.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import com.sopt.dive.presentation.home.navigation.homeGraph
import com.sopt.dive.presentation.my.navigation.myGraph
import com.sopt.dive.presentation.search.navigation.searchGraph
import com.sopt.dive.presentation.signin.navigation.signInGraph
import com.sopt.dive.presentation.signup.navigation.signUpGraph
import com.sopt.dive.presentation.splash.navigation.splashGraph

@Composable
fun MainNavHost(
    navigator: MainNavigator,
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navigator.navController,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
        startDestination = navigator.startDestination,
        modifier = modifier

    ) {
        val clearStackNavOptions = navOptions {
            popUpTo(0) { inclusive = true }
            launchSingleTop = true
            restoreState = false
        }

        val keepStackNavOptions = navOptions {

        }

        splashGraph(
            onNavigateToHome = { navigator.navigateToHome(clearStackNavOptions) },
            onNavigateToSignIn = { navigator.navigateToSignIn(clearStackNavOptions) },
            paddingValues = paddingValues
        )

        signInGraph(
            paddingValues = paddingValues,
            navigateToHome = { navigator.navigateToHome(clearStackNavOptions) },
            navigateToSignUp = { navigator.navigateToSignUp(keepStackNavOptions) }
        )

        signUpGraph(
            onNavigateToSignIn = { navigator.navigateToSignIn(clearStackNavOptions) },
            paddingValues = paddingValues
        )

        homeGraph(
            paddingValues = paddingValues
        )

        searchGraph(
            paddingValues = paddingValues
        )

        myGraph(
            paddingValues = paddingValues,
            onNavigateToSignIn = { navigator.navigateToSignIn(clearStackNavOptions) }
        )
    }
}
