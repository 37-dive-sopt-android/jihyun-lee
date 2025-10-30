package com.sopt.dive.ui.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sopt.dive.R
import com.sopt.dive.domain.model.Music
import com.sopt.dive.domain.model.ProfileInfo
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun ProfileItem(
    profileInfo: ProfileInfo,
    modifier: Modifier = Modifier,
    imageSize: Dp = Dp(60F)
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ProfileImage(
            imageUrl = profileInfo.imageUrl,
            imageSize = imageSize
        )

        Column (
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1f)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = profileInfo.name,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (profileInfo.isBirth) {
                    Image(
                        painter = painterResource(R.drawable.ic_birthday_cake),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            if (profileInfo.introduction != null) {
                Text(
                    text = profileInfo.introduction,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        if (profileInfo.music != null) {
            MusicBox(
                music = profileInfo.music,
                modifier = Modifier.weight(1f, fill = false)
            )
        }
    }
}

@Composable
private fun ProfileImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    imageSize: Dp = Dp(60F)
) {
    Box(
        modifier = modifier
            .size(imageSize)
            .clip(RoundedCornerShape(24.dp))
            .background(if(imageUrl == null) Color.LightGray else Color.Transparent),
        contentAlignment = Alignment.Center
    ) {
        if (imageUrl.isNullOrEmpty()) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_person_24),
                contentDescription = null,
                modifier = Modifier.size(imageSize * 2 / 3),
                tint = Color.Gray
            )
        } else {
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                modifier = Modifier.size(imageSize)
            )
        }
    }
}

@Composable
private fun MusicBox(
    music: Music,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .border(1.dp, Color.Green, RoundedCornerShape(20.dp))
            .padding(vertical = 4.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(R.string.home_profile_music, music.title, music.artist),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_play_button),
            contentDescription = null,
            modifier = Modifier.size(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileItemPreview() {
    DiveTheme {
        Column {
            ProfileItem(
                ProfileInfo(
                    imageUrl = "https://i.pinimg.com/736x/be/e0/d0/bee0d0a2cf15e7d3a92da047a016bbb6.jpg",
                    name = "이지현",
                    introduction = "안녕하세요",
                    music = Music("COLOR", "NCT WISH")
                )
            )
            ProfileItem(
                ProfileInfo(
                    name = "이지현",
                    isBirth = true
                )
            )
        }
    }
}