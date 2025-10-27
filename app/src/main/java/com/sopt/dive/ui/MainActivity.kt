package com.sopt.dive.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.ui.components.DiveBottomBar
import com.sopt.dive.ui.model.DiveTab
import com.sopt.dive.ui.navigation.DiveNavHost
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                val navController = rememberNavController()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = DiveBottomBar(
                        navController = navController,
                        currentTab = DiveTab.HOME
                    )
                ) { innerPadding ->
                    DiveNavHost(
                        navController = navController,
                        padding = innerPadding
                    )
                }
            }
        }
    }
}
