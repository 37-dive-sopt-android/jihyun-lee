package com.sopt.dive.ui.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier
) {
    SearchScreen(
        modifier = modifier
    )
}

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        FlipCard(
            frontImageUrl = "https://i.pinimg.com/736x/39/e4/83/39e483cc2769b34faa081680c3741c7c.jpg",
            backImageUrl = "https://i.pinimg.com/736x/e4/1f/3c/e41f3cb1c9d36dda02709861e57d00ed.jpg",
            modifier = Modifier.width(300.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    SearchScreen()
}