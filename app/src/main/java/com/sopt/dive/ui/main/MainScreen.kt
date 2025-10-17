package com.sopt.dive.ui.main

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.ui.components.DiveBasicButton
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun MainRoute(
    id: String,
    password: String,
    name: String,
    nickname: String,
    mbti: String,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MainScreen(
        id = id,
        password = password,
        name = name,
        nickname = nickname,
        mbti = mbti,
        onLogoutClick = onLogoutClick,
        modifier = modifier,
    )
}

@Composable
private fun MainScreen(
    id: String,
    password: String,
    name: String,
    nickname: String,
    mbti: String,
    onLogoutClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = 20.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.icon_person_24),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(Color.LightGray)
            )
            Text(
                text = name,
                fontSize = 20.sp,
            )
        }
        Text(
            text = stringResource(R.string.main_user_description, name),
            modifier = Modifier.padding(top = 10.dp),
        )

        Column(
            modifier = Modifier.padding(top = 40.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            MainDataField(stringResource(R.string.signup_id), id)
            MainDataField(stringResource(R.string.signup_pw), password)
            MainDataField(stringResource(R.string.signup_nickname), nickname)
            MainDataField(stringResource(R.string.signup_mbti), mbti)
        }

        Spacer(modifier = Modifier.weight(1f))
        DiveBasicButton(
            text = stringResource(R.string.main_logout),
            onClick = onLogoutClick,
        )
    }
}

@Composable
private fun MainDataField(
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
private fun MainPreview() {
    val id by remember { mutableStateOf("아이디") }
    val password by remember { mutableStateOf("비밀번호") }
    val name by remember { mutableStateOf("이지현") }
    val nickname by remember { mutableStateOf("지현") }
    val mbti by remember { mutableStateOf("ISTP") }

    DiveTheme {
        MainScreen(id, password, name, nickname, mbti, {})
    }
}
