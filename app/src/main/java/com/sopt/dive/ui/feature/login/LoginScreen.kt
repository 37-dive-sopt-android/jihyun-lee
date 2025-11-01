package com.sopt.dive.ui.feature.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.R
import com.sopt.dive.ui.components.DiveBasicButton
import com.sopt.dive.ui.components.DiveBasicTextField
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.data.local.UserPrefs
import com.sopt.dive.ui.util.noRippleClickable

@Composable
fun LoginRoute(
    onNavigateToSignUp: () -> Unit,
    onNavigateToHome: () -> Unit
) {
    val context = LocalContext.current

    var userId by rememberSaveable { mutableStateOf("") }
    var userPassword by rememberSaveable { mutableStateOf("") }
    val isLoginEnabled by remember(userId, userPassword) {
        derivedStateOf {
            userId.isNotBlank() && userPassword.isNotBlank()
        }
    }

    val uiState = LoginUiState(
        id = userId,
        password = userPassword,
        isLoginEnabled = isLoginEnabled
    )

    LoginScreen(
        uiState = uiState,
        onIdChange = { userId = it },
        onPasswordChange = { userPassword = it },
        onLoginClick = {
            val registeredId = UserPrefs.getId()
            val registeredPassword = UserPrefs.getPassword()

            if (userId == registeredId && userPassword == registeredPassword) {
                Toast.makeText(context, context.getString(R.string.login_success_message), Toast.LENGTH_SHORT,).show()
                UserPrefs.setLoggedIn(value = true)
                onNavigateToHome()
            } else {
                Toast.makeText(context, context.getString(R.string.login_fail_message), Toast.LENGTH_SHORT).show()
            }
        },
        onSignUpClick = onNavigateToSignUp
    )
}

@Composable
private fun LoginScreen(
    uiState: LoginUiState,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.imePadding(),
        topBar = {
            Text(
                text = stringResource(R.string.login_title),
                style = DiveTheme.typography.heather.semibold_32,
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
                DiveBasicButton(
                    onClick = onLoginClick,
                    text = stringResource(R.string.login_title),
                    isEnable = uiState.isLoginEnabled
                )
                Text(
                    text = stringResource(R.string.signup_button),
                    style = DiveTheme.typography.caption.regular_12,
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
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    var userId by rememberSaveable { mutableStateOf("") }
    var userPassword by rememberSaveable { mutableStateOf("") }
    val isLoginEnabled by remember(userId, userPassword) {
        derivedStateOf {
            userId.isNotBlank() && userPassword.isNotBlank()
        }
    }

    val uiState = LoginUiState(
        id = userId,
        password = userPassword,
        isLoginEnabled = isLoginEnabled
    )

    DiveTheme {
        LoginScreen(
            uiState = uiState,
            onIdChange = { userId = it },
            onPasswordChange = { userPassword = it },
            onLoginClick = {},
            onSignUpClick = {},
        )
    }
}
