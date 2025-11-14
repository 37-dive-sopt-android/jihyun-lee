package com.sopt.dive.presentation.ui.search

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.sopt.dive.presentation.theme.DiveTheme
import com.sopt.dive.presentation.util.noRippleClickable

private enum class FlipState { Flipped, NotFlipped }

@Composable
fun StackedFlipCard(
    imageUrl: String,
    text: String,
    modifier: Modifier = Modifier
) {
    var flipState by remember { mutableStateOf(FlipState.NotFlipped) }
    val transition = updateTransition(targetState = flipState)

    val commonSpring = remember {
        spring<Float>(
            dampingRatio = 0.65f,
            stiffness = 177.8f
        )
    }
    val rotation by transition.animateFloat(
        transitionSpec = { commonSpring }
    ) { state ->
        when (state) {
            FlipState.Flipped -> 180f
            FlipState.NotFlipped -> 0f
        }
    }
    val offset by transition.animateFloat(
        transitionSpec = { commonSpring }
    ) { state ->
        when(state) {
            FlipState.Flipped -> 30f
            FlipState.NotFlipped -> 0f
        }
    }
    val textAlpha by transition.animateFloat(
        transitionSpec = { commonSpring }
    ) { state ->
        when (state) {
            FlipState.Flipped -> 1f
            FlipState.NotFlipped -> 0f
        }
    }

    val frontShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 90.dp,
        bottomStart = 170.dp,
        bottomEnd = 20.dp
    )
    val backShape = RoundedCornerShape(
        topStart = 90.dp,
        topEnd = 20.dp,
        bottomStart = 20.dp,
        bottomEnd = 170.dp
    )

    val maxElevation = 12.dp
    val frontElevation = if (rotation <= 90f) {
        maxElevation * (1f - (rotation / 90f))
    } else 0.dp
    val backElevation = if (rotation > 90f) {
        maxElevation * ((rotation - 90f) / 90f)
    } else 0.dp

    Box(
        modifier = modifier
            .aspectRatio(2f / 3f)
            .noRippleClickable {
                flipState = if (flipState == FlipState.Flipped) FlipState.NotFlipped else FlipState.Flipped
            }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .shadow(
                    elevation = frontElevation,
                    shape = frontShape
                )
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 12f * density
                    translationX = offset.dp.toPx()
                    translationY = offset.dp.toPx()
                }
                .zIndex(if (rotation <= 90f) 1f else -1f)
                .clip(frontShape)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .shadow(
                    elevation = backElevation,
                    shape = backShape
                )
                .clip(backShape)
                .background(DiveTheme.colors.limeGreen)
                .zIndex(0f)
        ) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                modifier = Modifier.graphicsLayer {
                    alpha = textAlpha
                }
            )
        }
    }
}

@Preview
@Composable
private fun FlipCardPreview() {
    DiveTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            StackedFlipCard(
                imageUrl = "https://i.pinimg.com/736x/e4/1f/3c/e41f3cb1c9d36dda02709861e57d00ed.jpg",
                text = "안녕하세요".repeat(100),
                modifier = Modifier.width(300.dp)
            )
        }
    }
}