package com.emenjivar.transitions.ui.screens.albumDetail

import kotlinx.serialization.Serializable

@Serializable
data class AlbumDetailRoute(
    val id: Int,
    val cover: Int,
    val title: String,
    val artist: String,
)