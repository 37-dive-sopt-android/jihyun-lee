package com.sopt.dive.presentation.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sopt.dive.presentation.navigation.rememberMainNavigator
import com.sopt.dive.presentation.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigator = rememberMainNavigator()
            DiveTheme {
                MainScreen(
                    navigator = navigator
                )
            }
        }
    }
}
