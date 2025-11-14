package com.sopt.dive.presentation.signup.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sopt.dive.core.navigation.Route
import com.sopt.dive.presentation.signup.SignUpRoute
import kotlinx.serialization.Serializable

fun NavController.navigateToSignUp(navOptions: NavOptions? = null){
    navigate(SignUp, navOptions)
}

fun NavGraphBuilder.signUpGraph(
    onNavigateToSignIn: () -> Unit,
    paddingValues: PaddingValues
){
    composable<SignUp> {
        SignUpRoute(
            navigateToSignIn = onNavigateToSignIn,
            paddingValues = paddingValues

        )
    }
}

@Serializable
data object SignUp : Route
