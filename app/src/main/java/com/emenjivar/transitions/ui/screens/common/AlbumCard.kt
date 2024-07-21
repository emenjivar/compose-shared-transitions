package com.emenjivar.transitions.ui.screens.common

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.emenjivar.transitions.ui.screens.home.SharedTransitionLayoutPreview
import com.emenjivar.transitions.ui.theme.LocalDimensions

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AlbumCard(
    album: AlbumModel,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .sharedBounds(
                rememberSharedContentState(
                    key = AlbumKey(
                        albumId = album.id,
                        elementType = AlbumElement.CONTAINER,
                        origin = OriginTransition.CARD
                    )
                ),
                animatedVisibilityScope = animatedContentScope
            )
    ) {
        Card(
            modifier = Modifier
                .sharedElement(
                    rememberSharedContentState(
                        key = AlbumKey(
                            albumId = album.id,
                            elementType = AlbumElement.IMAGE,
                            origin = OriginTransition.CARD
                        )
                    ),
                    animatedVisibilityScope = animatedContentScope
                )
                .size(coverSize),
            shape = RoundedCornerShape(LocalDimensions.cornedRadius),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 15.dp
            )
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(),
                painter = painterResource(id = album.cover),
                contentScale = ContentScale.Crop,
                contentDescription = album.title
            )
        }

        Spacer(modifier = Modifier.height(LocalDimensions.space))
        Text(
            modifier = Modifier.sharedBounds(
                rememberSharedContentState(
                    key = AlbumKey(
                        albumId = album.id,
                        elementType = AlbumElement.TITLE,
                        origin = OriginTransition.CARD
                    )
                ),
                animatedVisibilityScope = animatedContentScope
            ),
            text = album.title,
            fontSize = titleFontSize,
            color = Color.Black
        )
        Text(
            modifier = Modifier.apply {
                with(animatedContentScope) {
                    animateEnterExit()
                }
            },
            text = album.artist,
            fontSize = artistFontSize,
            color = Color.Black
        )
    }
}

private val coverSize = 150.dp
private val titleFontSize = 15.sp
private val artistFontSize = 10.sp

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun AlbumCardPreview() {
    SharedTransitionLayoutPreview { transitionScope ->
        transitionScope.AlbumCard(
            album = AlbumModel(
                id = 0,
                title = "Parachutes",
                artist = "Coldplay",
                cover = R.drawable.cover_parachutes,
            ),
            animatedContentScope = this
        )
    }

}