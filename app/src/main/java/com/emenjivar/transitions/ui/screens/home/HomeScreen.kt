package com.emenjivar.transitions.ui.screens.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.emenjivar.transitions.data.models.AlbumModel
import com.emenjivar.transitions.ui.screens.common.OriginTransition
import com.emenjivar.transitions.ui.theme.LocalDimensions
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    viewModel: HomeViewModel = hiltViewModel()
) {
    with(sharedTransitionScope) {
        HomeContent(
            uiState = viewModel.uiState,
            animatedContentScope = animatedContentScope,
            onNavToAlbum = { album, origin ->
                navController.navigate(album.toRoute(origin))
            }
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeContent(
    uiState: HomeUiState,
    animatedContentScope: AnimatedContentScope,
    onNavToAlbum: (AlbumModel, OriginTransition) -> Unit
) {

    val albumGroping by uiState.albumGrouping.collectAsStateWithLifecycle()
    val favoriteAlbums by uiState.favoriteAlbums.collectAsStateWithLifecycle()

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
                        favoriteAlbums = favoriteAlbums,
                        animatedContentScope = animatedContentScope,
                        onClick = { album ->
                            onNavToAlbum(album, OriginTransition.TOOLBOX)
                        }
                    )
                }

                items(albumGroping) { grouping ->
                    AlbumsRow(
                        title = grouping.title,
                        albums = grouping.albums,
                        animatedContentScope = animatedContentScope,
                        onClickItem = { album ->
                            onNavToAlbum(album, OriginTransition.CARD)
                        }
                    )
                }
            }
        }
    }
}

private val topPadding = LocalDimensions.space

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun HomeContentPreview() {
    SharedTransitionLayoutPreview { transitionScope ->
        transitionScope.HomeContent(
            uiState = HomeUiState(
                albumGrouping = MutableStateFlow(emptyList()),
                favoriteAlbums = MutableStateFlow(emptyList())
            ),
            onNavToAlbum = { _, _ -> },
            animatedContentScope = this
        )
    }
}