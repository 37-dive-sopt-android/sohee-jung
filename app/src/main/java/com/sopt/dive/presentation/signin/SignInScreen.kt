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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.sopt.dive.core.designsystem.LocalAppSnackbarHostState
import com.sopt.dive.core.designsystem.component.button.DiveSoptButton
import com.sopt.dive.core.designsystem.component.textfield.DiveSoptPasswordTextField
import com.sopt.dive.core.designsystem.component.textfield.DiveSoptTextField
import com.sopt.dive.core.util.conditionalImePadding
import com.sopt.dive.data.UserInfo
import com.sopt.dive.data.UserPrefs
import kotlinx.coroutines.launch

@Composable
fun SignInRoute(
    paddingValues: PaddingValues,
    onSignInClick: () -> Unit,
    onSignUpClick: () -> Unit
) {
    val context = LocalContext.current
    val prefs = remember { UserPrefs(context) }
    val profile by prefs.profileFlow.collectAsStateWithLifecycle(UserInfo.EMPTY)
    val focusManager = LocalFocusManager.current
    val scope = rememberCoroutineScope()
    val snackbar = LocalAppSnackbarHostState.current

    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    SignInScreen(
        paddingValues = paddingValues,
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
        onSignInClick = {
            scope.launch {
                if (!userId.isBlank()&& !password.isBlank() && userId == profile.userId && password == profile.password) {
                    prefs.setLoggedIn(isLoggedIn = true)
                    onSignInClick()
                } else {
                    snackbar.showSnackbar("로그인에 실패했습니다.")
                }
            }
        },
        onSignUpClick = onSignUpClick
    )
}

@Composable
private fun SignInScreen(
    paddingValues: PaddingValues,
    userId: String,
    onUserIdChanged: (String) -> Unit,
    keyboardActions: KeyboardActions,
    password: String,
    onPasswordChanged: (String) -> Unit,
    isPasswordVisible: Boolean,
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
            value = userId,
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
            password = password,
            onPasswordChanged = onPasswordChanged,
            onIconClick = onIconClick,
            imeAction = ImeAction.Done,
            keyboardActions = keyboardActions,
            isPasswordVisible = isPasswordVisible
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
