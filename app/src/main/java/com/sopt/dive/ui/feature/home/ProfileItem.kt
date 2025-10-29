package com.sopt.dive.ui.feature.home

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sopt.dive.R
import com.sopt.dive.domain.model.Music
import com.sopt.dive.ui.theme.DiveTheme

@Composable
fun ProfileItem(
    imageUrl: String,
    name: String,
    modifier: Modifier = Modifier,
    introduction:String? = null,
    music: Music? = null
) {
    Row (
        modifier = modifier.padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(RoundedCornerShape(20.dp))
        )

        Column (
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge
            )
            if (introduction != null) {
                Text(text = introduction)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        if (music != null) {
            MusicBox(music = music)
        }
    }
}

@Composable
private fun MusicBox(
    music: Music,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .border(1.dp, Color.Green, RoundedCornerShape(20.dp))
            .padding(vertical = 8.dp, horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = music.title + " - " + music.artist
        )
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_play_button),
            contentDescription = null,
            modifier = Modifier
                .size(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileItemPreview() {
    DiveTheme {
        ProfileItem(
            imageUrl = "https://i.pinimg.com/736x/be/e0/d0/bee0d0a2cf15e7d3a92da047a016bbb6.jpg",
            name = "이지현",
            introduction = "안녕하세요",
            music = Music("COLOR", "NCT WISH")
        )
    }
}