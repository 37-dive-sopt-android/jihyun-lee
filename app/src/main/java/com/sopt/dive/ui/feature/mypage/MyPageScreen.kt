package com.sopt.dive.ui.feature.mypage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.domain.model.UserInfo
import com.sopt.dive.ui.components.ProfileImage
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.util.noRippleClickable

@Composable
fun MyPageRoute(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyPageViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val id = UserPrefs.getId()

    LaunchedEffect(id) {
        if (id != null) {
            viewModel.loadUserInfo(id)
        }
    }

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is MyPageSideEffect.NavigateToLogin -> onNavigateToLogin()
            }
        }
    }

    MyPageScreen(
        uiState = uiState,
        onLogoutClick = viewModel::logout,
        onWithdrawClick = viewModel::withdraw,
        modifier = modifier
    )
}

@Composable
private fun MyPageScreen(
    uiState: MyPageUiState,
    onLogoutClick: () -> Unit,
    onWithdrawClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp),
    ) {
        Row(
            modifier = Modifier.padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProfileImage(uiState.userInfo.profileImageUrl)
            Text(
                text = uiState.userInfo.name,
                style = DiveTheme.typography.body.large_semibold,
                color = DiveTheme.colors.gray600
            )
        }
        Text(
            text = stringResource(R.string.mypage_user_description, uiState.userInfo.name),
            style = DiveTheme.typography.caption.large_regular,
            modifier = Modifier.padding(top = 10.dp),
        )

        Column(
            modifier = Modifier.padding(vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            MyDataField(stringResource(R.string.signup_id), uiState.userInfo.id)
            MyDataField(stringResource(R.string.signup_email), uiState.userInfo.email)
            MyDataField(stringResource(R.string.signup_age), uiState.userInfo.age?.toString() ?: "")
        }

        Text(
            text = stringResource(R.string.mypage_logout),
            style = DiveTheme.typography.caption.medium_regular,
            modifier = Modifier.noRippleClickable { onLogoutClick() },
            textDecoration = TextDecoration.Underline,
        )
        Text(
            text = stringResource(R.string.mypage_withdraw),
            style = DiveTheme.typography.caption.medium_regular,
            modifier = Modifier
                .noRippleClickable { onWithdrawClick() }
                .padding(top = 8.dp),
            textDecoration = TextDecoration.Underline,
        )
    }
}

@Composable
private fun MyDataField(
    label: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = label,
            style = DiveTheme.typography.body.medium_semibold,
            color = DiveTheme.colors.gray600
        )
        Text(
            text = text,
            style = DiveTheme.typography.caption.large_regular
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPagePreview() {
    val userInfo = UserInfo(
        id = "아이디",
        name = "이지현",
        email = "example@email.com",
        age = 25
    )

    DiveTheme {
        MyPageScreen(
            uiState = MyPageUiState(userInfo = userInfo),
            onLogoutClick = {},
            onWithdrawClick = {}
        )
    }
}
