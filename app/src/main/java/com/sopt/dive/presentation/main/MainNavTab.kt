package com.sopt.dive.presentation.main

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import com.sopt.dive.R
import com.sopt.dive.core.navigation.MainTabRoute
import com.sopt.dive.presentation.home.navigation.Home
import com.sopt.dive.presentation.my.navigation.My
import com.sopt.dive.presentation.search.navigation.Search

enum class MainNavTab(
    @DrawableRes val icon: Int,
    val contentDescription: String,
    val route: MainTabRoute
) {
    HOME(
        icon = R.drawable.ic_home,
        contentDescription = "홈",
        route = Home
    ),

    SEARCH(
        icon = R.drawable.ic_search,
        contentDescription = "검색",
        route = Search
    ),
    MY(
        icon = R.drawable.ic_my,
        contentDescription = "마이",
        route = My
    );

    companion object {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainNavTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (MainTabRoute) -> Boolean): Boolean {
            return entries.any { predicate(it.route) }
        }
    }
}
