package com.sopt.dive.presentation.ui.home

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.domain.model.ProfileModel
import com.sopt.dive.presentation.components.ProfileImage
import com.sopt.dive.presentation.theme.DiveTheme
import com.sopt.dive.presentation.theme.defaultDiveTypography

@Composable
fun ProfileItem(
    profileInfo: ProfileModel,
    modifier: Modifier = Modifier,
    imageSize: Dp = Dp(60F),
    nameTextStyle: TextStyle = defaultDiveTypography.body.medium_regular,
    messageTextStyle: TextStyle = defaultDiveTypography.caption.medium_regular
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        ProfileImage(
            imageUrl = profileInfo.profileImageUrl,
            imageSize = imageSize
        )

        Column (
            modifier = Modifier.weight(1f)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = profileInfo.name,
                    style = nameTextStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                if (profileInfo.isBirthday) {
                    Image(
                        painter = painterResource(R.drawable.ic_birthday_cake),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
            if (!profileInfo.statusMessage.isNullOrEmpty()) {
                Text(
                    text = profileInfo.statusMessage,
                    style = messageTextStyle,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }

        if(profileInfo.isBirthday) {
            GiftButton()
        }
        else if (!profileInfo.music.isNullOrEmpty()) {
            MusicBox(
                music = profileInfo.music,
                modifier = Modifier.weight(1f, fill = false)
            )
        }
    }
}

@Composable
private fun MusicBox(
    music: String,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .border(1.dp, DiveTheme.colors.limeGreen, RoundedCornerShape(20.dp))
            .padding(vertical = 6.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = music,
            style = DiveTheme.typography.caption.medium_regular,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f, fill = false)
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_play_button),
            contentDescription = null,
            modifier = Modifier.size(10.dp),
            tint = DiveTheme.colors.gray600
        )
    }
}

@Composable
private fun GiftButton(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .border(1.dp, DiveTheme.colors.gray400, RoundedCornerShape(20.dp))
            .padding(vertical = 4.dp, horizontal = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.home_gift_button),
            style = DiveTheme.typography.caption.medium_regular
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileItemPreview() {
    DiveTheme {
        Column {
            ProfileItem(
                ProfileModel(
                    profileImageUrl = "https://i.pinimg.com/736x/be/e0/d0/bee0d0a2cf15e7d3a92da047a016bbb6.jpg",
                    name = "이지현",
                    statusMessage = "안녕하세요",
                    music = "COLOR - NCT WISH"
                )
            )
            ProfileItem(
                ProfileModel(
                    name = "이지현",
                    isBirthday = true
                )
            )
        }
    }
}