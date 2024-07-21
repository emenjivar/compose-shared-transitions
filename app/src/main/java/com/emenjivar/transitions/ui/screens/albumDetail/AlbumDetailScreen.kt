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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.emenjivar.transitions.R
import com.emenjivar.transitions.ui.components.DefaultIconButton
import com.emenjivar.transitions.ui.screens.common.AlbumElement
import com.emenjivar.transitions.ui.screens.common.AlbumKey
import com.emenjivar.transitions.ui.theme.LocalDimensions
import com.emenjivar.transitions.ui.theme.TransitionsTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AlbumDetailScreen(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    args: AlbumDetailRoute
) {
    with(sharedTransitionScope) {
        AlbumDetailContent(
            animatedContentScope = animatedContentScope,
            args = args,
            onNavBack = { navController.popBackStack() }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.AlbumDetailContent(
    animatedContentScope: AnimatedContentScope,
    args: AlbumDetailRoute,
    onNavBack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.sharedBounds(
            rememberSharedContentState(
                key = AlbumKey(
                    albumId = args.id,
                    elementType = AlbumElement.CONTAINER
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
                .padding(horizontal = LocalDimensions.spaceMed),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .sharedElement(
                        rememberSharedContentState(
                            key = AlbumKey(
                                albumId = args.id,
                                elementType = AlbumElement.IMAGE
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
                            elementType = AlbumElement.TITLE
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

            repeat(10) {
                Column(
                    modifier = Modifier
                        .animateVertical(
                            animatedContentScope,
                            delay = 70 * it
                        ),
                ) {
                    Text(
                        text = "Song #$it",
                        color = Color.Black,
                        fontSize = songFontSize
                    )
                    HorizontalDivider(color = Color.Black)
                }
            }
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
        enter = fadeIn(tween(delayMillis = delay)) + slideInVertically { it * 3 },
        exit = fadeOut(tween(delayMillis = delay)) + slideOutVertically { it * 3 }
    )
}

private val titleFontSize = 24.sp
private val descriptionFontSize = 13.sp
private val songFontSize = 13.sp

@Preview
@Composable
private fun AlbumDetailContentPreview() {
    TransitionsTheme {
//        AlbumDetailContent(
//            args = AlbumModel.preview1.toRoute()
//        )
    }
}