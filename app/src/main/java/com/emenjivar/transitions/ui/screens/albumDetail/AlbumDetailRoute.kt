package com.emenjivar.transitions.ui.screens.albumDetail

import com.emenjivar.transitions.ui.screens.common.OriginTransition
import kotlinx.serialization.Serializable

/**
 * @param origin The string name for OriginTransition enum option.
 */
@Serializable
data class AlbumDetailRoute(
    val id: Int,
    val cover: Int,
    val title: String,
    val artist: String,
    private val origin: String
) {
    fun getOriginTransition() = when(origin) {
        OriginTransition.CARD.name -> OriginTransition.CARD
        else -> OriginTransition.TOOLBOX
    }
}