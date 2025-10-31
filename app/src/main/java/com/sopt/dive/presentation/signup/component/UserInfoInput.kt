package com.sopt.dive.presentation.signup.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.core.designsystem.component.textfield.DiveSoptTextField

@Composable fun UserInfoInput(
    userInfoInputSection: String,
    userInfoInputDescription: String,
    onUserInfoInputChanged: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default
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
            imeAction = imeAction,
            keyboardActions = keyboardActions
        )
    }
}
