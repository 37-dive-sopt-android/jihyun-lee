package com.sopt.dive.ui.feature.mypage

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.domain.model.UserInfo
import com.sopt.dive.ui.components.DiveBasicButton
import com.sopt.dive.ui.feature.login.LoginActivity
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun MyPageRoute(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val userInfo = remember { UserPrefs.loadUser(context) }

    MyPageScreen(
        userInfo = userInfo?: UserInfo(),
        onLogoutClick = {
            UserPrefs.logout(context)
            val intent = Intent(context, LoginActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            context.startActivity(intent)
        },
        modifier = modifier
    )
}

@Composable
private fun MyPageScreen(
    userInfo: UserInfo,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.ic_person_24),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Text(
                text = userInfo.name,
                fontSize = 20.sp,
            )
        }
        Text(
            text = stringResource(R.string.mypage_user_description, userInfo.name),
            modifier = Modifier.padding(top = 10.dp),
        )

        Column(
            modifier = Modifier.padding(top = 40.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            MyDataField(stringResource(R.string.signup_id), userInfo.id)
            MyDataField(stringResource(R.string.signup_pw), userInfo.password)
            MyDataField(stringResource(R.string.signup_nickname), userInfo.nickname)
            MyDataField(stringResource(R.string.signup_mbti), userInfo.mbti)
        }

        Spacer(modifier = Modifier.weight(1f))
        DiveBasicButton(
            text = stringResource(R.string.mypage_logout),
            onClick = onLogoutClick,
        )
    }
}

@Composable
private fun MyDataField(
    label: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            fontSize = 25.sp,
        )
        Text(text)
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

    DiveTheme {
        MyPageScreen(
            userInfo = userInfo,
            onLogoutClick = {}
        )
    }
}
