package com.emenjivar.transitions.ui.screens.home

import com.emenjivar.transitions.data.models.AlbumGroup
import com.emenjivar.transitions.data.models.SongModel
import kotlinx.coroutines.flow.StateFlow

data class HomeUiState(
    val albumGrouping: StateFlow<List<AlbumGroup>>,
    val favoriteSongs: StateFlow<List<SongModel>>
)
