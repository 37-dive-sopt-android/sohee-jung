package com.sopt.dive.presentation.search

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sopt.dive.R

@Composable
fun SearchRoute(
    paddingValues: PaddingValues
) {
    SearchScreen(
        paddingValues = paddingValues
    )
}

@Composable
private fun SearchScreen(
    paddingValues: PaddingValues,
    modifier: Modifier = Modifier
) {
    var isCardFlipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isCardFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "rotation"
    )
    val isReversing = !isCardFlipped && rotation > 0f
    val showBack = if (isReversing) rotation >= 90f else rotation > 90f
    val image = if (showBack) R.drawable.img_back else R.drawable.img_profile

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(300.dp)
                .graphicsLayer{
                    rotationY = rotation
                    cameraDistance = 12 * density
                }
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "눌러보세요",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.Yellow)
                .padding(vertical = 10.dp)
                .clickable(onClick = { isCardFlipped = !isCardFlipped })
        )
    }
}
