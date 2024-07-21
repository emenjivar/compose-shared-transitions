package com.emenjivar.transitions.ui.screens.albumDetail

import com.emenjivar.transitions.data.models.AlbumModel
import com.emenjivar.transitions.data.models.SongModel
import com.emenjivar.transitions.ui.screens.common.OriginTransition
import kotlinx.coroutines.flow.StateFlow

data class AlbumDetailUiState(
    val album: AlbumModel,
    val origin: OriginTransition,
    val songs: StateFlow<List<SongModel>>
)
