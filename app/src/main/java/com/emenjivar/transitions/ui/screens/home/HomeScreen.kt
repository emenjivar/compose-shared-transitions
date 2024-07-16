package com.emenjivar.transitions.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.emenjivar.transitions.R
import com.emenjivar.transitions.data.models.AlbumModel
import com.emenjivar.transitions.data.models.SongModel
import com.emenjivar.transitions.ui.theme.LocalDimensions
import com.emenjivar.transitions.ui.theme.TransitionsTheme
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    HomeContent(
        uiState = viewModel.uiState,
        onNavToAlbum = { album ->
            navController.navigate(album.toRoute())
        }
    )
}

@Composable
fun HomeContent(
    uiState: HomeUiState,
    onNavToAlbum: (AlbumModel) -> Unit
) {
    val albumGroping by uiState.albumGrouping.collectAsStateWithLifecycle()
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
                        favoriteSongs = (favoriteSongs + favoriteSongs + favoriteSongs)
                    )
                }

                items(albumGroping) { grouping ->
                    AlbumsRow(
                        title = grouping.title,
                        albums = grouping.albums,
                        onClickItem = onNavToAlbum
                    )
                }
            }
        }
    }
}

private val topPadding = LocalDimensions.space

@Preview
@Composable
private fun HomeContentPreview() {
    TransitionsTheme {
        HomeContent(
            uiState = HomeUiState(
                albumGrouping = MutableStateFlow(emptyList()),
                favoriteSongs = MutableStateFlow(emptyList())
            ),
            onNavToAlbum = {}
        )
    }
}