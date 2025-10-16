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

    private var userId by mutableStateOf("")
    private var password by mutableStateOf("")
    private var nickname by mutableStateOf("")
    private var drinking by mutableStateOf("")

    private var loginId by mutableStateOf("")
    private var loginPassword by mutableStateOf("")

    private val signUpLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            userId = data?.getStringExtra("userId").orEmpty()
            password = data?.getStringExtra("password").orEmpty()
            nickname = data?.getStringExtra("nickname").orEmpty()
            drinking = data?.getStringExtra("drinking").orEmpty()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current

            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LogInScreen(
                        id = loginId,
                        password = loginPassword,
                        onIdChange = { loginId = it },
                        onPasswordChange = { loginPassword = it },
                        onClickLogin = {
                            if (
                                loginId.isNotBlank() &&
                                loginPassword.isNotBlank() &&
                                loginId == userId &&
                                loginPassword == password
                            ) {
                                Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
                                val intent = Intent(context, MainActivity::class.java)
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(context, "로그인 실패", Toast.LENGTH_SHORT).show()
                            }
                        },
                        onClickSignUp = {
                            val intent = Intent(this, SignUpActivity::class.java)
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
    onClickLogin: () -> Unit,
    onClickSignUp: () -> Unit,
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
            onClick = onClickLogin,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("로그인 하기")
        }

        Text(
            text = "회원가입하기",
            modifier = Modifier
                .padding(top = 4.dp)
                .clickable { onClickSignUp() },
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
            onClickLogin = {},
            onClickSignUp = {}
        )
    }
}