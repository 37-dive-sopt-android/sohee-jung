package com.sopt.dive.presentation.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.core.designsystem.component.DiveSoptButton
import com.sopt.dive.core.designsystem.component.DiveSoptTextField
import com.sopt.dive.core.util.noRippleClickable

@Composable
fun SignUpRoute(
    userId: String,
    onUserIdChanged: (String) -> Unit,
    onKeyboardNext: KeyboardActions,
    password: String,
    onPasswordChanged: (String) -> Unit,
    isPasswordVisible: Boolean,
    onIconClick: () -> Unit,
    nickname: String,
    onNicknameChanged: (String) -> Unit,
    mbti: String,
    onMbtiChanged: (String) -> Unit,
    onKeyboardDone: KeyboardActions,
    onSignUpClick: () -> Unit,
    iconId: Int,
    modifier: Modifier = Modifier
) {
    SignUpScreen(
        userId = userId,
        onUserIdChanged = onUserIdChanged,
        onKeyboardNext = onKeyboardNext,
        password = password,
        onPasswordChanged = onPasswordChanged,
        isPasswordVisible = isPasswordVisible,
        onIconClick = onIconClick,
        nickname = nickname,
        onNicknameChanged = onNicknameChanged,
        mbti = mbti,
        onMbtiChanged = onMbtiChanged,
        onKeyboardDone = onKeyboardDone,
        onSignUpClick = onSignUpClick,
        modifier = modifier,
        iconId = iconId
    )
}

@Composable
private fun SignUpScreen(
    userId: String,
    onUserIdChanged: (String) -> Unit,
    onKeyboardNext: KeyboardActions,
    password: String,
    onPasswordChanged: (String) -> Unit,
    isPasswordVisible: Boolean,
    onIconClick: () -> Unit,
    nickname: String,
    onNicknameChanged: (String) -> Unit,
    mbti: String,
    onMbtiChanged: (String) -> Unit,
    onKeyboardDone: KeyboardActions,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconId: Int = R.drawable.ic_visibility_off,
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
            keyboardActions = onKeyboardNext,
            placeholder = "아이디를 입력해주세요"
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "PW",
            color = Color.Black,
            fontSize = 30.sp
        )

        DiveSoptTextField(
            value = password,
            onValueChanged = onPasswordChanged,
            keyboardActions = onKeyboardNext,
            placeholder = "비밀번호를 입력해주세요",
            keyboardType = KeyboardType.Password,
            isPasswordVisible = isPasswordVisible,
            trailingIcon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = iconId),
                    contentDescription = "",
                    modifier = Modifier
                        .noRippleClickable(onClick = onIconClick),
                    tint = Color.LightGray
                )
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        UserInfoInput(
            userInfoInputSection = "NICKNAME",
            userInfoInputDescription = nickname,
            onUserInfoInputChanged = onNicknameChanged,
            keyboardActions = onKeyboardDone,
            placeholder = "닉네임을 입력해주세요"
        )

        Spacer(modifier = Modifier.height(20.dp))

        UserInfoInput(
            userInfoInputSection = "MBTI",
            userInfoInputDescription = mbti,
            onUserInfoInputChanged = onMbtiChanged,
            keyboardActions = onKeyboardDone,
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
    keyboardActions: KeyboardActions,
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
            keyboardActions = keyboardActions,
            placeholder = placeholder,
            imeAction = imeAction
        )
    }
}
