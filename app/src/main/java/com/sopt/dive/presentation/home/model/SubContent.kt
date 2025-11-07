package com.sopt.dive.presentation.home.model

sealed class SubContent {
    data object Birthday : SubContent()
    data class Song(val songName: String, val singer: String) : SubContent()
    data object None: SubContent()
}
