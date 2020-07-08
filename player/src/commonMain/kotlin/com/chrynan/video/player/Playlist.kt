package com.chrynan.video.player

interface Playlist<P : Playable> : MutableList<P>,
    Playable {

    fun move(currentIndex: Int, newIndex: Int)
}
