package com.sopt.dive.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.sopt.dive.presentation.home.navigation.navigateToHome
import com.sopt.dive.presentation.my.navigation.navigateToMy
import com.sopt.dive.presentation.search.navigation.navigateToSearch
import com.sopt.dive.presentation.signin.navigation.navigateToSignIn
import com.sopt.dive.presentation.signup.navigation.navigateToSignUp
import com.sopt.dive.presentation.splash.navigation.Splash
import com.sopt.dive.presentation.splash.navigation.navigateToSplash

class MainNavigator(
    val navController: NavHostController
) {
    val startDestination = Splash

    val currentTab: MainNavTab?
        @Composable get() = MainNavTab.find {
            currentDestination?.hasRoute(it::class) == true
        }

    private val currentDestination: NavDestination?
        @Composable get() = navController.currentBackStackEntryAsState().value?.destination

    @Composable
    fun showBottomBar() = MainNavTab.contains {
        currentDestination?.hasRoute(it::class) == true
    }

    fun navigate(tab: MainNavTab) {
        val navOptions = navOptions {
            navController.currentDestination?.route?.let {
                popUpTo(it) {
                    inclusive = true
                    saveState = true
                }
            }
            launchSingleTop = true
            restoreState = true
        }
        when (tab) {
            MainNavTab.HOME -> navigateToHome(navOptions)
            MainNavTab.SEARCH -> navigateToSearch(navOptions)
            MainNavTab.MY -> navigateToMy(navOptions)
        }
    }

    fun navigateToSplash(navOptions: NavOptions) {
        navController.navigateToSplash(navOptions)
    }

    fun navigateToSignIn(navOptions: NavOptions) {
        navController.navigateToSignIn(navOptions)
    }

    fun navigateToSignUp(navOptions: NavOptions) {
        navController.navigateToSignUp(navOptions)
    }

    fun navigateToHome(navOptions: NavOptions) {
        navController.navigateToHome(navOptions)
    }

    fun navigateToSearch(navOptions: NavOptions) {
        navController.navigateToSearch(navOptions)
    }

    fun navigateToMy(navOptions: NavOptions) {
        navController.navigateToMy(navOptions)
    }
}

@Composable
fun rememberMainNavigator(
    navController: NavHostController = rememberNavController()
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
