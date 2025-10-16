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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
                SignUpScreen()
            }
        }
    }
}

@Composable
fun SignUpScreen() {
    val id = remember { mutableStateOf("") }
    val pw = remember { mutableStateOf("") }
    val nickname = remember { mutableStateOf("") }
    val drinking = remember { mutableStateOf("") }
    val context = LocalContext.current

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
            value = id.value,
            onValueChange = { newValue -> id.value = newValue },
            placeholder = "아이디를 입력해주세요",
            modifier = Modifier.padding(top = 40.dp)
        )
        InputField(
            label = "PW",
            value = pw.value,
            onValueChange = { newValue -> pw.value = newValue },
            placeholder = "비밀번호를 입력해주세요",
            modifier = Modifier.padding(top = 20.dp)
        )
        InputField(
            label = "NICKNAME",
            value = nickname.value,
            onValueChange = { newValue -> nickname.value = newValue },
            placeholder = "닉네임을 입력해주세요",
            modifier = Modifier.padding(top = 20.dp)
        )
        InputField(
            label = "주량",
            value = drinking.value,
            onValueChange = { newValue -> drinking.value = newValue },
            placeholder = "소주 주량을 입력해주세요",
            modifier = Modifier.padding(top = 20.dp)
        )

        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                val intent = Intent(context, LoginActivity::class.java)
                intent.putExtra("userId", id.value)
                intent.putExtra("password", pw.value)
                intent.putExtra("nickname", nickname.value)
                intent.putExtra("drinking", drinking.value)
                context.startActivity(intent)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("회원가입하기")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    DiveTheme {
        SignUpScreen()
    }
}