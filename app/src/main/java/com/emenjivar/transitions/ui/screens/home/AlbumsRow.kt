package com.emenjivar.transitions.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.emenjivar.transitions.data.models.AlbumModel
import com.emenjivar.transitions.ui.screens.common.AlbumCard
import com.emenjivar.transitions.ui.theme.LocalDimensions

@Composable
@Stable
fun AlbumsRow(
    title: String,
    albums: List<AlbumModel>,
    modifier: Modifier = Modifier,
    onClickItem: (AlbumModel) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(start = LocalDimensions.spaceMed)
                .padding(bottom = LocalDimensions.space),
            text = title,
            color = Color.White,
            fontSize = titleFontSize,
            fontWeight = FontWeight.Medium
        )

        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(horizontalSpace),
            contentPadding = PaddingValues(horizontal = horizontalSpace)
        ) {
            items(albums) { album ->
                AlbumCard(
                    modifier = Modifier.clickable {
                        onClickItem(album)
                    },
                    album = album
                )
            }
        }
    }
}

private val titleFontSize = 18.sp
private val horizontalSpace = LocalDimensions.space

@Preview
@Composable
private fun AlbumsRowPreview() {
    val albums = listOf(
        AlbumModel.preview1,
        AlbumModel.preview2
    )
    AlbumsRow(
        title = "Recommended songs",
        albums = albums,
        onClickItem = {}
    )
}