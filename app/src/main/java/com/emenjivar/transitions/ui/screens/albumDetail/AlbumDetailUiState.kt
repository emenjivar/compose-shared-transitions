package com.emenjivar.transitions.ui.screens.albumDetail

import com.emenjivar.transitions.data.models.SongModel
import kotlinx.coroutines.flow.StateFlow

data class AlbumDetailUiState(
    val songs: StateFlow<List<SongModel>>
)
