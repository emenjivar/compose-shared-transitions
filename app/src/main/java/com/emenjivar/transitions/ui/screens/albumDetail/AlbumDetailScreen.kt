package com.emenjivar.transitions.ui.screens.albumDetail

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.emenjivar.transitions.R
import com.emenjivar.transitions.data.models.AlbumModel
import com.emenjivar.transitions.ui.components.DefaultIconButton
import com.emenjivar.transitions.ui.screens.common.AlbumElement
import com.emenjivar.transitions.ui.screens.common.AlbumKey
import com.emenjivar.transitions.ui.screens.common.OriginTransition
import com.emenjivar.transitions.ui.screens.home.SharedTransitionLayoutPreview
import com.emenjivar.transitions.ui.theme.LocalDimensions
import kotlinx.coroutines.flow.MutableStateFlow

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AlbumDetailScreen(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    args: AlbumDetailRoute,
    viewModel: AlbumDetailViewModel = hiltViewModel()
) {
    with(sharedTransitionScope) {
        AlbumDetailContent(
            animatedContentScope = animatedContentScope,
            args = args,
            uiState = viewModel.uiState,
            onNavBack = { navController.popBackStack() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AlbumDetailContent(
    animatedContentScope: AnimatedContentScope,
    args: AlbumDetailRoute,
    uiState: AlbumDetailUiState,
    onNavBack: () -> Unit
) {
    val songs by uiState.songs.collectAsStateWithLifecycle()
    Scaffold(
        modifier = Modifier.sharedBounds(
            rememberSharedContentState(
                key = AlbumKey(
                    albumId = args.id,
                    elementType = AlbumElement.CONTAINER,
                    origin = args.getOriginTransition()
                )
            ),
            animatedVisibilityScope = animatedContentScope
        ),
        topBar = {
            TopAppBar(
                title = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                modifier = Modifier
                                    .animateFadeIn(animatedContentScope),
                                text = args.artist,
                                color = Color.Black
                            )
                        }
                    )
                },
                navigationIcon = {
                    DefaultIconButton(
                        iconRes = R.drawable.ic_arrow_back,
                        tintColor = Color.Black,
                        onClick = onNavBack,
                    )
                },
                actions = {
                    DefaultIconButton(
                        iconRes = R.drawable.ic_bookmark,
                        tintColor = Color.Black,
                        onClick = {}
                    )
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
                .padding(horizontal = LocalDimensions.spaceMed)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(
                            key = AlbumKey(
                                albumId = args.id,
                                elementType = AlbumElement.IMAGE,
                                origin = args.getOriginTransition()
                            )
                        ),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .size(250.dp),
                shape = RoundedCornerShape(LocalDimensions.cornedRadius),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 15.dp
                )
            ) {
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxSize(),
                    painter = painterResource(id = args.cover),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(LocalDimensions.spaceDouble))
            Text(
                modifier = Modifier.sharedBounds(
                    rememberSharedContentState(
                        key = AlbumKey(
                            albumId = args.id,
                            elementType = AlbumElement.TITLE,
                            origin = args.getOriginTransition()
                        )
                    ),
                    animatedVisibilityScope = animatedContentScope
                ),
                text = args.title,
                fontSize = titleFontSize,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(LocalDimensions.space))
            Text(
                modifier = Modifier.animateFadeIn(
                    animatedContentScope,
                ),
                text = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.",
                fontSize = descriptionFontSize,
                lineHeight = 15.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color.Black.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(LocalDimensions.spaceMed))
            songs.forEachIndexed { index, song ->
                SongItem(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = LocalDimensions.spaceQuarter)
                        .animateVertical(
                            animatedContentScope,
                            delay = 70 * index
                        ),
                    song = song
                )
            }
            Spacer(modifier = Modifier.height(LocalDimensions.spaceMed))
        }
    }
}

private fun Modifier.animateFadeIn(
    animatedContentScope: AnimatedContentScope
) = with(animatedContentScope) {
    animateEnterExit(
        enter = fadeIn(),
        exit = fadeOut()
    )
}

private fun Modifier.animateVertical(
    animatedContentScope: AnimatedContentScope,
    delay: Int
) = with(animatedContentScope) {
    animateEnterExit(
        enter = fadeIn(tween(delayMillis = delay)) + slideInVertically { it * 4 },
        exit = fadeOut(tween(delayMillis = delay)) + slideOutVertically { it * 4 }
    )
}

private val titleFontSize = 24.sp
private val descriptionFontSize = 13.sp

@OptIn(ExperimentalSharedTransitionApi::class)
@Preview
@Composable
private fun AlbumDetailContentPreview() {
    SharedTransitionLayoutPreview { transitionScope ->
        transitionScope.AlbumDetailContent(
            args = AlbumModel.preview1.toRoute(OriginTransition.TOOLBOX),
            animatedContentScope = this,
            onNavBack = {},
            uiState = AlbumDetailUiState(
                songs = MutableStateFlow(emptyList())
            )
        )
    }
}