package com.emenjivar.transitions.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emenjivar.transitions.R
import com.emenjivar.transitions.data.models.AlbumModel
import com.emenjivar.transitions.ui.theme.LocalDimensions

@Composable
fun AlbumCard(
    album: AlbumModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Card(
            modifier = Modifier.size(coverSize),
            shape = RoundedCornerShape(LocalDimensions.cornedRadius)
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = album.cover),
                contentScale = ContentScale.Crop,
                contentDescription = album.title
            )
        }

        Spacer(modifier = Modifier.height(LocalDimensions.space))
        Text(
            text = album.title,
            fontSize = titleFontSize,
            color = Color.White
        )
        Text(
            text = album.artist,
            fontSize = artistFontSize,
            color = Color.White
        )
    }
}

private val coverSize = 150.dp
private val titleFontSize = 15.sp
private val artistFontSize = 10.sp

@Preview
@Composable
private fun AlbumCardPreview() {
    AlbumCard(
        album = AlbumModel(
            id = 0,
            title = "Parachutes",
            artist = "Coldplay",
            cover = R.drawable.cover_parachutes,
        )
    )
}