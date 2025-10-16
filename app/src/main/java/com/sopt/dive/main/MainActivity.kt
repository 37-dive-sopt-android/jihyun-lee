package com.sopt.dive.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val id = intent.getStringExtra("userId").orEmpty()
        val password = intent.getStringExtra("password").orEmpty()
        val nickname = intent.getStringExtra("nickname").orEmpty()
        val mbti = intent.getStringExtra("mbti").orEmpty()

        setContent {
            DiveTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainRoute(
                        id = id,
                        password = password,
                        nickname = nickname,
                        mbti = mbti,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

}