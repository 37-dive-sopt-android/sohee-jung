package com.sopt.dive.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sopt.dive.core.designsystem.ui.theme.DiveTheme
import com.sopt.dive.core.designsystem.ui.theme.PurpleGrey80
import com.sopt.dive.core.util.Keys

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userId = intent.getStringExtra(Keys.USER_ID) ?: ""
        val password = intent.getStringExtra(Keys.USER_PASSWORD) ?: ""
        val nickname = intent.getStringExtra(Keys.USER_NICKNAME) ?: ""
        val mbti = intent.getStringExtra(Keys.USER_MBTI) ?: ""

        enableEdgeToEdge()
        setContent {
            DiveTheme {
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
