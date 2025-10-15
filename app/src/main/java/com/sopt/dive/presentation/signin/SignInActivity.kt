package com.sopt.dive.presentation.signin

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.sopt.dive.data.SessionStateStore
import com.sopt.dive.data.UserInfoDatastore.mbti
import com.sopt.dive.data.UserInfoDatastore.nickname
import com.sopt.dive.data.UserInfoDatastore.password
import com.sopt.dive.data.UserInfoDatastore.userId
import com.sopt.dive.core.designsystem.ui.theme.DiveTheme
import com.sopt.dive.core.designsystem.ui.theme.PurpleGrey80
import com.sopt.dive.data.userDatastore
import com.sopt.dive.core.util.Keys
import com.sopt.dive.presentation.main.MainActivity
import com.sopt.dive.presentation.signup.SignUpActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SignInActivity : ComponentActivity() {
    private var validateUserId: String? = null
    private var validatePassword: String? = null
    private var validateNickname: String? = null
    private var validateMbti: String? = null
    private val launcher =
        registerForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                validateUserId = data?.getStringExtra(Keys.USER_ID) ?: ""
                validatePassword = data?.getStringExtra(Keys.USER_PASSWORD) ?: ""
                validateNickname = data?.getStringExtra(Keys.USER_NICKNAME) ?: ""
                validateMbti = data?.getStringExtra(Keys.USER_MBTI) ?: ""
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            val prefs = applicationContext.userDatastore.data.first()
            val savedId = prefs[userId] ?: " "
            val savedPassword = prefs[password] ?: ""
            val savedNickname = prefs[nickname] ?: ""
            val savedMbti = prefs[mbti] ?: ""
            var isLoggedIn = prefs[SessionStateStore.isLoggedIn] ?: false

            if (isLoggedIn) {
                ActivityToMain(this@SignInActivity, savedId, savedPassword, savedNickname, savedMbti)
                finish()
            } else {
                setContent {
                    DiveTheme {
                        val scope = rememberCoroutineScope()

                        Scaffold(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = PurpleGrey80)
                        ) { innerPadding ->
                            val context = LocalContext.current

                            var userId by remember { mutableStateOf("") }
                            var password by remember { mutableStateOf("") }

                            var isPasswordVisible by remember { mutableStateOf(false) }

                            SignInRoute(
                                userId = userId,
                                onUserIdChanged = { userId = it },
                                password = password,
                                onPasswordChanged = { password = it },
                                isPasswordVisible = isPasswordVisible,
                                onIconClick = { isPasswordVisible = !isPasswordVisible },
                                onSignInClick = {
                                    if (userId == validateUserId && password == validatePassword) {
                                        scope.launch {
                                            context.userDatastore.edit {
                                                it[SessionStateStore.isLoggedIn] = true
                                            }
                                        }
                                        ActivityToMain(this@SignInActivity, userId, password, validateNickname!!, validateMbti!!)
                                        finish()
                                        Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_SHORT)
                                            .show()
                                    } else {
                                        Toast.makeText(context, "로그인에 실패했습니다.", Toast.LENGTH_SHORT)
                                            .show()
                                    }
                                },
                                onSignUpClick = {
                                    val intent = Intent(context, SignUpActivity::class.java)
                                    launcher.launch(intent)
                                },
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

private fun ActivityToMain(
    context: Activity,
    userId: String,
    password: String,
    nickname: String,
    mbti: String
) {
    val intent = Intent(context, MainActivity::class.java).apply {
        putExtra(Keys.USER_ID, userId)
        putExtra(Keys.USER_PASSWORD, password)
        putExtra(Keys.USER_NICKNAME, nickname)
        putExtra(Keys.USER_MBTI, mbti)
    }
    context.startActivity(intent)
}
