package com.sopt.dive.ui.feature.mypage

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.domain.model.ProfileInfo
import com.sopt.dive.domain.model.UserInfo
import com.sopt.dive.ui.components.ProfileImage
import com.sopt.dive.ui.feature.login.LoginActivity
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.util.noRippleClickable

@Composable
fun MyPageRoute(
    padding: PaddingValues
) {
    val context = LocalContext.current
    val userInfo = remember { UserPrefs.loadUser(context) }
    val userProfile = ProfileInfo(profileImageUrl = "https://i.pinimg.com/736x/96/37/2d/96372ded13d1e6b17cdf10b4ecb23483.jpg")

    MyPageScreen(
        userInfo = userInfo?: UserInfo(),
        userProfile = userProfile,
        onLogoutClick = {
            UserPrefs.logout(context)
            val intent = Intent(context, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            context.startActivity(intent)
        },
        modifier = Modifier.padding(padding)
    )
}

@Composable
private fun MyPageScreen(
    userInfo: UserInfo,
    userProfile: ProfileInfo,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Row(
            modifier = Modifier.padding(top = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProfileImage(userProfile.profileImageUrl)
            Text(
                text = userInfo.name,
                style = DiveTheme.typography.body.regular_18
            )
        }
        Text(
            text = stringResource(R.string.mypage_user_description, userInfo.name),
            style = DiveTheme.typography.caption.regular_14,
            modifier = Modifier.padding(top = 10.dp),
        )

        Column(
            modifier = Modifier.padding(vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            MyDataField(stringResource(R.string.signup_id), userInfo.id)
            MyDataField(stringResource(R.string.signup_pw), userInfo.password)
            MyDataField(stringResource(R.string.signup_nickname), userInfo.nickname)
            MyDataField(stringResource(R.string.signup_mbti), userInfo.mbti)
        }

        Text(
            text = stringResource(R.string.mypage_logout),
            style = DiveTheme.typography.caption.regular_12,
            modifier = Modifier.noRippleClickable { onLogoutClick() },
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
            style = DiveTheme.typography.body.regular_18
        )
        Text(
            text = text,
            style = DiveTheme.typography.caption.regular_14
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyPagePreview() {
    val userInfo = remember {
        UserInfo(
            id = "아이디",
            password = "비밀번호",
            name = "이지현",
            nickname = "지현",
            mbti = "istp"
        )
    }
    val userProfile = ProfileInfo()

    DiveTheme {
        MyPageScreen(
            userInfo = userInfo,
            userProfile = userProfile,
            onLogoutClick = {}
        )
    }
}
