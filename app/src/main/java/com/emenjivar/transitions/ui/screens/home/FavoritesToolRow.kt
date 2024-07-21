package com.emenjivar.transitions.ui.screens.home

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.emenjivar.transitions.data.models.AlbumModel
import com.emenjivar.transitions.ui.screens.common.FavoriteAlbumCard
import com.emenjivar.transitions.ui.theme.LocalDimensions

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Stable
fun SharedTransitionScope.FavoritesToolRow(
    favoriteAlbums: List<AlbumModel>,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
    onClick: (AlbumModel) -> Unit
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = horizontalSpace),
        horizontalArrangement = Arrangement.spacedBy(LocalDimensions.space),
    ) {
        items(favoriteAlbums) { album ->
            FavoriteAlbumCard(
                modifier = Modifier.clickable { onClick(album) },
                album = album,
                animatedContentScope = animatedContentScope
            )
        }
    }
}

private val horizontalSpace = LocalDimensions.spaceHalf

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun FavoritesToolRowPreview() {
    val albums = listOf(
        AlbumModel.preview1,
        AlbumModel.preview2
    )

    SharedTransitionLayoutPreview { transitionScope ->
        transitionScope.FavoritesToolRow(
            favoriteAlbums = albums,
            animatedContentScope = this,
            onClick = {}
        )

    }
}