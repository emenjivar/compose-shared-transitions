package com.emenjivar.transitions.data.repository

import com.emenjivar.transitions.R
import com.emenjivar.transitions.data.models.AlbumGroup
import com.emenjivar.transitions.data.models.AlbumModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.random.Random

interface AlbumRepository {
    fun getGroupedAlbums(): Flow<List<AlbumGroup>>
    fun getAlbums(): Flow<List<AlbumModel>>
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
            cover = R.drawable.cover_all_moon_shaped,
            title = "All Moon Shaped",
            artist = "Radiohead"
        ),
        AlbumModel(
            id = 7,
            cover = R.drawable.cover_lateralus,
            title = "Lateralus",
            artist = "Tool"
        ),
        AlbumModel(
            id = 8,
            cover = R.drawable.cover_red_roses,
            title = "Red Rose Speedway",
            artist = "Paul McCartney"
        ),
    )

    override fun getGroupedAlbums() = flowOf(
        listOf(
            AlbumGroup(
                title = "Recommended albums",
                albums = albums.shuffled().take(5).map { it.copy(id = Random.nextInt()) }
            ),
            AlbumGroup(
                title = "Vintage",
                albums = albums.shuffled().take(5).map { it.copy(id = Random.nextInt()) }
            ),
            AlbumGroup(
                title = "According to your mood",
                albums = albums.shuffled().take(5).map { it.copy(id = Random.nextInt()) }
            )
        )
    )

    override fun getAlbums() = flowOf(albums)

}
