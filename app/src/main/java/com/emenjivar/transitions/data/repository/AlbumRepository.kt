package com.emenjivar.transitions.data.repository

import com.emenjivar.transitions.R
import com.emenjivar.transitions.data.models.AlbumGroup
import com.emenjivar.transitions.data.models.AlbumModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface AlbumRepository {
    fun getRecommended(): Flow<AlbumGroup>
    fun getVintage(): Flow<AlbumGroup>
}

class AlbumRepositoryImp : AlbumRepository {
    private val albums = listOf(
        AlbumModel(
            id = 0,
            cover = R.drawable.cover_parachutes,
            title = "Parachutes",
            artist = "Coldplay"
        ),
        AlbumModel(
            id = 1,
            cover = R.drawable.cover_sail_moon,
            title = "Hail to the thief",
            artist = "Radiohead"
        ),
        AlbumModel(
            id = 3,
            cover = R.drawable.cover_universal_mother,
            title = "Universal mother",
            artist = "Sinead o'connor"
        ),
        AlbumModel(
            id = 4,
            cover = R.drawable.cover_ok_computer,
            title = "Ok computer",
            artist = "Radiohead"
        ),
        AlbumModel(
            id = 5,
            cover = R.drawable.cover_marshall_mathers,
            title = "Marshall mathers",
            artist = "Eminem"
        ),
        AlbumModel(
            id = 6,
            cover = R.drawable.cover_mercedes_sosa,
            title = "Censurada",
            artist = "Mercedes sosa"
        )
    )

    override fun getRecommended() = flowOf(
        AlbumGroup(
            title = "Recommended albums",
            albums = albums.shuffled().take(4)
        )
    )

    override fun getVintage() = flowOf(
        AlbumGroup(
            title = "Vintage",
            albums = albums.shuffled().take(4)
        )
    )
}
