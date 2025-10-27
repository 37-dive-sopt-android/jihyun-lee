package com.sopt.dive.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.ui.components.DiveBottomBar
import com.sopt.dive.ui.model.DiveTab
import com.sopt.dive.ui.navigation.DiveNavHost
import com.sopt.dive.ui.navigation.Route
import com.sopt.dive.ui.theme.DiveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiveTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRouteName = navBackStackEntry?.destination?.route
                val currentTab = when (currentRouteName) {
                    Route.Home::class.qualifiedName -> DiveTab.HOME
                    Route.Search::class.qualifiedName -> DiveTab.SEARCH
                    Route.MyPage::class.qualifiedName -> DiveTab.MYPAGE
                    else -> DiveTab.HOME
                }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        DiveBottomBar(
                            navController = navController,
                            currentTab = currentTab,
                            modifier = Modifier.navigationBarsPadding()
                        )
                    }
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
