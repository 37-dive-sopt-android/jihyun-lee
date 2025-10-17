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
import com.sopt.dive.ui.components.DiveInputField
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
    onComplete: (SignUpResult) -> Unit,
    modifier: Modifier = Modifier,
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    var idError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    var nameError by remember { mutableStateOf(false) }
    var nicknameError by remember { mutableStateOf(false) }
    var mbtiError by remember { mutableStateOf(false) }

    val context = LocalContext.current

    SignUpScreen(
        id = id,
        onIdChange = {
            id = it
            idError = if (it.length in 6..10) null else context.getString(R.string.signup_id_fail_message)
        },
        password = password,
        onPwChange = {
            password = it
            passwordError = if (it.length in 8..12) null else context.getString(R.string.signup_pw_fail_message)
        },
        name = name,
        onNameChange = {
            name = it
            nameError = it.isBlank()
        },
        nickname = nickname,
        onNicknameChange = {
            nickname = it
            nameError = it.isBlank()
        },
        mbti = mbti,
        onMbtiChange = {
            mbti = it
            mbtiError = it.isBlank()
        },
        idError = idError,
        passwordError = passwordError,
        nameError = nameError,
        nicknameError = nicknameError,
        mbtiError = mbtiError,
        onSignUpButtonClick = {
            if (idError == null && passwordError == null && !nameError && !nicknameError && !mbtiError) {
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
    idError: String?,
    passwordError: String?,
    nameError: Boolean,
    nicknameError: Boolean,
    mbtiError: Boolean,
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
                DiveInputField(
                    label = stringResource(R.string.signup_id),
                    value = id,
                    onValueChange = onIdChange,
                    placeholder = stringResource(R.string.signup_id_placeholder),
                    modifier = Modifier.padding(top = 40.dp),
                    isError = idError != null,
                    errorMessage = idError,
                    imeAction = ImeAction.Next,
                )
                DiveInputField(
                    label = stringResource(R.string.signup_pw),
                    value = password,
                    onValueChange = onPwChange,
                    placeholder = stringResource(R.string.signup_pw_placeholder),
                    isError = passwordError != null,
                    errorMessage = passwordError,
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password,
                )
                DiveInputField(
                    label = stringResource(R.string.signup_name),
                    value = name,
                    onValueChange = onNameChange,
                    placeholder = stringResource(R.string.signup_name_placeholder),
                    isError = nameError,
                    imeAction = ImeAction.Next,
                )
                DiveInputField(
                    label = stringResource(R.string.signup_nickname),
                    value = nickname,
                    onValueChange = onNicknameChange,
                    placeholder = stringResource(R.string.signup_nickname_placeholder),
                    isError = nicknameError,
                    imeAction = ImeAction.Next,
                )
                DiveInputField(
                    label = stringResource(R.string.signup_mbti),
                    value = mbti,
                    onValueChange = onMbtiChange,
                    placeholder = stringResource(R.string.signup_mbti_placeholder),
                    isError = mbtiError,
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
    val context = LocalContext.current

    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    var idError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }

    var nameError by remember { mutableStateOf(false) }
    var nicknameError by remember { mutableStateOf(false) }
    var mbtiError by remember { mutableStateOf(false) }

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
            idError = idError,
            passwordError = passwordError,
            nameError = nameError,
            nicknameError = nicknameError,
            mbtiError = mbtiError,
            onSignUpButtonClick = {
                when {
                    id.length !in 6..10 ->
                        idError =
                            context.getString(R.string.signup_id_fail_message)

                    password.length !in 8..12 ->
                        passwordError =
                            context.getString(R.string.signup_pw_fail_message)

                    name.isBlank() -> nameError = true
                    nickname.isBlank() -> nicknameError = true
                    mbti.isBlank() -> mbtiError = true
                    else -> {}
                }
            },
        )
    }
}
