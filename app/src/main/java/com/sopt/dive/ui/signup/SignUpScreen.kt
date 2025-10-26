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
import androidx.compose.runtime.derivedStateOf
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
import com.sopt.dive.data.UserInfo
import com.sopt.dive.ui.components.DiveBasicButton
import com.sopt.dive.ui.components.DiveBasicTextField
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun SignUpRoute(
    onComplete: (UserInfo) -> Unit,
    modifier: Modifier = Modifier,
) {
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var nickname by remember { mutableStateOf("") }
    var mbti by remember { mutableStateOf("") }

    val context = LocalContext.current

    val idError by remember {
        derivedStateOf {
            if (id.length in 6..10) null else context.getString(R.string.signup_id_fail_message)
        }
    }

    val passwordError by remember(password) {
        derivedStateOf {
            if (password.length in 8..12) null else context.getString(R.string.signup_pw_fail_message)
        }
    }

    val nameError by remember(name) { derivedStateOf { name.isBlank() } }
    val nicknameError by remember(nickname) { derivedStateOf { nickname.isBlank() } }
    val mbtiError by remember(mbti) { derivedStateOf { mbti.isBlank() } }

    val uiState = SignUpUiState(
        id = id,
        password = password,
        name = name,
        nickname = nickname,
        mbti = mbti,
        idError = idError,
        passwordError = passwordError,
        nameError = nameError,
        nicknameError = nicknameError,
        mbtiError = mbtiError
    )

    SignUpScreen(
        uiState = uiState,
        onIdChange = { id = it },
        onPwChange = { password = it },
        onNameChange = { name = it },
        onNicknameChange = { nickname = it },
        onMbtiChange = { mbti = it },
        onSignUpButtonClick = {
            if (uiState.isSignUpEnabled) {
                Toast.makeText(
                    context,
                    context.getString(R.string.signup_success_message),
                    Toast.LENGTH_SHORT,
                ).show()
                onComplete(UserInfo(id, password, name, nickname, mbti))
            }
        },
        modifier = modifier
    )
}

@Composable
private fun SignUpScreen(
    uiState: SignUpUiState,
    onIdChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onMbtiChange: (String) -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Column(
            modifier = Modifier
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
                DiveBasicTextField(
                    label = stringResource(R.string.signup_id),
                    value = uiState.id,
                    onValueChange = onIdChange,
                    placeholder = stringResource(R.string.signup_id_placeholder),
                    modifier = Modifier.padding(top = 40.dp),
                    imeAction = ImeAction.Next,
                    textFieldValidType =  uiState.textFieldValidType
                )
                DiveBasicTextField(
                    label = stringResource(R.string.signup_pw),
                    value = uiState.password,
                    onValueChange = onPwChange,
                    placeholder = stringResource(R.string.signup_pw_placeholder),
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Password,
                    textFieldValidType = uiState.textFieldValidType
                )
                DiveBasicTextField(
                    label = stringResource(R.string.signup_name),
                    value = uiState.name,
                    onValueChange = onNameChange,
                    placeholder = stringResource(R.string.signup_name_placeholder),
                    imeAction = ImeAction.Next,
                    textFieldValidType = uiState.textFieldValidType
                )
                DiveBasicTextField(
                    label = stringResource(R.string.signup_nickname),
                    value = uiState.nickname,
                    onValueChange = onNicknameChange,
                    placeholder = stringResource(R.string.signup_nickname_placeholder),
                    imeAction = ImeAction.Next,
                    textFieldValidType = uiState.textFieldValidType
                )
                DiveBasicTextField(
                    label = stringResource(R.string.signup_mbti),
                    value = uiState.mbti,
                    onValueChange = onMbtiChange,
                    placeholder = stringResource(R.string.signup_mbti_placeholder),
                    textFieldValidType = uiState.textFieldValidType
                )
            }
        }
        DiveBasicButton(
            onClick = onSignUpButtonClick,
            text = stringResource(R.string.signup_button),
            modifier = Modifier
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

    val context = LocalContext.current

    val idError by remember {
        derivedStateOf {
            if (id.length in 6..10) null else context.getString(R.string.signup_id_fail_message)
        }
    }

    val passwordError by remember(password) {
        derivedStateOf {
            if (password.length in 8..12) null else context.getString(R.string.signup_pw_fail_message)
        }
    }

    val nameError by remember(name) { derivedStateOf { name.isBlank() } }
    val nicknameError by remember(nickname) { derivedStateOf { nickname.isBlank() } }
    val mbtiError by remember(mbti) { derivedStateOf { mbti.isBlank() } }

    val uiState = SignUpUiState(
        id = id,
        password = password,
        name = name,
        nickname = nickname,
        mbti = mbti,
        idError = idError,
        passwordError = passwordError,
        nameError = nameError,
        nicknameError = nicknameError,
        mbtiError = mbtiError
    )

    DiveTheme {
        SignUpScreen(
            uiState = uiState,
            onIdChange = { id = it },
            onPwChange = { password = it },
            onNameChange = { name = it },
            onNicknameChange = { nickname = it },
            onMbtiChange = { mbti = it },
            onSignUpButtonClick = {
                if (uiState.isSignUpEnabled) {
                    Toast.makeText(
                        context,
                        context.getString(R.string.signup_success_message),
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
        )
    }
}
