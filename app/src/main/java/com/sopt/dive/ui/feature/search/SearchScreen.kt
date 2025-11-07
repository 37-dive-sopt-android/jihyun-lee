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
            imageUrl = "https://i.pinimg.com/736x/13/fc/14/13fc149bd618a181cf1542c3140c5f40.jpg",
            text = "안녕하세요".repeat(100),
            modifier = Modifier.width(300.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    SearchScreen()
}