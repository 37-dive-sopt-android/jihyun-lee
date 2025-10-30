package com.sopt.dive.ui.feature.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
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
import com.sopt.dive.R
import com.sopt.dive.domain.model.UserInfo
import com.sopt.dive.ui.model.TextFieldValidState
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
    var isNameTouched by remember { mutableStateOf(false) }
    var isNicknameTouched by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val idValidType by remember(id) {
        derivedStateOf {
            when {
                id.isEmpty() -> TextFieldValidState.DEFAULT
                id.length in 6..10 -> TextFieldValidState.VALID
                else -> TextFieldValidState.INVALID(R.string.signup_id_fail_message)
            }
        }
    }
    val passwordValidType by remember(password) {
        derivedStateOf {
            when {
                password.isEmpty() -> TextFieldValidState.DEFAULT
                password.length in 8..12 -> TextFieldValidState.VALID
                else -> TextFieldValidState.INVALID(R.string.signup_pw_fail_message)
            }
        }
    }
    val nameValidType by remember(name, isNameTouched) {
        derivedStateOf {
            when {
                !isNameTouched -> TextFieldValidState.DEFAULT
                name.isNotBlank() -> TextFieldValidState.VALID
                else -> TextFieldValidState.INVALID(R.string.signup_name_fail_message)
            }
        }
    }
    val nicknameValidType by remember(nickname, isNicknameTouched) {
        derivedStateOf {
            when {
                !isNicknameTouched -> TextFieldValidState.DEFAULT
                nickname.isNotBlank() -> TextFieldValidState.VALID
                else -> TextFieldValidState.INVALID(R.string.signup_nickname_fail_message)
            }
        }
    }
    val mbtiValidType by remember(mbti) {
        derivedStateOf {
            when {
                mbti.isEmpty() -> TextFieldValidState.DEFAULT
                mbti.length == 4 -> TextFieldValidState.VALID
                else -> TextFieldValidState.INVALID(R.string.signup_mbti_fail_message)
            }
        }
    }

    val uiState = SignUpUiState(
        id = id,
        password = password,
        name = name,
        nickname = nickname,
        mbti = mbti,
        idValidType = idValidType,
        passwordValidType = passwordValidType,
        nameValidType = nameValidType,
        nicknameValidType = nicknameValidType,
        mbtiValidType = mbtiValidType
    )

    SignUpScreen(
        uiState = uiState,
        onIdChange = { id = it },
        onPwChange = { password = it },
        onNameChange = {
            name = it
            isNameTouched = true
        },
        onNicknameChange = {
            nickname = it
            isNicknameTouched = true
        },
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .navigationBarsPadding()
            .imePadding()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.signup_title),
            style = DiveTheme.typography.heather.semibold_32,
            color = DiveTheme.colors.gray600,
            modifier = Modifier.padding(top = 20.dp)
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(vertical = 40.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            DiveBasicTextField(
                label = stringResource(R.string.signup_id),
                value = uiState.id,
                onValueChange = onIdChange,
                placeholder = stringResource(R.string.signup_id_placeholder),
                imeAction = ImeAction.Next,
                textFieldValidType = uiState.idValidType
            )
            DiveBasicTextField(
                label = stringResource(R.string.signup_pw),
                value = uiState.password,
                onValueChange = onPwChange,
                placeholder = stringResource(R.string.signup_pw_placeholder),
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password,
                textFieldValidType = uiState.passwordValidType
            )
            DiveBasicTextField(
                label = stringResource(R.string.signup_name),
                value = uiState.name,
                onValueChange = onNameChange,
                placeholder = stringResource(R.string.signup_name_placeholder),
                imeAction = ImeAction.Next,
                textFieldValidType = uiState.nameValidType
            )
            DiveBasicTextField(
                label = stringResource(R.string.signup_nickname),
                value = uiState.nickname,
                onValueChange = onNicknameChange,
                placeholder = stringResource(R.string.signup_nickname_placeholder),
                imeAction = ImeAction.Next,
                textFieldValidType = uiState.nicknameValidType
            )
            DiveBasicTextField(
                label = stringResource(R.string.signup_mbti),
                value = uiState.mbti,
                onValueChange = onMbtiChange,
                placeholder = stringResource(R.string.signup_mbti_placeholder),
                imeAction = ImeAction.Done,
                textFieldValidType = uiState.mbtiValidType
            )
        }
        DiveBasicButton(
            onClick = onSignUpButtonClick,
            text = stringResource(R.string.signup_button),
            modifier = Modifier
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
    var isNameTouched by remember { mutableStateOf(false) }
    var isNicknameTouched by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val idValidType by remember(id) {
        derivedStateOf {
            when {
                id.isEmpty() -> TextFieldValidState.DEFAULT
                id.length in 6..10 -> TextFieldValidState.VALID
                else -> TextFieldValidState.INVALID(R.string.signup_id_fail_message)
            }
        }
    }
    val passwordValidType by remember(password) {
        derivedStateOf {
            when {
                password.isEmpty() -> TextFieldValidState.DEFAULT
                password.length in 8..12 -> TextFieldValidState.VALID
                else -> TextFieldValidState.INVALID(R.string.signup_pw_fail_message)
            }
        }
    }
    val nameValidType by remember(name) {
        derivedStateOf {
            when {
                !isNameTouched -> TextFieldValidState.DEFAULT
                name.isNotBlank() -> TextFieldValidState.VALID
                else -> TextFieldValidState.INVALID(R.string.signup_name_fail_message)
            }
        }
    }
    val nicknameValidType by remember(nickname) {
        derivedStateOf {
            when {
                !isNicknameTouched -> TextFieldValidState.DEFAULT
                nickname.isNotBlank() -> TextFieldValidState.VALID
                else -> TextFieldValidState.INVALID(R.string.signup_nickname_fail_message)
            }
        }
    }
    val mbtiValidType by remember(mbti) {
        derivedStateOf {
            when {
                mbti.isEmpty() -> TextFieldValidState.DEFAULT
                mbti.length == 4 -> TextFieldValidState.VALID
                else -> TextFieldValidState.INVALID(R.string.signup_mbti_fail_message)
            }
        }
    }

    val uiState = SignUpUiState(
        id = id,
        password = password,
        name = name,
        nickname = nickname,
        mbti = mbti,
        idValidType = idValidType,
        passwordValidType = passwordValidType,
        nameValidType = nameValidType,
        nicknameValidType = nicknameValidType,
        mbtiValidType = mbtiValidType
    )

    DiveTheme {
        SignUpScreen(
            uiState = uiState,
            onIdChange = { id = it },
            onPwChange = { password = it },
            onNameChange = {
                name = it
                isNameTouched = true
            },
            onNicknameChange = {
                nickname = it
                isNicknameTouched = true
            },
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
