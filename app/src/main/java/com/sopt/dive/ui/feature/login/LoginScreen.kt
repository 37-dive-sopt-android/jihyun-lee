package com.sopt.dive.ui.feature.login

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.unit.sp
import com.sopt.dive.R
import com.sopt.dive.ui.components.DiveBasicButton
import com.sopt.dive.ui.components.DiveBasicTextField
import com.sopt.dive.ui.MainActivity
import com.sopt.dive.ui.feature.signup.SignUpActivity
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.model.IntentKeys
import com.sopt.dive.data.local.Prefs
import com.sopt.dive.ui.util.noRippleClickable

@Composable
fun LoginRoute(modifier: Modifier = Modifier) {
    val context = LocalContext.current

    var userId by rememberSaveable { mutableStateOf("") }
    var userPassword by rememberSaveable { mutableStateOf("") }

    var registeredId by rememberSaveable { mutableStateOf("") }
    var registeredPassword by rememberSaveable { mutableStateOf("") }
    var registeredName by rememberSaveable { mutableStateOf("") }
    var registeredNickname by rememberSaveable { mutableStateOf("") }
    var registeredMbti by rememberSaveable { mutableStateOf("") }

    val signUpLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.StartActivityForResult(),
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                registeredId = data?.getStringExtra(IntentKeys.ID).orEmpty()
                registeredPassword = data?.getStringExtra(IntentKeys.PASSWORD).orEmpty()
                registeredName = data?.getStringExtra(IntentKeys.NAME).orEmpty()
                registeredNickname = data?.getStringExtra(IntentKeys.NICKNAME).orEmpty()
                registeredMbti = data?.getStringExtra(IntentKeys.MBTI).orEmpty()
            }
        }

    LoginScreen(
        id = userId,
        password = userPassword,
        onIdChange = { userId = it },
        onPasswordChange = { userPassword = it },
        onLoginClick = {
            val ok = userId.isNotBlank() && userPassword.isNotBlank() && userId == registeredId && userPassword == registeredPassword

            if (ok) {
                Toast.makeText(context, context.getString(R.string.login_success_message), Toast.LENGTH_SHORT,).show()

                Prefs.setLoggedIn(
                    context = context,
                    value = true,
                )

                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra(IntentKeys.ID, registeredId)
                    putExtra(IntentKeys.PASSWORD, registeredPassword)
                    putExtra(IntentKeys.NAME, registeredName)
                    putExtra(IntentKeys.NICKNAME, registeredNickname)
                    putExtra(IntentKeys.MBTI, registeredMbti)
                }
                context.startActivity(intent)
            } else {
                Toast.makeText(context, context.getString(R.string.login_fail_message), Toast.LENGTH_SHORT).show()
            }
        },
        onSignUpClick = { signUpLauncher.launch(Intent(context, SignUpActivity::class.java)) },
        modifier = modifier,
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
            .padding(20.dp)
            .imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.login_title),
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier.padding(top = 20.dp)
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
                imeAction = ImeAction.Next,
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
            text = stringResource(R.string.login_title),
        )
        Text(
            text = stringResource(R.string.signup_button),
            fontSize = 14.sp,
            modifier = Modifier
                .padding(top = 4.dp)
                .noRippleClickable { onSignUpClick() },
            textDecoration = TextDecoration.Underline,
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
