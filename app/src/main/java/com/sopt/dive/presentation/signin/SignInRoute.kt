package com.sopt.dive.presentation.signin

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.designsystem.component.DiveSoptButton
import com.sopt.dive.core.designsystem.component.DiveSoptTextField

@Composable
fun SignInRoute(
    userId: String,
    onUserIdChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    isPasswordVisible: Boolean,
    onIconClick: () -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    SignInScreen(
        userId = userId,
        onUserIdChanged = onUserIdChanged,
        password = password,
        onPasswordChanged = onPasswordChanged,
        isPasswordVisible = isPasswordVisible,
        onIconClick = onIconClick,
        onSignInClick = onSignInClick,
        onSignUpClick = onSignUpClick,
        modifier = modifier
    )
}

@Composable
private fun SignInScreen(
    userId: String,
    onUserIdChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit,
    isPasswordVisible: Boolean,
    onIconClick: () -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .padding(horizontal = 20.dp, vertical = 30.dp)
    ) {
        Text(
            text = "Welcome To Sopt",
            modifier = Modifier.fillMaxWidth(),
            color = Color.Black,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "ID",
            color = Color.Black,
            fontSize = 30.sp
        )

        DiveSoptTextField(
            value = userId,
            onValueChanged = onUserIdChanged,
            placeholder = "아이디를 입력해주세요"
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "PW",
            color = Color.Black,
            fontSize = 30.sp
        )

        DiveSoptTextField(
            value = password,
            onValueChanged = onPasswordChanged,
            placeholder = "비밀번호를 입력해주세요",
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done,
            isPasswordVisible = isPasswordVisible,
            onIconClick = onIconClick
        )

        Spacer(modifier = Modifier.weight(1f))

        DiveSoptButton(
            buttonText = "Welcome To SOPT",
            onButtonClick = onSignInClick
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "회원가입하기",
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onSignUpClick),
            color = Color.Gray,
            fontSize = 15.sp,
            textAlign = TextAlign.Center
        )
    }
}
