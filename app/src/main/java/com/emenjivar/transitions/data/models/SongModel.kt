package com.emenjivar.transitions.data.models

import androidx.annotation.DrawableRes

data class SongModel(
    val id: Int,
    val title: String,
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
}