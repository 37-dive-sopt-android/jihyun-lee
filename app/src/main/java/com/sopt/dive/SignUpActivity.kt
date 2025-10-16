package com.sopt.dive

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

class SignUpActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                SignUpRoute { result ->
                    val intent = Intent().apply {
                        putExtra("userId", result.id)
                        putExtra("password", result.pw)
                        putExtra("nickname", result.nickname)
                        putExtra("drinking", result.drinking)
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            }
        }
    }
}

data class SignUpResult(
    val id: String,
    val pw: String,
    val nickname: String,
    val drinking: String
)

@Composable
fun SignUpRoute(
    onComplete: (SignUpResult) -> Unit
) {
    var id by remember { mutableStateOf("") }
    var pw by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var drinking by remember { mutableStateOf("") }

    SignUpScreen(
        id = id,
        onIdChange = { id = it },
        pw = pw,
        onPwChange = { pw = it },
        nickname = nickname,
        onNicknameChange = { nickname = it },
        drinking = drinking,
        onDrinkingChange = { drinking = it },
        onSignUpButtonClick = {
            onComplete(SignUpResult(id, pw, nickname, drinking))
        }
    )
}

@Composable
fun SignUpScreen(
    id: String,
    onIdChange: (String) -> Unit,
    pw: String,
    onPwChange: (String) -> Unit,
    nickname: String,
    onNicknameChange: (String) -> Unit,
    drinking: String,
    onDrinkingChange: (String) -> Unit,
    onSignUpButtonClick: () -> Unit
) {
    Column (
        modifier = Modifier
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
            label = "주량",
            value = drinking,
            onValueChange = onDrinkingChange,
            placeholder = "소주 주량을 입력해주세요",
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
    var drinking by remember { mutableStateOf("") }

    DiveTheme {
        SignUpScreen(
            id = id,
            onIdChange = {},
            pw = pw,
            onPwChange = {},
            nickname = nickname,
            onNicknameChange = {},
            drinking = drinking,
            onDrinkingChange = {},
            onSignUpButtonClick = { TODO() }
        )
    }
}