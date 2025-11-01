package com.sopt.dive.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.util.noRippleClickable

@Composable
fun DiveBasicButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    containerColor: Color = DiveTheme.colors.purple40,
    contentColor: Color = DiveTheme.colors.white,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .noRippleClickable { onClick() }
            .background(if (isEnable) containerColor else DiveTheme.colors.gray200)
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = if(isEnable) contentColor else DiveTheme.colors.gray600,
            style = DiveTheme.typography.caption.regular_14
        )
    }
}

@Preview
@Composable
private fun DiveBasicButtonPreview() {
    DiveTheme {
        DiveBasicButton(
            "회원가입하기",
            onClick = {},
        )
    }
}
