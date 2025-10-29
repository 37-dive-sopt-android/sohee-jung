package com.sopt.dive.data

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.sopt.dive.core.util.Keys

object SessionStateDataStore {
    val isLoggedIn = booleanPreferencesKey(Keys.IS_LOGGED_IN)
}
