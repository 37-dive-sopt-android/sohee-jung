package com.sopt.dive.core

import android.content.Context
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.sopt.dive.core.util.Keys


val Context.userDatastore by preferencesDataStore(name = "user_prefs")

object UserInfoDatastore {
    val userId = stringPreferencesKey(Keys.USER_ID)
    val password = stringPreferencesKey(Keys.USER_PASSWORD)
    val nickname = stringPreferencesKey(Keys.USER_NICKNAME)
    val mbti = stringPreferencesKey(Keys.USER_MBTI)
}
