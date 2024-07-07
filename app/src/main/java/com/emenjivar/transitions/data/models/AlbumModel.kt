package com.emenjivar.transitions.data.models

import androidx.annotation.DrawableRes
import com.emenjivar.transitions.R

data class AlbumModel(
    val id: Int,
    @DrawableRes val cover: Int,
    val title: String,
    val artist: String
) {
    companion object {
        val preview1 = AlbumModel(
            id = 0,
            cover = R.drawable.cover_parachutes,
            title = "Parachutes",
            artist = "Coldplay"
        )
        val preview2 = AlbumModel(
            id = 1,
            cover = R.drawable.cover_sail_moon,
            title = "Hail to the thief",
            artist = "Radiohead"
        )
    }
}