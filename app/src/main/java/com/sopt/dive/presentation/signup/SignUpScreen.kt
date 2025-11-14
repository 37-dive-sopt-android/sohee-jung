package com.sopt.dive.presentation.signup

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.core.designsystem.component.button.DiveSoptButton
import com.sopt.dive.core.designsystem.component.textfield.DiveSoptPasswordTextField
import com.sopt.dive.core.util.conditionalImePadding
import com.sopt.dive.presentation.signup.component.UserInfoInput

@Composable
fun SignUpRoute(
    navigateToSignIn: () -> Unit,
    paddingValues: PaddingValues,
    viewModel: SignUpViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SignUpSideEffect.NavigateToSignIn -> navigateToSignIn()
            }
        }
    }


    SignUpScreen(
        uiState = uiState,
        onUserIdChanged = viewModel::updateUserId,
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(FocusDirection.Next) }
        ),
        onPasswordChanged = viewModel::updatePassword,
        onIconClick = viewModel::onIconClicked,
        onNameChanged = viewModel::updateName,
        onEmailChanged = viewModel::updateEmail,
        onAgeChanged = viewModel::updateAge,
        onSignUpClick = viewModel::onSignUpClicked,
        paddingValues = paddingValues
    )
}

@Composable
private fun SignUpScreen(
    uiState: SignUpState,
    paddingValues: PaddingValues,
    onUserIdChanged: (String) -> Unit,
    keyboardActions: KeyboardActions,
    onPasswordChanged: (String) -> Unit,
    onIconClick: () -> Unit,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onAgeChanged: (String) -> Unit,
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
            userInfoInputDescription = uiState.userId,
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
            password = uiState.password,
            onPasswordChanged = onPasswordChanged,
            onIconClick = onIconClick,
            isPasswordVisible = uiState.isPasswordVisible,
            keyboardActions = keyboardActions
        )

        Spacer(modifier = Modifier.height(10.dp))

        UserInfoInput(
            userInfoInputSection = "NAME",
            userInfoInputDescription = uiState.name,
            onUserInfoInputChanged = onNameChanged,
            placeholder = "이름을 입력해주세요",
            keyboardActions = keyboardActions
        )

        Spacer(modifier = Modifier.height(10.dp))

        UserInfoInput(
            userInfoInputSection = "EMAIL",
            userInfoInputDescription = uiState.email,
            onUserInfoInputChanged = onEmailChanged,
            placeholder = "이메일을 입력해주세요",
            imeAction = ImeAction.Done,
            keyboardActions = keyboardActions
        )

        Spacer(modifier = Modifier.height(10.dp))

        UserInfoInput(
            userInfoInputSection = "AGE",
            userInfoInputDescription = uiState.age.toString(),
            onUserInfoInputChanged = onAgeChanged,
            placeholder = "나이를 입력해주세요",
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
