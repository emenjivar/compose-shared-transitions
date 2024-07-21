package com.emenjivar.transitions.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emenjivar.transitions.data.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    albumRepository: AlbumRepository
) : ViewModel() {
    private val albumGrouping = albumRepository.getGroupedAlbums()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val favoriteAlbums = albumRepository
        .getAlbums()
        .map { it.shuffled() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    val uiState = HomeUiState(
        albumGrouping = albumGrouping,
        favoriteAlbums = favoriteAlbums
    )
}