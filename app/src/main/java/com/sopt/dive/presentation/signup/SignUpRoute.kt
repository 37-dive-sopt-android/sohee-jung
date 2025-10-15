package com.sopt.dive.presentation.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.designsystem.component.button.DiveSoptButton
import com.sopt.dive.core.designsystem.component.textfield.DiveSoptPasswordTextField
import com.sopt.dive.core.designsystem.component.textfield.DiveSoptTextField

@Composable
fun SignUpRoute(
    userId: String,
    onUserIdChanged: (String) -> Unit,
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
    SignUpScreen(
        userId = userId,
        onUserIdChanged = onUserIdChanged,
        password = password,
        onPasswordChanged = onPasswordChanged,
        isPasswordVisible = isPasswordVisible,
        onIconClick = onIconClick,
        nickname = nickname,
        onNicknameChanged = onNicknameChanged,
        mbti = mbti,
        onMbtiChanged = onMbtiChanged,
        onSignUpClick = onSignUpClick,
        modifier = modifier,
    )
}

@Composable
private fun SignUpScreen(
    userId: String,
    onUserIdChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    isPasswordVisible: Boolean,
    onIconClick: () -> Unit,
    nickname: String,
    onNicknameChanged: (String) -> Unit,
    mbti: String,
    onMbtiChanged: (String) -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 40.dp)
    ) {
        Text(
            text = "SIGN UP",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(60.dp))

        UserInfoInput(
            userInfoInputSection = "ID",
            userInfoInputDescription = userId,
            onUserInfoInputChanged = onUserIdChanged,
            placeholder = "아이디를 입력해주세요"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "PW",
            color = Color.Black,
            fontSize = 30.sp
        )

        DiveSoptPasswordTextField(
            password = password,
            onPasswordChanged = onPasswordChanged,
            onIconClick = onIconClick,
            isPasswordVisible = isPasswordVisible
        )

        Spacer(modifier = Modifier.height(20.dp))

        UserInfoInput(
            userInfoInputSection = "NICKNAME",
            userInfoInputDescription = nickname,
            onUserInfoInputChanged = onNicknameChanged,
            placeholder = "닉네임을 입력해주세요"
        )

        Spacer(modifier = Modifier.height(20.dp))

        UserInfoInput(
            userInfoInputSection = "MBTI",
            userInfoInputDescription = mbti,
            onUserInfoInputChanged = onMbtiChanged,
            placeholder = "엠비티아이를 입력해주세요",
            imeAction = ImeAction.Done
        )

        Spacer(modifier = Modifier.weight(1f))

        DiveSoptButton(
            buttonText = "회원가입하기",
            onButtonClick = onSignUpClick
        )
    }
}

@Composable
private fun UserInfoInput(
    userInfoInputSection: String,
    userInfoInputDescription: String,
    onUserInfoInputChanged: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = userInfoInputSection,
            color = Color.Black,
            fontSize = 30.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        DiveSoptTextField(
            value = userInfoInputDescription,
            onValueChanged = onUserInfoInputChanged,
            placeholder = placeholder,
            imeAction = imeAction
        )
    }
}
