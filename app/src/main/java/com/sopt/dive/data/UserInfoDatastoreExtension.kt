package com.sopt.dive.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

fun Context.userInfo(): Flow<UserInfo> = userDatastore.data.map {
    UserInfo(
        userId = it[UserInfoDatastore.userId] ?: "",
        password = it[UserInfoDatastore.password] ?: "",
        nickname = it[UserInfoDatastore.nickname] ?: "",
        mbti = it[UserInfoDatastore.mbti] ?: "",
        isLoggedIn = it[SessionStateDataStore.isLoggedIn] ?: false
    )
}.distinctUntilChanged()
