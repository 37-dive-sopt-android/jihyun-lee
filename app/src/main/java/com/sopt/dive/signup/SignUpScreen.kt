package com.sopt.dive.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.component.InputField
import com.sopt.dive.ui.theme.DiveTheme


data class SignUpResult(
    val id: String,
    val pw: String,
    val nickname: String,
    val mbti: String
)

@Composable
fun SignUpRoute(
    modifier: Modifier = Modifier,
    onComplete: (SignUpResult) -> Unit
) {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    val context = LocalContext.current

    SignUpScreen(
        id = id,
        onIdChange = { id = it },
        pw = pw,
        onPwChange = { pw = it },
        nickname = nickname,
        onNicknameChange = { nickname = it },
        mbti = mbti,
        onMbtiChange = { mbti = it },
        onSignUpButtonClick = {
            if (id.isBlank() || pw.isBlank() || nickname.isBlank() || mbti.isBlank()) {
                Toast.makeText(context, "모든 정보를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (id.length < 6 || id.length > 10) {
                Toast.makeText(context, "아이디는 6~10글자여야 합니다.", Toast.LENGTH_SHORT).show()
            } else if (pw.length < 8 || pw.length > 12) {
                Toast.makeText(context, "비밀번호는 8~12글자여야 합니다.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                onComplete(SignUpResult(id, pw, nickname, mbti))
            }
        },
        modifier = modifier
    )
}

@Composable
private fun SignUpScreen(
    id: String,
    onIdChange: (String) -> Unit,
    pw: String,
    onPwChange: (String) -> Unit,
    nickname: String,
    onNicknameChange: (String) -> Unit,
    mbti: String,
    onMbtiChange: (String) -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 40.dp, horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SIGN UP",
            fontSize = 30.sp
        )

        InputField(
            label = "ID",
            value = id,
            onValueChange = onIdChange,
            placeholder = "아이디를 입력해주세요",
            modifier = Modifier.padding(top = 40.dp)
        )
        InputField(
            label = "PW",
            value = pw,
            onValueChange = onPwChange,
            placeholder = "비밀번호를 입력해주세요",
            modifier = Modifier.padding(top = 20.dp)
        )
        InputField(
            label = "NICKNAME",
            value = nickname,
            onValueChange = onNicknameChange,
            placeholder = "닉네임을 입력해주세요",
            modifier = Modifier.padding(top = 20.dp)
        )
        InputField(
            label = "MBTI",
            value = mbti,
            onValueChange = onMbtiChange,
            placeholder = "MBTI를 입력해주세요",
            modifier = Modifier.padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onSignUpButtonClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("회원가입하기")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    DiveTheme {
        SignUpScreen(
            id = id,
            onIdChange = { id = it },
            pw = pw,
            onPwChange = { pw = it },
            nickname = nickname,
            onNicknameChange = { nickname = it },
            mbti = mbti,
            onMbtiChange = { mbti = it },
            onSignUpButtonClick = { }
        )
    }
}