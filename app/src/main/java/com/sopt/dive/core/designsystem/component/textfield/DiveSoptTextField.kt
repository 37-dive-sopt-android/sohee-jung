package com.sopt.dive.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DiveSoptTextField(
    value: String,
    onValueChanged: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = FocusRequester(),
    keyboardType: KeyboardType = KeyboardType.Unspecified,
    imeAction: ImeAction = ImeAction.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    maxLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable () -> Unit = {}
) {
    BasicTextField(
        value = value,
        onValueChange = { newValue ->
            onValueChanged(newValue)
        },
        modifier = modifier
            .focusRequester(focusRequester = focusRequester),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 22.sp
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        keyboardActions = keyboardActions,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        cursorBrush = SolidColor(Color.Gray),
        decorationBox = { innerTextField ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .padding(top = 10.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        innerTextField()

                        if (value.isEmpty()) {
                            Text(
                                text = placeholder,
                                color = Color.LightGray,
                                fontSize = 18.sp
                            )
                        }
                    }
                    trailingIcon()
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = Color.Black)
                )
            }
        }
    )
}
