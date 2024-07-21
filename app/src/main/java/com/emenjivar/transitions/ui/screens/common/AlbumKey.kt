package com.emenjivar.transitions.ui.screens.common

enum class AlbumElement {
    IMAGE,
    TITLE,
    ARTIST,
    CONTAINER
}

data class AlbumKey(
    val albumId: Int,
    val elementType: AlbumElement
)