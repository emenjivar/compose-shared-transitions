package com.emenjivar.transitions.data.models

import androidx.annotation.DrawableRes
import com.emenjivar.transitions.R

data class SongModel(
    val id: Int,
    val title: String,
    val artist: String,
    @DrawableRes val cover: Int
) {
    companion object {
        val preview1 = SongModel(
            id = 0,
            cover = R.drawable.cover_parachutes,
            artist = "Coldplay",
            title = "Trouble"
        )

        val preview2 = SongModel(
            id = 1,
            cover = R.drawable.cover_sail_moon,
            artist = "Radiohead",
            title = "Sail to the moon"
        )
    }
}