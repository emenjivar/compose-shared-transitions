package com.emenjivar.transitions.ui.screens.albumDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emenjivar.transitions.data.models.SongModel
import com.emenjivar.transitions.ui.theme.LocalDimensions

@Composable
fun SongItem(
    song: SongModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black, shape = CircleShape)
                .size(CIRCLE_SIZE),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = song.id.toString(),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.width(LocalDimensions.spaceMed))

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = song.title,
                fontSize = 14.sp,
                color = Color.Black
            )

            Text(
                text = "${song.artist} - ${song.getFormattedTime()}",
                fontSize = 14.sp,
                color = Color.Black.copy(alpha = ALPHA_SUB_TEXT)
            )
        }
    }
}

private const val ALPHA_SUB_TEXT = 0.5f
private val CIRCLE_SIZE = 40.dp