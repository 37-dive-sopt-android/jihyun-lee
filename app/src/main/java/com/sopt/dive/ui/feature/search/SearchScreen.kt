package com.sopt.dive.ui.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
        flipCardFrontImageUrl = "https://i.pinimg.com/736x/39/e4/83/39e483cc2769b34faa081680c3741c7c.jpg",
        flipCardBackImageUrl = "https://i.pinimg.com/736x/e4/1f/3c/e41f3cb1c9d36dda02709861e57d00ed.jpg",
        stackedFlipCardImageUrl = "https://i.pinimg.com/736x/13/fc/14/13fc149bd618a181cf1542c3140c5f40.jpg",
        modifier = modifier
    )
}

@Composable
fun SearchScreen(
    flipCardFrontImageUrl: String,
    flipCardBackImageUrl: String,
    stackedFlipCardImageUrl: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(vertical = 100.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(100.dp)
    ){
        FlipCard(
            frontImageUrl = flipCardFrontImageUrl,
            backImageUrl = flipCardBackImageUrl,
            modifier = Modifier.width(300.dp)
        )
        StackedFlipCard(
            imageUrl = stackedFlipCardImageUrl,
            text = "안녕하세요".repeat(100),
            modifier = Modifier.width(300.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchPreview() {
    SearchScreen(
        flipCardFrontImageUrl = "https://i.pinimg.com/736x/39/e4/83/39e483cc2769b34faa081680c3741c7c.jpg",
        flipCardBackImageUrl = "https://i.pinimg.com/736x/e4/1f/3c/e41f3cb1c9d36dda02709861e57d00ed.jpg",
        stackedFlipCardImageUrl = "https://i.pinimg.com/736x/13/fc/14/13fc149bd618a181cf1542c3140c5f40.jpg"
    )
}