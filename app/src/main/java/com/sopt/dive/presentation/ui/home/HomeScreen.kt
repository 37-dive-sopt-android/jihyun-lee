package com.sopt.dive.presentation.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sopt.dive.R
import com.sopt.dive.domain.model.ProfileModel
import com.sopt.dive.domain.model.ReqresUserModel
import com.sopt.dive.presentation.components.ProfileImage
import com.sopt.dive.presentation.theme.DiveTheme

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadData()
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileImage(
                    imageUrl = uiState.userProfile.profileImageUrl,
                    imageSize = 80.dp
                )
                Text(
                    text = uiState.userProfile.name,
                    style = DiveTheme.typography.body.large_regular
                )
            }
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
        userProfile = ProfileModel(
            profileImageUrl = "https://i.pinimg.com/736x/be/e0/d0/bee0d0a2cf15e7d3a92da047a016bbb6.jpg",
            name = "이지현"
        ),
        friendList = listOf(
            ReqresUserModel(
                id = 0,
                email = "",
                firstName = "",
                lastName = "",
                avatar = ""
            )
        )
    )

    DiveTheme {
        HomeScreen(
            uiState = uiState
        )
    }
}