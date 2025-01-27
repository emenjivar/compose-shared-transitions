package com.emenjivar.transitions.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun DefaultIconButton(
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
    tintColor: Color = Color.White,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            tint = tintColor,
            contentDescription = null
        )
    }
}
