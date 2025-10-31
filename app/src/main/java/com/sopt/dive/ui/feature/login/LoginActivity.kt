package com.sopt.dive.ui.feature.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sopt.dive.ui.MainActivity
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.data.local.UserPrefs

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (UserPrefs.isLoggedIn()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        enableEdgeToEdge()
        setContent {
            DiveTheme {
                LoginRoute()
            }
        }
    }
}
