package com.sopt.dive.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sopt.dive.core.util.Keys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map


val Context.userDatastore by preferencesDataStore(name = "user_prefs")

object UserInfoDatastore {
    val userId = stringPreferencesKey(Keys.USER_ID)
    val password = stringPreferencesKey(Keys.USER_PASSWORD)
    val nickname = stringPreferencesKey(Keys.USER_NICKNAME)
    val mbti = stringPreferencesKey(Keys.USER_MBTI)
}

class UserPrefs(private val context: Context) {
    val profileFlow: Flow<UserInfo> = context.userDatastore.data.map { p ->
        UserInfo(
            userId = p[UserInfoDatastore.userId] ?: "",
            password = p[UserInfoDatastore.password] ?: "",
            nickname = p[UserInfoDatastore.nickname] ?: "",
            mbti = p[UserInfoDatastore.mbti] ?: "",
            isLoggedIn = p[SessionStateDataStore.isLoggedIn] ?: false
        )
    }

    suspend fun saveProfile(profile: UserInfo) {
        context.userDatastore.edit { p ->
            p[UserInfoDatastore.userId] = profile.userId
            p[UserInfoDatastore.password] = profile.password
            p[UserInfoDatastore.nickname] = profile.nickname
            p[UserInfoDatastore.mbti] = profile.mbti
        }
    }

    suspend fun setUserId(userId: Long) {
        context.userDatastore.edit { p ->
            p[UserInfoDatastore.userId] = userId.toString()
        }
    }

    suspend fun getUserId(): Long? {
        return context.userDatastore.data
            .map { p -> p[UserInfoDatastore.userId] }
            .first()               // String? 하나 꺼내고
            ?.toLongOrNull()       // Long? 으로 변환
    }

    suspend fun setLoggedIn(isLoggedIn: Boolean){
        context.userDatastore.edit { p ->
            p[SessionStateDataStore.isLoggedIn] = isLoggedIn
        }
    }

    suspend fun clear() {
        context.userDatastore.edit { it.clear() }
    }
}
