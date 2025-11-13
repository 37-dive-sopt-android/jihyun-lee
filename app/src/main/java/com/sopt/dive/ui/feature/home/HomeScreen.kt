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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.domain.model.ProfileInfo
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val id = UserPrefs.getId()

    LaunchedEffect(id) {
        if (id != null) {
            viewModel.loadData(id)
        }
    }
    HomeScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            ProfileItem(
                profileInfo = uiState.userProfile,
                imageSize = 80.dp,
                nameTextStyle = DiveTheme.typography.body.large_regular
            )
        }

        item {
            Column {
                HorizontalDivider()
                Text(
                    text = stringResource(R.string.home_friends, uiState.friendList.size),
                    style = DiveTheme.typography.caption.medium_regular,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        items(items = uiState.friendList) { profile ->
            ProfileItem(profile)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    val uiState = HomeUiState(
        userProfile = ProfileInfo(
            profileImageUrl = "https://i.pinimg.com/736x/be/e0/d0/bee0d0a2cf15e7d3a92da047a016bbb6.jpg",
            name = "이지현"
        ),
        friendList = listOf(
            ProfileInfo(
                profileImageUrl = "https://i.pinimg.com/736x/be/e0/d0/bee0d0a2cf15e7d3a92da047a016bbb6.jpg",
                name = "이지현",
                statusMessage = "안녕하세요",
                music = "COLOR - NCT WISH"
            ),
            ProfileInfo(
                name = "이지현",
                isBirthday = true
            )
        )
    )

    DiveTheme {
        HomeScreen(
            uiState = uiState
        )
    }
}