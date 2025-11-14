package com.sopt.dive.presentation.components

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
import com.sopt.dive.presentation.model.DiveTab
import com.sopt.dive.presentation.theme.DiveTheme
import com.sopt.dive.presentation.util.noRippleClickable

@Composable
fun DiveBottomBar(
    currentTab: DiveTab,
    onTabSelected: (DiveTab) -> Unit,
    isVisible: Boolean,
    modifier: Modifier = Modifier
) {
    if (isVisible) {
        Row (
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        ) {
            DiveTab.entries.forEach { diveTab ->
                BottomBarItem(
                    tab = diveTab,
                    selected = (currentTab == diveTab),
                    onClick = { onTabSelected(diveTab) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
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
            style = DiveTheme.typography.caption.small_regular
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomBarPreview() {
    DiveTheme {
        DiveBottomBar(
            currentTab = DiveTab.HOME,
            onTabSelected = {},
            isVisible = true
        )
    }
}