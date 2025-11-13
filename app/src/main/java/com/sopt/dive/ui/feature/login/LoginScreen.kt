package com.sopt.dive.ui.feature.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sopt.dive.R
import com.sopt.dive.ui.components.DiveBasicTextField
import com.sopt.dive.ui.components.DiveLargeButton
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.util.noRippleClickable

@Composable
fun LoginRoute(
    onNavigateToSignUp: () -> Unit,
    onNavigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffect) {
        viewModel.sideEffect.collect { effect ->
            when (effect) {
                is LoginSideEffect.NavigateToHome -> onNavigateToHome()
                is LoginSideEffect.ShowToast -> {
                    Toast.makeText(context, context.getString(effect.messageResId), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    LoginScreen(
        uiState = uiState,
        onIdChange = viewModel::onIdChange,
        onPasswordChange = viewModel::onPasswordChange,
        onPasswordToggleClick = viewModel::onPasswordToggleClick,
        onLoginClick = viewModel::login,
        onSignUpClick = onNavigateToSignUp,
        modifier = modifier
    )
}

@Composable
private fun LoginScreen(
    uiState: LoginUiState,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordToggleClick: () -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .imePadding(),
        topBar = {
            Text(
                text = stringResource(R.string.login_title),
                style = DiveTheme.typography.heather.medium_bold,
                color = DiveTheme.colors.gray600,
                modifier = Modifier
                    .padding(vertical = 40.dp, horizontal = 20.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        },
        bottomBar = {
            Column (
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DiveLargeButton(
                    onClick = onLoginClick,
                    text = stringResource(R.string.login_title),
                    isEnable = uiState.isLoginEnabled
                )
                Text(
                    text = stringResource(R.string.signup_button),
                    style = DiveTheme.typography.caption.medium_regular,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .noRippleClickable { onSignUpClick() },
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            DiveBasicTextField(
                label = stringResource(R.string.signup_id),
                value = uiState.id,
                onValueChange = onIdChange,
                placeholder = stringResource(R.string.signup_id_placeholder),
                imeAction = ImeAction.Next
            )
            DiveBasicTextField(
                label = stringResource(R.string.signup_pw),
                value = uiState.password,
                onValueChange = onPasswordChange,
                placeholder = stringResource(R.string.signup_pw_placeholder),
                visualTransformation = uiState.visualTransformation,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
                trailingIcon = {
                    IconButton(onClick = onPasswordToggleClick) {
                        Icon(
                            imageVector = if (uiState.isPasswordVisible) {
                                ImageVector.vectorResource(R.drawable.ic_visibility_off)
                            } else {
                                ImageVector.vectorResource(R.drawable.ic_visibility)
                            },
                            contentDescription = null,
                            tint = DiveTheme.colors.gray400
                        )
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    val viewModel = remember { LoginViewModel() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DiveTheme {
        LoginScreen(
            uiState = uiState,
            onIdChange = viewModel::onIdChange,
            onPasswordChange = viewModel::onPasswordChange,
            onPasswordToggleClick = viewModel::onPasswordToggleClick,
            onLoginClick = viewModel::login,
            onSignUpClick = {}
        )
    }
}
