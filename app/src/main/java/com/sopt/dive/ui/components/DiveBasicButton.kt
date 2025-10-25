package com.sopt.dive.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sopt.dive.ui.theme.DiveTheme
import com.sopt.dive.ui.theme.Purple40
import com.sopt.dive.util.noRippleClickable

@Composable
fun DiveBasicButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    containerColor: Color = Purple40,
    contentColor: Color = contentColorFor(containerColor),
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .noRippleClickable { onClick() }
            .background(containerColor)
            .padding(vertical = 10.dp, horizontal = 20.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = contentColor,
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
