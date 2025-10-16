package com.sopt.dive.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun MainRoute(
    id: String,
    password: String,
    nickname: String,
    mbti: String,
    modifier: Modifier = Modifier
) {
    MainScreen(id, password, nickname, mbti, modifier)
}

@Composable
private fun MainScreen(
    id: String,
    password: String,
    nickname: String,
    mbti: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(vertical = 20.dp, horizontal = 20.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.tomayo),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Text("이지현")
        }
        Text("안녕하세요. 이지현입니다.")

        Column(
            modifier = Modifier.padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            MainDataField("ID", id)
            MainDataField("PW", password)
            MainDataField("NICKNAME", nickname)
            MainDataField("MBTI", mbti)
        }
    }
}

@Composable
private fun MainDataField(
    label: String,
    text: String,
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier) {
        Text(
            text = label,
            fontSize = 25.sp
        )
        Text(text)
    }
}

@Preview(showBackground = true)
@Composable
private fun MainPreview() {
    val id by remember { mutableStateOf("아이디") }
    val password by remember { mutableStateOf("비밀번호") }
    val nickname by remember { mutableStateOf("지현") }
    val mbti by remember { mutableStateOf("ISTP") }

    DiveTheme {
        MainScreen(id, password, nickname, mbti)
    }
}