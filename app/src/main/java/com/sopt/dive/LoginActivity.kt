package com.sopt.dive

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.ui.theme.DiveTheme

class LoginActivity : ComponentActivity() {

    private var registeredId by mutableStateOf("")
    private var registeredPassword by mutableStateOf("")
    private var registeredNickname by mutableStateOf("")
    private var registeredDrinking by mutableStateOf("")

    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            registeredId = data?.getStringExtra("userId").orEmpty()
            registeredPassword = data?.getStringExtra("password").orEmpty()
            registeredNickname = data?.getStringExtra("nickname").orEmpty()
            registeredDrinking = data?.getStringExtra("drinking").orEmpty()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            var userId by remember { mutableStateOf("") }
            var userPassword by remember { mutableStateOf("") }

            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LogInScreen(
                        id = userId,
                        password = userPassword,
                        onIdChange = { userId = it },
                        onPasswordChange = { userPassword = it },
                        onLoginClick = {
                            if (
                                userId.isNotBlank() &&
                                userPassword.isNotBlank() &&
                                userId == registeredId &&
                                userPassword == registeredPassword
                            ) {
                                Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                            }
                        },
                        onSignUpClick = {
                            val intent = Intent(context, SignUpActivity::class.java)
                            signUpLauncher.launch(intent)
                        },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}

@Composable
fun LogInScreen(
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
                modifier = Modifier.padding(top = 20.dp)
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