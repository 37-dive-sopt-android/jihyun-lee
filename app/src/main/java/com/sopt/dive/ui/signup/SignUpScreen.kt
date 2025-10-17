package com.sopt.dive.ui.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.ui.components.DiveBasicButton
import com.sopt.dive.ui.components.InputField
import com.sopt.dive.ui.theme.DiveTheme

data class SignUpResult(
    val id: String,
    val pw: String,
    val name: String,
    val nickname: String,
    val mbti: String,
)

@Composable
fun SignUpRoute(
    modifier: Modifier = Modifier,
    onComplete: (SignUpResult) -> Unit,
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    val context = LocalContext.current

    SignUpScreen(
        id = id,
        onIdChange = { id = it },
        password = password,
        onPwChange = { password = it },
        name = name,
        onNameChange = { name = it },
        nickname = nickname,
        onNicknameChange = { nickname = it },
        mbti = mbti,
        onMbtiChange = { mbti = it },
        onSignUpButtonClick = {
            if (id.isBlank() || password.isBlank() || nickname.isBlank() || mbti.isBlank()) {
                Toast
                    .makeText(
                        context,
                        context.getString(R.string.signup_empty_fail_message),
                        Toast.LENGTH_SHORT,
                    ).show()
            } else if (id.length < 6 || id.length > 10) {
                Toast
                    .makeText(
                        context,
                        context.getString(R.string.signup_id_fail_message),
                        Toast.LENGTH_SHORT,
                    ).show()
            } else if (password.length < 8 || password.length > 12) {
                Toast
                    .makeText(
                        context,
                        context.getString(R.string.signup_pw_fail_message),
                        Toast.LENGTH_SHORT,
                    ).show()
            } else {
                Toast
                    .makeText(
                        context,
                        context.getString(R.string.signup_success_message),
                        Toast.LENGTH_SHORT,
                    ).show()
                onComplete(SignUpResult(id, password, name, nickname, mbti))
            }
        },
        modifier = modifier,
    )
}

@Composable
private fun SignUpScreen(
    id: String,
    onIdChange: (String) -> Unit,
    password: String,
    onPwChange: (String) -> Unit,
    name: String,
    onNameChange: (String) -> Unit,
    nickname: String,
    onNicknameChange: (String) -> Unit,
    mbti: String,
    onMbtiChange: (String) -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .padding(20.dp),
    ) {
        Column(
            modifier =
                Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(top = 20.dp)
                    .weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.signup_title),
                fontSize = 30.sp,
            )

            Column(verticalArrangement = Arrangement.spacedBy(30.dp)) {
                InputField(
                    label = stringResource(R.string.signup_id),
                    value = id,
                    onValueChange = onIdChange,
                    placeholder = stringResource(R.string.signup_id_placeholder),
                    modifier = Modifier.padding(top = 40.dp),
                    imeAction = ImeAction.Next,
                )
                InputField(
                    label = stringResource(R.string.signup_pw),
                    value = password,
                    onValueChange = onPwChange,
                    placeholder = stringResource(R.string.signup_pw_placeholder),
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password,
                )
                InputField(
                    label = stringResource(R.string.signup_name),
                    value = name,
                    onValueChange = onNameChange,
                    placeholder = stringResource(R.string.signup_name_placeholder),
                    imeAction = ImeAction.Next,
                )
                InputField(
                    label = stringResource(R.string.signup_nickname),
                    value = nickname,
                    onValueChange = onNicknameChange,
                    placeholder = stringResource(R.string.signup_nickname_placeholder),
                    imeAction = ImeAction.Next,
                )
                InputField(
                    label = stringResource(R.string.signup_mbti),
                    value = mbti,
                    onValueChange = onMbtiChange,
                    placeholder = stringResource(R.string.signup_mbti_placeholder),
                )
            }
        }
        DiveBasicButton(
            onClick = onSignUpButtonClick,
            text = stringResource(R.string.signup_button),
            modifier =
                Modifier
                    .padding(vertical = 10.dp)
                    .imePadding(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    DiveTheme {
        SignUpScreen(
            id = id,
            onIdChange = { id = it },
            password = password,
            onPwChange = { password = it },
            name = name,
            onNameChange = { name = it },
            nickname = nickname,
            onNicknameChange = { nickname = it },
            mbti = mbti,
            onMbtiChange = { mbti = it },
            onSignUpButtonClick = { },
        )
    }
}
