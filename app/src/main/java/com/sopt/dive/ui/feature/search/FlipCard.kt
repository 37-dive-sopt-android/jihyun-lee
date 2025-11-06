package com.sopt.dive.ui.feature.search

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.util.noRippleClickable

@Composable
fun FlipCard(
    frontImageUrl: String,
    backImageUrl: String,
    modifier: Modifier = Modifier
) {
    var isFlipped by remember { mutableStateOf(false) }
    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 1500)
    )

    AsyncImage(
        model = if (rotation <= 90f) frontImageUrl else backImageUrl,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .graphicsLayer {
                rotationY = rotation
                cameraDistance = 12f * density
                clip = false
            }
            .graphicsLayer {
                if (rotation > 90f) rotationY = 180f
            }
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(20.dp))
            .noRippleClickable { isFlipped = !isFlipped }
    )
}

@Preview
@Composable
private fun FlipCardPreview() {
    DiveTheme {
        FlipCard(
            frontImageUrl = "https://i.pinimg.com/736x/39/e4/83/39e483cc2769b34faa081680c3741c7c.jpg",
            backImageUrl = "https://i.pinimg.com/736x/e4/1f/3c/e41f3cb1c9d36dda02709861e57d00ed.jpg"
        )
    }
}