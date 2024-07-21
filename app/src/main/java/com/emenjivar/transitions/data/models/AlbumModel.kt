package com.emenjivar.transitions.data.models

import androidx.annotation.DrawableRes
import com.emenjivar.transitions.R
import com.emenjivar.transitions.ui.screens.albumDetail.AlbumDetailRoute
import com.emenjivar.transitions.ui.screens.common.OriginTransition
import kotlinx.serialization.Serializable

@Serializable
data class AlbumModel(
    val id: Int,
    @DrawableRes val cover: Int,
    val title: String,
    val artist: String
) {

    fun toRoute(
        originTransition: OriginTransition
    ) = AlbumDetailRoute(
        id = this.id,
        cover = this.cover,
        title = this.title,
        artist = this.artist,
        origin = originTransition.name
    )
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