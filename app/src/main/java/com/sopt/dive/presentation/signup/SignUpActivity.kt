package com.sopt.dive.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.lifecycleScope
import com.sopt.dive.core.designsystem.ui.theme.DiveTheme
import com.sopt.dive.core.designsystem.ui.theme.PurpleGrey80
import com.sopt.dive.core.util.Keys
import com.sopt.dive.core.util.validateErrorMessage
import com.sopt.dive.data.UserInfoDatastore
import com.sopt.dive.data.userDatastore
import kotlinx.coroutines.launch

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                val snackbarHostState = remember { SnackbarHostState() }

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = PurpleGrey80),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
                ) { innerPadding ->
                    val context = LocalContext.current
                    val focusManager = LocalFocusManager.current

                    var userId by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }
                    var nickname by remember { mutableStateOf("") }
                    var mbti by remember { mutableStateOf("") }

                    var isPasswordVisible by remember { mutableStateOf(false) }

                    SignUpRoute(
                        userId = userId,
                        onUserIdChanged = { userId = it },
                        keyboardActions = KeyboardActions(
                            onDone = { focusManager.clearFocus() },
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        password = password,
                        onPasswordChanged = { password = it },
                        isPasswordVisible = isPasswordVisible,
                        onIconClick = { isPasswordVisible = !isPasswordVisible },
                        nickname = nickname,
                        onNicknameChanged = { nickname = it },
                        mbti = mbti,
                        onMbtiChanged = { mbti = it },
                        onSignUpClick = {
                            val errorMessage = validateErrorMessage(
                                userId = userId,
                                password = password,
                                nickname = nickname,
                                mbti = mbti
                            )

                            if (errorMessage == null) {
                                lifecycleScope.launch {
                                    context.userDatastore.edit { prefs ->
                                        prefs[UserInfoDatastore.userId] = userId
                                        prefs[UserInfoDatastore.password] = password
                                        prefs[UserInfoDatastore.nickname] = nickname
                                        prefs[UserInfoDatastore.mbti] = mbti
                                    }
                                    val result = Intent().apply {
                                        putExtra(Keys.USER_ID, userId)
                                        putExtra(Keys.USER_PASSWORD, password)
                                        putExtra(Keys.USER_NICKNAME, nickname)
                                        putExtra(Keys.USER_MBTI, mbti)
                                    }
                                    Toast.makeText(context, "회원가입에 성공했습니다.", Toast.LENGTH_SHORT)
                                        .show()
                                    setResult(RESULT_OK, result)
                                    finish()
                                }

                            } else {
                                lifecycleScope.launch {
                                    snackbarHostState.showSnackbar(message = errorMessage)
                                }
                            }
                        },
                        paddingValues = innerPadding
                    )
                }
            }
        }
    }
}
