package com.sopt.dive.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.sopt.dive.ui.feature.main.MainRoute
import com.sopt.dive.ui.model.DiveTab
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.util.noRippleClickable

@Composable
fun DiveBottomBar(
    navController: NavHostController,
    currentTab: DiveTab,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomBarItem(
            tab = DiveTab.HOME,
            selected = (currentTab == DiveTab.HOME),
            onClick = { navController.navigate(MainRoute.Home) }
        )
        BottomBarItem(
            tab = DiveTab.SEARCH,
            selected = (currentTab == DiveTab.SEARCH),
            onClick = { navController.navigate(MainRoute.Search)}
        )
        BottomBarItem(
            tab = DiveTab.MYPAGE,
            selected = (currentTab == DiveTab.MYPAGE),
            onClick = { navController.navigate(MainRoute.MyPage)}
        )
    }
}

@Composable
private fun BottomBarItem(
    tab: DiveTab,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .noRippleClickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(tab.iconResId),
            contentDescription = null,
            tint = if(selected) tab.selectedColor else tab.defaultColor
        )
        Text(
            text = stringResource(tab.label),
            color = if (selected) tab.selectedColor else tab.defaultColor,
            style = DiveTheme.typography.caption.regular_12
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomBarPreview() {
    val navController = rememberNavController()

    DiveTheme {
        DiveBottomBar(
            navController = navController,
            currentTab = DiveTab.HOME
        )
    }
}