package com.sopt.dive.ui.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.domain.model.Music
import com.sopt.dive.domain.model.ProfileInfo
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun HomeRoute(
    padding: PaddingValues
) {
    val userProfile = ProfileInfo(
        imageUrl = "https://i.pinimg.com/736x/be/e0/d0/bee0d0a2cf15e7d3a92da047a016bbb6.jpg",
        name = "이지현"
    )

    val friendList = listOf(
        ProfileInfo(
            imageUrl = "https://i.pinimg.com/736x/be/e0/d0/bee0d0a2cf15e7d3a92da047a016bbb6.jpg",
            name = "이지현",
            introduction = "안녕하세요",
            music = Music("COLOR", "NCT WISH")
        ),
        ProfileInfo(
            name = "이지현",
            isBirth = true
        ),
        ProfileInfo(
            name = "이지현",
            isBirth = true,
            music = Music("COLOR", "NCT WISH")
        )
    )

    HomeScreen(
        userProfile = userProfile,
        friendList = friendList,
        modifier =  Modifier.padding(padding)
    )
}

@Composable
fun HomeScreen(
    userProfile: ProfileInfo,
    friendList: List<ProfileInfo>,
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            ProfileItem(
                profileInfo = userProfile,
                imageSize = 80.dp
            )
        }

        item {
            Column {
                HorizontalDivider()
                Text(
                    text = stringResource(R.string.home_friends, friendList.size),
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        items(friendList) { profile ->
            ProfileItem(profile)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    val userProfile = ProfileInfo(
        imageUrl = "https://i.pinimg.com/736x/be/e0/d0/bee0d0a2cf15e7d3a92da047a016bbb6.jpg",
        name = "이지현"
    )

    val friendList = listOf(
        ProfileInfo(
            imageUrl = "https://i.pinimg.com/736x/be/e0/d0/bee0d0a2cf15e7d3a92da047a016bbb6.jpg",
            name = "이지현",
            introduction = "안녕하세요",
            music = Music("COLOR", "NCT WISH")
        ),
        ProfileInfo(
            name = "이지현",
            isBirth = true
        )
    )

    DiveTheme {
        HomeScreen(
            userProfile = userProfile,
            friendList = friendList
        )
    }
}