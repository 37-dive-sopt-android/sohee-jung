package com.sopt.dive.presentation.search

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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
    var isCard1Flipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isCard1Flipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "rotation"
    )
    val showBack = if (rotation > 90f) rotation >= 90f else rotation > 90f
    val imageAnim1 = if (showBack) R.drawable.img_back else R.drawable.img_profile


    // 2번째 애니메이션 관련
    var isCard2Flipped by remember { mutableStateOf(false) }
    val transition = updateTransition(targetState = isCard2Flipped, label = "Card Transition")

    val angle by transition.animateFloat(
        transitionSpec = {
            spring(dampingRatio = 0.75f, stiffness = 178f)
        },
        label = "angle"
    ) { flipped -> if (flipped) 180f else 0f }

    val textAlpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 500) }
    ) { isCard2Flipped ->
        if (isCard2Flipped) 1f else 0f
    }

    val offset by transition.animateDp(label = "offset") { flipped -> if (flipped) 15.dp else 0.dp }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues = paddingValues)
            .padding(top = 20.dp)
            .padding(horizontal = 30.dp)
    ) {
        Image(
            painter = painterResource(id = imageAnim1),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(300.dp)
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12 * density
                }
                .clickable(onClick = { isCard1Flipped = !isCard1Flipped })
        )

        Spacer(modifier = Modifier.height(40.dp))

        Box(
            modifier = Modifier
                .clickable(onClick = { isCard2Flipped = !isCard2Flipped })
        ) {
            Box(
                modifier = Modifier
                    .offset(x = offset, y = offset)
                    .zIndex(if (isCard2Flipped) 0f else 1f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(
                            shape = RoundedCornerShape(
                                topStart = 10.dp,
                                topEnd = 60.dp,
                                bottomStart = 60.dp,
                                bottomEnd = 10.dp
                            )
                        )
                        .graphicsLayer {
                            rotationY = angle
                            cameraDistance = 12 * density
                        }
                )
            }

            Text(
                text = "asdfasdfasdfasdfasdfdfasdfasdfasdfasdfasdfaagdkflkslkfa'sldkfla;ksdlfka",
                modifier = Modifier
                    .size(200.dp)
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 60.dp,
                            topEnd = 10.dp,
                            bottomStart = 10.dp,
                            bottomEnd = 60.dp
                        )
                    )
                    .background(color = Color.Magenta)
                    .graphicsLayer(alpha = textAlpha)
                    .zIndex(if (isCard2Flipped) 1f else 0f)
            )
        }
    }
}
