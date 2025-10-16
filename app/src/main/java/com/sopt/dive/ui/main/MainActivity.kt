package com.sopt.dive.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.util.IntentKeys

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val id = intent.getStringExtra(IntentKeys.ID).orEmpty()
        val password = intent.getStringExtra(IntentKeys.PASSWORD).orEmpty()
        val name = intent.getStringExtra(IntentKeys.NAME).orEmpty()
        val nickname = intent.getStringExtra(IntentKeys.NICKNAME).orEmpty()
        val mbti = intent.getStringExtra(IntentKeys.MBTI).orEmpty()

        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainRoute(
                        id = id,
                        password = password,
                        name = name,
                        nickname = nickname,
                        mbti = mbti,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}