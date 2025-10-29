package com.sopt.dive.core.designsystem.component.textfield

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.sopt.dive.R
import com.sopt.dive.core.util.noRippleClickable

@Composable
fun DiveSoptPasswordTextField(
    password: String,
    onPasswordChanged: (String) -> Unit,
    onIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    isPasswordVisible: Boolean = true
) {
    val iconId = if (isPasswordVisible) R.drawable.ic_visibility else R.drawable.ic_visibility_off

    DiveSoptTextField(
        value = password,
        onValueChanged = onPasswordChanged,
        placeholder = "비밀번호를 입력하세요",
        keyboardType = KeyboardType.Password,
        imeAction = imeAction,
        keyboardActions = keyboardActions,
        visualTransformation = if (isPasswordVisible) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = iconId),
                contentDescription = "",
                modifier = Modifier.noRippleClickable(onClick = onIconClick),
                tint = Color.LightGray
            )
        },
        modifier = modifier
    )
}
