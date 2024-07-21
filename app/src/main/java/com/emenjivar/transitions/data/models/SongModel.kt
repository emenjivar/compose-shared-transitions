package com.emenjivar.transitions.data.models

import androidx.annotation.DrawableRes
import com.emenjivar.transitions.R

data class SongModel(
    val id: Int,
    val title: String,
    val artist: String,
    val duration: Long = 6000L,
    @DrawableRes val cover: Int
) {

    fun getFormattedTime(): String {
        val totalMinutes = duration / 1000
        val totalSeconds = totalMinutes / 60
        val seconds = totalSeconds % 60

        val minutesString = totalMinutes.toString().padStart(2, '0')
        val secondsString = seconds.toString().padStart(2, '0')
        return "$minutesString:$secondsString"
    }

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