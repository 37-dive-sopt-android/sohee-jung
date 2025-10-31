package com.sopt.dive.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.designsystem.LocalAppSnackbarHostState
import com.sopt.dive.core.designsystem.component.button.DiveSoptButton
import com.sopt.dive.core.designsystem.component.textfield.DiveSoptPasswordTextField
import com.sopt.dive.core.util.conditionalImePadding
import com.sopt.dive.data.UserPrefs
import com.sopt.dive.domain.CheckSignUpResult
import com.sopt.dive.domain.Result
import com.sopt.dive.presentation.signup.component.UserInfoInput
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    onSignUpClick: () -> Unit,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current.applicationContext
    val prefs = remember { UserPrefs(context) }
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val snackbar = LocalAppSnackbarHostState.current

    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    var isPasswordVisible by remember { mutableStateOf(false) }

    SignUpScreen(
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
            scope.launch {
                when (val r= CheckSignUpResult(prefs = prefs, userId = userId, password = password, nickname = nickname, mbti = mbti)) {
                    is Result.Success -> {
                        Toast.makeText(context, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()
                        onSignUpClick()
                    }
                    is Result.Failure -> {
                        snackbar.showSnackbar(r.message)
                    }
                }
            }
        },
        paddingValues = paddingValues
    )
}

@Composable
private fun SignUpScreen(
    paddingValues: PaddingValues,
    userId: String,
    onUserIdChanged: (String) -> Unit,
    keyboardActions: KeyboardActions,
    password: String,
    onPasswordChanged: (String) -> Unit,
    isPasswordVisible: Boolean,
    onIconClick: () -> Unit,
    nickname: String,
    onNicknameChanged: (String) -> Unit,
    mbti: String,
    onMbtiChanged: (String) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = paddingValues.calculateTopPadding() + 30.dp)
            .conditionalImePadding(bottom = 30.dp)
    ) {
        Text(
            text = "SIGN UP",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(20.dp))

        UserInfoInput(
            userInfoInputSection = "ID",
            userInfoInputDescription = userId,
            onUserInfoInputChanged = onUserIdChanged,
            placeholder = "아이디를 입력해주세요",
            keyboardActions = keyboardActions
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "PW",
            color = Color.Black,
            fontSize = 30.sp
        )

        DiveSoptPasswordTextField(
            password = password,
            onPasswordChanged = onPasswordChanged,
            onIconClick = onIconClick,
            isPasswordVisible = isPasswordVisible,
            keyboardActions = keyboardActions
        )

        Spacer(modifier = Modifier.height(10.dp))

        UserInfoInput(
            userInfoInputSection = "NICKNAME",
            userInfoInputDescription = nickname,
            onUserInfoInputChanged = onNicknameChanged,
            placeholder = "닉네임을 입력해주세요",
            keyboardActions = keyboardActions
        )

        Spacer(modifier = Modifier.height(10.dp))

        UserInfoInput(
            userInfoInputSection = "MBTI",
            userInfoInputDescription = mbti,
            onUserInfoInputChanged = onMbtiChanged,
            placeholder = "엠비티아이를 입력해주세요",
            imeAction = ImeAction.Done,
            keyboardActions = keyboardActions
        )

        Spacer(modifier = Modifier.weight(1f))

        DiveSoptButton(
            buttonText = "회원가입하기",
            onButtonClick = onSignUpClick
        )
    }
}
