package com.emenjivar.transitions.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.emenjivar.transitions.data.models.SongModel
import com.emenjivar.transitions.ui.screens.common.FavoriteSongCard
import com.emenjivar.transitions.ui.theme.LocalDimensions

@Composable
@Stable
fun FavoritesToolRow(
    favoriteSongs: List<SongModel>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = horizontalSpace),
        horizontalArrangement = Arrangement.spacedBy(LocalDimensions.space),
    ) {
        items(favoriteSongs) { song ->
            FavoriteSongCard(
                song = song
            )
        }
    }
}

private val horizontalSpace = LocalDimensions.spaceHalf

@Preview
@Composable
private fun FavoritesToolRowPreview() {
    val songs = listOf(
        SongModel.preview1,
        SongModel.preview2
    )

    FavoritesToolRow(
        favoriteSongs = songs
    )
}