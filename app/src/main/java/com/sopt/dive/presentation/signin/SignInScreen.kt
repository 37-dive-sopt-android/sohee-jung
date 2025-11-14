package com.sopt.dive.presentation.signin

import androidx.compose.foundation.clickable
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.core.designsystem.component.button.DiveSoptButton
import com.sopt.dive.core.designsystem.component.textfield.DiveSoptPasswordTextField
import com.sopt.dive.core.designsystem.component.textfield.DiveSoptTextField
import com.sopt.dive.core.util.conditionalImePadding
import com.sopt.dive.data.UserPrefs

@Composable
fun SignInRoute(
    paddingValues: PaddingValues,
    navigateToSignUp: () -> Unit,
    navigateToHome: () -> Unit,
) {
    val context = LocalContext.current
    val userPrefs = remember { UserPrefs(context) }
    val viewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory(userPrefs))
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val focusManager = LocalFocusManager.current


    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is SignInSideEffect.NavigateToSignUp -> navigateToSignUp()
                is SignInSideEffect.NavigateToHome -> navigateToHome()
            }
        }
    }

    SignInScreen(
        uiState = uiState,
        paddingValues = paddingValues,
        onUserIdChanged = viewModel::updateUserId,
        keyboardActions = KeyboardActions(
            onDone = { focusManager.clearFocus() },
            onNext = { focusManager.moveFocus(FocusDirection.Next) }
        ),
        onPasswordChanged = viewModel::updatePassword,
        onIconClick = viewModel::onIconClicked,
        onSignInClick = viewModel::onSignInClicked,
        onSignUpClick = viewModel::onSignUpClicked
    )
}

@Composable
private fun SignInScreen(
    uiState: SignInState,
    paddingValues: PaddingValues,
    onUserIdChanged: (String) -> Unit,
    keyboardActions: KeyboardActions,
    onPasswordChanged: (String) -> Unit,
    onIconClick: () -> Unit,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(top = paddingValues.calculateTopPadding() + 30.dp)
            .conditionalImePadding(bottom = 20.dp)
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
            value = uiState.username,
            onValueChanged = onUserIdChanged,
            placeholder = "아이디를 입력해주세요",
            imeAction = ImeAction.Next,
            keyboardActions = keyboardActions
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "PW",
            color = Color.Black,
            fontSize = 30.sp
        )

        DiveSoptPasswordTextField(
            password = uiState.password,
            onPasswordChanged = onPasswordChanged,
            onIconClick = onIconClick,
            imeAction = ImeAction.Done,
            keyboardActions = keyboardActions,
            isPasswordVisible = uiState.isPasswordVisible
        )

        Spacer(modifier = Modifier.weight(1f))

        DiveSoptButton(
            buttonText = "Welcome To SOPT",
            onButtonClick = onSignInClick
        )

        Spacer(modifier = Modifier.height(10.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "회원가입하기",
                modifier = Modifier
                    .clickable(onClick = onSignUpClick),
                color = Color.Gray,
                fontSize = 15.sp,
            )
        }
    }
}
