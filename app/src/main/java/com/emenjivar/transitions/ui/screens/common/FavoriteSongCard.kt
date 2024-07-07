package com.emenjivar.transitions.ui.screens.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emenjivar.transitions.R
import com.emenjivar.transitions.data.models.AlbumModel
import com.emenjivar.transitions.data.models.SongModel

@Composable
fun FavoriteSongCard(
    song: SongModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.size(
            width = width,
            height = height
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Box {
            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = song.cover),
                contentScale = ContentScale.Crop,
                contentDescription = song.title
            )

            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp),
                text = song.title,
                textAlign = TextAlign.Center,
                fontSize = 10.sp,
                color = Color.White
            )
        }
    }
}

private val cornerRadius = 16.dp
private val width = 80.dp
private val height = 110.dp


@Preview
@Composable
private fun FaceCardPreview() {
    FavoriteSongCard(
        song = SongModel(
            id = 0,
            cover = R.drawable.cover_parachutes,
            title = "Sail to the moon",
            artist = "Radiohead"
        )
    )
}

