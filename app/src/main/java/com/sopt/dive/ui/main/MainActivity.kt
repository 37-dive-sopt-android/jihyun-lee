package com.sopt.dive.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sopt.dive.ui.login.LoginActivity
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.IntentKeys
import com.sopt.dive.util.Prefs

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        var id = intent.getStringExtra(IntentKeys.ID)
        var password = intent.getStringExtra(IntentKeys.PASSWORD)
        var name = intent.getStringExtra(IntentKeys.NAME)
        var nickname = intent.getStringExtra(IntentKeys.NICKNAME)
        var mbti = intent.getStringExtra(IntentKeys.MBTI)

        if (id.isNullOrEmpty() && Prefs.isLoggedIn(this)) {
            Prefs.loadUser(this)?.let { user ->
                id = user.id
                password = user.password
                name = user.name
                nickname = user.nickname
                mbti = user.mbti
            }
        }

        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainRoute(
                        id = id.orEmpty(),
                        password = password.orEmpty(),
                        name = name.orEmpty(),
                        nickname = nickname.orEmpty(),
                        mbti = mbti.orEmpty(),
                        onLogoutClick = {
                            Prefs.logout(this)
                            val intent = Intent(this, LoginActivity::class.java)
                            startActivity(intent)
                            finish()
                        },
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}
