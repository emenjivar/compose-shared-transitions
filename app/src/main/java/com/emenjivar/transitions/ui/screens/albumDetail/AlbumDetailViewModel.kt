package com.emenjivar.transitions.ui.screens.albumDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emenjivar.transitions.data.models.SongModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(): ViewModel() {

    private val mockTitles = listOf(
        "The Golden Age",
        "Paper Tiger",
        "Guess I'm Doing Fine",
        "Lonesome Tears",
        "Lost Cause",
        "End Of The Day",
        "It's All In Your Ming",
        "Sunday Day",
        "Side Of The Road"
    )

    private val songs = flowOf(
        mockTitles.mapIndexed { index, title ->
            SongModel(
                id = index,
                title = title,
                artist = "Artist",
                cover = 0,
                duration = Random.nextLong() % 1_800
            )
        }
    ).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = emptyList()
    )

    val uiState = AlbumDetailUiState(
        songs = songs
    )
}