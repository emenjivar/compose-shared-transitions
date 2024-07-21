package com.emenjivar.transitions.ui.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
@Stable
fun SharedTransitionScope.AlbumsRow(
    title: String,
    albums: List<AlbumModel>,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
    onClickItem: (AlbumModel) -> Unit
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier
                .padding(start = LocalDimensions.spaceMed)
                .padding(bottom = LocalDimensions.space),
            text = title,
            color = Color.Black,
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
                    album = album,
                    animatedContentScope = animatedContentScope
                )
            }
        }
    }
}

private val titleFontSize = 18.sp
private val horizontalSpace = LocalDimensions.space

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun AlbumsRowPreview() {
    val albums = listOf(
        AlbumModel.preview1,
        AlbumModel.preview2
    )
    SharedTransitionLayoutPreview { transitionScope ->
        transitionScope.AlbumsRow(
            title = "Recommended songs",
            albums = albums,
            animatedContentScope = this,
            onClickItem = {}
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionLayoutPreview(
    content: @Composable AnimatedContentScope.(transitionScope: SharedTransitionScope) -> Unit
) {
    SharedTransitionLayout {
        AnimatedContent(
            targetState = true,
            label = "preview"
        ) { display ->
            if (display) {
                content(this@SharedTransitionLayout)
            }
        }
    }
}