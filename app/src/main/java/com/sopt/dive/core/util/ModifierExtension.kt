package com.sopt.dive.core.util

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.unit.Dp

@Composable
fun Modifier.noRippleClickable(
    onClick: () -> Unit
): Modifier = this
    .clickable(
        interactionSource = remember { MutableInteractionSource() },
        indication = null,
        onClick = onClick
    )

@SuppressLint("SuspiciousModifierThen")
fun Modifier.conditionalImePadding(bottom: Dp): Modifier = composed {
    val imeBottom = WindowInsets.ime.asPaddingValues().calculateBottomPadding()
    val navBottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()

    val shouldApplyImePadding = imeBottom > navBottom

    if (shouldApplyImePadding) {
        this.then(imePadding())
    } else {
        this.then(padding(bottom = navBottom + bottom))
    }
}
