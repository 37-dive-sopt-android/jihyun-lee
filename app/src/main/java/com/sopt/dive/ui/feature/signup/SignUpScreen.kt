package com.sopt.dive.ui.feature.signup

import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.ui.components.DiveBasicTextField
import com.sopt.dive.ui.components.DiveLargeButton
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun SignUpRoute(
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is SignUpSideEffect.NavigateToLogin -> onNavigateToLogin()
                is SignUpSideEffect.ShowToastResId -> {
                    Toast.makeText(context, effect.messageResId, Toast.LENGTH_SHORT).show()
                }
                is SignUpSideEffect.ShowToastString -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    SignUpScreen(
        uiState = uiState,
        onIdChange = viewModel::onIdChange,
        onPwChange = viewModel::onPwChange,
        onNameChange = viewModel::onNameChange,
        onEmailChange = viewModel::onEmailChange,
        onAgeChange = viewModel::onAgeChange,
        onSignUpButtonClick = viewModel::signUp,
        modifier = modifier
    )
}

@Composable
private fun SignUpScreen(
    uiState: SignUpUiState,
    onIdChange: (String) -> Unit,
    onPwChange: (String) -> Unit,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onAgeChange: (String) -> Unit,
    onSignUpButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold (
        modifier = modifier.imePadding(),
        topBar = {
            Text(
                text = stringResource(R.string.signup_title),
                style = DiveTheme.typography.heather.medium_bold,
                color = DiveTheme.colors.gray600,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 40.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        bottomBar = {
            DiveLargeButton(
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
                label = stringResource(R.string.signup_email),
                value = uiState.email,
                onValueChange = onEmailChange,
                placeholder = stringResource(R.string.signup_email_placeholder),
                imeAction = ImeAction.Next,
                textFieldValidType = uiState.emailValidType
            )
            DiveBasicTextField(
                label = stringResource(R.string.signup_age),
                value = uiState.age?.toString() ?: "",
                onValueChange = onAgeChange,
                placeholder = stringResource(R.string.signup_age_placeholder),
                imeAction = ImeAction.Done,
                textFieldValidType = uiState.ageValidType
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpPreview() {
    val viewModel = remember { SignUpViewModel() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DiveTheme {
        SignUpScreen(
            uiState = uiState,
            onIdChange = viewModel::onIdChange,
            onPwChange = viewModel::onPwChange,
            onNameChange = viewModel::onNameChange,
            onEmailChange = viewModel::onEmailChange,
            onAgeChange = viewModel::onAgeChange,
            onSignUpButtonClick = viewModel::signUp
        )
    }
}
