package com.sopt.dive.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.sopt.dive.core.UserInfoDatastore
import com.sopt.dive.core.designsystem.ui.theme.DiveTheme
import com.sopt.dive.core.designsystem.ui.theme.PurpleGrey80
import com.sopt.dive.core.userDatastore
import com.sopt.dive.core.util.Keys
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val initialUserId = intent.getStringExtra(Keys.USER_ID) ?: ""
        val initialPassword = intent.getStringExtra(Keys.USER_PASSWORD) ?: ""
        val initialNickname = intent.getStringExtra(Keys.USER_NICKNAME) ?: ""
        val initialMbti = intent.getStringExtra(Keys.USER_MBTI) ?: ""

        lifecycleScope.launch {
            applicationContext.userDatastore.edit { prefs ->
                if (initialUserId.isNotBlank()) {
                    prefs[UserInfoDatastore.userId] = initialUserId
                    prefs[UserInfoDatastore.password] = initialPassword
                    prefs[UserInfoDatastore.nickname] = initialNickname
                    prefs[UserInfoDatastore.mbti] = initialMbti
                }
            }
        }

        enableEdgeToEdge()
        setContent {
            DiveTheme {
                var userId by rememberSaveable { mutableStateOf(initialUserId) }
                var password by rememberSaveable { mutableStateOf(initialPassword) }
                var nickname by rememberSaveable { mutableStateOf(initialNickname) }
                var mbti by rememberSaveable { mutableStateOf(initialMbti) }

                LaunchedEffect(Unit) {
                    if (userId.isBlank()){
                        val prefs = applicationContext.userDatastore.data.first()
                        userId = prefs[UserInfoDatastore.userId].orEmpty()
                        password = prefs[UserInfoDatastore.password].orEmpty()
                        nickname = prefs[UserInfoDatastore.nickname].orEmpty()
                        mbti = prefs[UserInfoDatastore.mbti].orEmpty()
                    }
                }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = PurpleGrey80)
                ) { innerPadding ->
                    MainScreen(
                        nickname = nickname,
                        userId = userId,
                        password = password,
                        mbti = mbti,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
