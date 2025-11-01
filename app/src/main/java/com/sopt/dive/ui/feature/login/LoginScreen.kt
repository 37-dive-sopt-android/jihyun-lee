package com.sopt.dive.ui.feature.login

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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


    LoginScreen(
        id = userId,
        password = userPassword,
        onIdChange = { userId = it },
        onPasswordChange = { userPassword = it },
        onLoginClick = {
            val registeredId = UserPrefs.getId()
            val registeredPassword = UserPrefs.getPassword()

            val isLoginEnable = userId.isNotBlank() && userPassword.isNotBlank() && userId == registeredId && userPassword == registeredPassword

            if (isLoginEnable) {
                Toast.makeText(context, context.getString(R.string.login_success_message), Toast.LENGTH_SHORT,).show()
                UserPrefs.setLoggedIn(value = true,)
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
    id: String,
    password: String,
    onIdChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .imePadding()
            .navigationBarsPadding()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.login_title),
            style = DiveTheme.typography.heather.semibold_32,
            color = DiveTheme.colors.gray600,
            modifier = Modifier.padding(top = 40.dp)
        )

        Column(
            modifier = Modifier
                .padding(top = 50.dp)
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            DiveBasicTextField(
                label = stringResource(R.string.signup_id),
                value = id,
                onValueChange = onIdChange,
                placeholder = stringResource(R.string.signup_id_placeholder),
                imeAction = ImeAction.Next
            )
            DiveBasicTextField(
                label = stringResource(R.string.signup_pw),
                value = password,
                onValueChange = onPasswordChange,
                placeholder = stringResource(R.string.signup_pw_placeholder),
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        }

        DiveBasicButton(
            onClick = onLoginClick,
            text = stringResource(R.string.login_title)
        )
        Text(
            text = stringResource(R.string.signup_button),
            style = DiveTheme.typography.caption.regular_12,
            modifier = Modifier
                .padding(top = 4.dp)
                .noRippleClickable { onSignUpClick() },
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    var userId by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    DiveTheme {
        LoginScreen(
            id = userId,
            password = password,
            onIdChange = { userId = it },
            onPasswordChange = { password = it },
            onLoginClick = {},
            onSignUpClick = {},
        )
    }
}
