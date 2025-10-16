package com.sopt.dive.login

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.component.InputField
import com.sopt.dive.main.MainActivity
import com.sopt.dive.signup.SignUpActivity
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun LoginRoute(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var userId by rememberSaveable { mutableStateOf("") }
    var userPassword by rememberSaveable { mutableStateOf("") }

    var registeredId by rememberSaveable { mutableStateOf("") }
    var registeredPassword by rememberSaveable { mutableStateOf("") }
    var registeredNickname by rememberSaveable { mutableStateOf("") }
    var registeredDrinking by rememberSaveable { mutableStateOf("") }

    val signUpLauncher = rememberLauncherForActivityResult(
        contract = StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            registeredId = data?.getStringExtra("userId").orEmpty()
            registeredPassword = data?.getStringExtra("password").orEmpty()
            registeredNickname = data?.getStringExtra("nickname").orEmpty()
            registeredDrinking = data?.getStringExtra("drinking").orEmpty()
        }
    }

    LogInScreen(
        id = userId,
        password = userPassword,
        onIdChange = { userId = it },
        onPasswordChange = { userPassword = it },
        onLoginClick = {
            val ok = userId.isNotBlank() &&
                    userPassword.isNotBlank() &&
                    userId == registeredId &&
                    userPassword == registeredPassword

            if (ok) {
                Toast.makeText(context, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()
                context.startActivity(Intent(context, MainActivity::class.java))
            } else {
                Toast.makeText(context, "로그인에 실패했습니다", Toast.LENGTH_SHORT).show()
            }
        },
        onSignUpClick = {
            signUpLauncher.launch(Intent(context, SignUpActivity::class.java))
        },
        modifier = modifier
    )
}


@Composable
private fun LogInScreen(
    id: String,
    password: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 40.dp)
        ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome To SOPT",
            fontSize = 30.sp
        )

        Column (
            modifier = Modifier
                .padding(top = 40.dp)
                .weight(1f)
        ) {
            InputField(
                label = "ID",
                value = id,
                onValueChange = onIdChange,
                placeholder = "아이디를 입력해주세요"
            )
            InputField(
                label = "PW",
                value = password,
                onValueChange = onPasswordChange,
                placeholder = "비밀번호를 입력해주세요",
                modifier = Modifier.padding(top = 20.dp),
                isPassword = true
            )
        }

        Button(
            onClick = onLoginClick,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("로그인 하기")
        }

        Text(
            text = "회원가입하기",
            modifier = Modifier
                .padding(top = 4.dp)
                .clickable { onSignUpClick() },
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LogInPreview() {
    val userId by remember { mutableStateOf("") }
    val password by remember { mutableStateOf("") }

    DiveTheme {
        LogInScreen(
            id = userId,
            password = password,
            onIdChange = {},
            onPasswordChange = {},
            onLoginClick = {},
            onSignUpClick = {}
        )
    }
}