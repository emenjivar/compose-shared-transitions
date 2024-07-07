package com.emenjivar.transitions.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.emenjivar.transitions.R
import com.emenjivar.transitions.data.models.AlbumModel
import com.emenjivar.transitions.data.models.SongModel
import com.emenjivar.transitions.ui.theme.LocalDimensions
import kotlinx.serialization.Serializable

@Composable
fun HomeScreen(
    navController: NavController
) {
    HomeContent()
}

@Composable
fun HomeContent() {
    val favoriteSongs = remember {
        listOf(
            SongModel(
                id = 0,
                cover = R.drawable.cover_parachutes,
                artist = "Coldplay",
                title = "Trouble"
            ),
            SongModel(
                id = 1,
                cover = R.drawable.cover_sail_moon,
                artist = "Radiohead",
                title = "Sail to the moon"
            ),
            SongModel(
                id = 0,
                cover = R.drawable.cover_parachutes,
                artist = "Coldplay",
                title = "Trouble"
            ),
            SongModel(
                id = 0,
                cover = R.drawable.cover_parachutes,
                artist = "Coldplay",
                title = "Trouble"
            ),
            SongModel(
                id = 1,
                cover = R.drawable.cover_sail_moon,
                artist = "Radiohead",
                title = "Sail to the moon"
            ),
            SongModel(
                id = 0,
                cover = R.drawable.cover_parachutes,
                artist = "Coldplay",
                title = "Trouble"
            ),
        )
    }

    val albums = remember {
        listOf(
            AlbumModel(
                id = 0,
                cover = R.drawable.cover_parachutes,
                title = "Parachutes",
                artist = "Coldplay"
            ),
            AlbumModel(
                id = 1,
                cover = R.drawable.cover_sail_moon,
                title = "Hail to the thief",
                artist = "Radiohead"
            ),
            AlbumModel(
                id = 0,
                cover = R.drawable.cover_parachutes,
                title = "Parachutes",
                artist = "Coldplay"
            ),
            AlbumModel(
                id = 1,
                cover = R.drawable.cover_sail_moon,
                title = "Hail to the thief",
                artist = "Radiohead"
            )
        )
    }

    Scaffold { paddingValues ->
        Surface {
            LazyColumn(
                modifier = Modifier.padding(paddingValues),
                verticalArrangement = Arrangement.spacedBy(LocalDimensions.space),
                contentPadding = PaddingValues(bottom = LocalDimensions.spaceMed)
            ) {
                item {
                    FavoritesToolRow(
                        modifier = Modifier.padding(top = topPadding),
                        favoriteSongs = (favoriteSongs + favoriteSongs + favoriteSongs).shuffled()
                    )
                }

                item {
                    AlbumsRow(
                        title = "Recommended songs",
                        albums = albums.shuffled()
                    )
                }

                item {
                    AlbumsRow(
                        title = "Vintage",
                        albums = albums.shuffled()
                    )
                }

                item {
                    AlbumsRow(
                        title = "Mixed albums",
                        albums = albums.shuffled()
                    )
                }
            }
        }
    }
}

private val topPadding = LocalDimensions.space

@Serializable
object HomeRoute

@Preview
@Composable
private fun HomeContentPreview() {
    HomeContent()
}