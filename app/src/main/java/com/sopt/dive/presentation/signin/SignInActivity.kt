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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import com.sopt.dive.R
import com.sopt.dive.core.designsystem.theme.DiveTheme
import com.sopt.dive.core.designsystem.theme.PurpleGrey80
import com.sopt.dive.core.util.Keys
import com.sopt.dive.presentation.main.MainActivity
import com.sopt.dive.presentation.signup.SignUpActivity

class SignInActivity : ComponentActivity() {
    private var validateUserId: String? = null
    private var validatePassword: String? = null
    private var validateNickname: String? = null
    private var validateMbti: String? = null
    private val launcher = registerForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            validateUserId = data?.getStringExtra(Keys.USER_ID)?:""
            validatePassword = data?.getStringExtra(Keys.USER_PASSWORD)?:""
            validateNickname = data?.getStringExtra(Keys.USER_NICKNAME)?:""
            validateMbti = data?.getStringExtra(Keys.USER_MBTI)?:""
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = PurpleGrey80)
                ) { innerPadding ->
                    val context = LocalContext.current
                    val focusManager = LocalFocusManager.current

                    var userId by remember { mutableStateOf("") }
                    var password by remember { mutableStateOf("") }

                    var isPasswordVisible by remember { mutableStateOf(false) }

                    SignInRoute(
                        userId = userId,
                        onUserIdChanged = { userId = it },
                        onKeyboardNext = KeyboardActions(
                            onNext = { focusManager.moveFocus(FocusDirection.Next) }
                        ),
                        password = password,
                        onPasswordChanged = { password = it },
                        onKeyboardDone = KeyboardActions(
                            onDone = {
                                focusManager.clearFocus()
                            }
                        ),
                        isPasswordVisible = isPasswordVisible,
                        onIconClick = { isPasswordVisible = !isPasswordVisible },
                        onSignInClick = {
                            if (userId == validateUserId && password == validatePassword) {
                                val intent = Intent(context, MainActivity::class.java)
                                intent.putExtra(Keys.USER_ID, userId)
                                intent.putExtra(Keys.USER_PASSWORD, password)
                                intent.putExtra(Keys.USER_NICKNAME, validateNickname)
                                intent.putExtra(Keys.USER_MBTI, validateMbti)
                                context.startActivity(intent)
                                Toast.makeText(context, "로그인에 성공했습니다.", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(context, "로그인에 실패했습니다.", Toast.LENGTH_SHORT).show()
                            }
                        },
                        onSignUpClick = {
                            val intent = Intent(context, SignUpActivity::class.java)
                            launcher.launch(intent)                        },
                        iconId = if (isPasswordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
