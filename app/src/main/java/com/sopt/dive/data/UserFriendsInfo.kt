package com.sopt.dive.data

import androidx.compose.runtime.Immutable

@Immutable
data class UserFriendsInfo(
    val friendImage: Int,
    val friendName: String,
    val friendSaying: String
)
