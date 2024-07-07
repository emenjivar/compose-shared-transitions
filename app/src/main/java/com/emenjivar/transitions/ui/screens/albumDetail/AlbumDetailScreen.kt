package com.emenjivar.transitions.ui.screens.albumDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
import androidx.navigation.NavController
import com.emenjivar.transitions.R
import com.emenjivar.transitions.data.models.AlbumModel
import com.emenjivar.transitions.ui.components.DefaultIconButton
import com.emenjivar.transitions.ui.theme.LocalDimensions
import com.emenjivar.transitions.ui.theme.TransitionsTheme

@Composable
fun AlbumDetailScreen(
    navController: NavController,
    args: AlbumDetailRoute
) {
    AlbumDetailContent(
        args = args
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AlbumDetailContent(
    args: AlbumDetailRoute
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CenterAlignedTopAppBar(
                        title = {
                            Text(
                                text = args.artist,
                                color = Color.White
                            )
                        }
                    )
                },
                navigationIcon = {
                    DefaultIconButton(
                        iconRes = R.drawable.ic_arrow_back,
                        onClick = {}
                    )
                },
                actions = {
                    DefaultIconButton(
                        iconRes = R.drawable.ic_bookmark,
                        onClick = {}
                    )
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(horizontal = LocalDimensions.spaceMed),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier.size(250.dp),
                    shape = RoundedCornerShape(LocalDimensions.cornedRadius),
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
                    text = args.title,
                    fontSize = titleFontSize,
                    fontWeight = FontWeight.Medium,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(LocalDimensions.space))
                Text(
                    text = "Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.",
                    fontSize = descriptionFontSize,
                    lineHeight = 15.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
        }
    }
}

private val titleFontSize = 24.sp
private val descriptionFontSize = 13.sp

@Preview
@Composable
private fun AlbumDetailContentPreview() {
    TransitionsTheme {
        AlbumDetailContent(
            args = AlbumModel.preview1.toRoute()
        )
    }
}