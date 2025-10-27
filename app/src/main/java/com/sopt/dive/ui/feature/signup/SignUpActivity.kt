package com.sopt.dive.ui.feature.signup

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.model.IntentKeys
import com.sopt.dive.data.local.UserPrefs

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignUpRoute(
                        onComplete = { result ->
                            UserPrefs.saveUserInfo(
                                context = this,
                                id = result.id,
                                password = result.password,
                                name = result.name,
                                nickname = result.name,
                                mbti = result.mbti,
                            )
                            val intent = Intent().apply {
                                putExtra(IntentKeys.ID, result.id)
                                putExtra(IntentKeys.PASSWORD, result.password)
                                putExtra(IntentKeys.NAME, result.name)
                                putExtra(IntentKeys.NICKNAME, result.nickname)
                                putExtra(IntentKeys.MBTI, result.mbti)
                            }
                            setResult(RESULT_OK, intent)
                            finish()
                        },
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
