package com.sopt.dive.ui.feature.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.ui.components.DiveBasicButton
import com.sopt.dive.ui.components.DiveBasicTextField
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun SignUpRoute(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    state: SignUpState = rememberSignUpState(onNavigateToLogin = onNavigateToLogin)
) {
    SignUpScreen(
        uiState = state.uiState,
        onIdChange = state::onIdChange,
        onPwChange = state::onPwChange,
        onNameChange = state::onNameChange,
        onNicknameChange = state::onNicknameChange,
        onMbtiChange = state::onMbtiChange,
        onSignUpButtonClick = state::onSignUpButtonClick,
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
    Scaffold (
        modifier = modifier.imePadding(),
        topBar = {
            Text(
                text = stringResource(R.string.signup_title),
                style = DiveTheme.typography.heather.semibold_32,
                color = DiveTheme.colors.gray600,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 40.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        bottomBar = {
            DiveBasicButton(
                onClick = onSignUpButtonClick,
                text = stringResource(R.string.signup_button),
                isEnable = uiState.isSignUpEnabled,
                modifier = Modifier.padding(20.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp),
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
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    val state = rememberSignUpState(onNavigateToLogin = {})

    DiveTheme {
        SignUpScreen(
            uiState = state.uiState,
            onIdChange = state::onIdChange,
            onPwChange = state::onPwChange,
            onNameChange = state::onNameChange,
            onNicknameChange = state::onNicknameChange,
            onMbtiChange = state::onMbtiChange,
            onSignUpButtonClick = state::onSignUpButtonClick
        )
    }
}
