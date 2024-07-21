package com.emenjivar.transitions.ui.screens.common

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.emenjivar.transitions.data.models.AlbumModel
import com.emenjivar.transitions.ui.screens.home.SharedTransitionLayoutPreview

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.FavoriteAlbumCard(
    album: AlbumModel,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .sharedBounds(
                rememberSharedContentState(
                    key = AlbumKey(
                        albumId = album.id,
                        elementType = AlbumElement.CONTAINER,
                        origin = OriginTransition.TOOLBOX
                    )
                ),
                animatedVisibilityScope = animatedContentScope
            )
            .size(
                width = width,
                height = height
            )
        ,
        shape = RoundedCornerShape(cornerRadius),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 15.dp
        )
    ) {
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(cornerRadius))
                .sharedElement(
                    rememberSharedContentState(
                        key = AlbumKey(
                            albumId = album.id,
                            elementType = AlbumElement.IMAGE,
                            origin = OriginTransition.TOOLBOX
                        )
                    ),
                    animatedVisibilityScope = animatedContentScope
                )
                .fillMaxWidth(),
            painter = painterResource(id = album.cover),
            contentScale = ContentScale.Crop,
            contentDescription = album.title
        )
    }
}

private val cornerRadius = 16.dp
private val width = 80.dp
private val height = 110.dp

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun FaceCardPreview() {
    SharedTransitionLayoutPreview { transitionScope ->
        transitionScope.FavoriteAlbumCard(
            album = AlbumModel.preview1,
            animatedContentScope = this
        )
    }
}

