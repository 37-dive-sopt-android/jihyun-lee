package com.sopt.dive.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import coil.compose.AsyncImage
import com.sopt.dive.R
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun ProfileImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    imageSize: Dp = Dp(60F)
) {
    Box(
        modifier = modifier
            .size(imageSize)
            .clip(RoundedCornerShape(percent = 40))
            .background(if (imageUrl.isNullOrEmpty()) DiveTheme.colors.gray200 else DiveTheme.colors.transParent),
        contentAlignment = Alignment.Center
    ) {
        if (imageUrl.isNullOrEmpty()) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_person_24),
                contentDescription = null,
                modifier = Modifier.size(imageSize * 2 / 3),
                tint = DiveTheme.colors.gray400
            )
        } else {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.size(imageSize),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Preview
@Composable
private fun ProfileImagePreview() {
    DiveTheme {
        ProfileImage(
            imageUrl = null
        )
    }
}