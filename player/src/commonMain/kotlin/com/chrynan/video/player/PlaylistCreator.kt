package com.chrynan.video.player

interface PlaylistCreator {

    fun <P : Playable> playlistOf(playables: Collection<P>): Playlist<P>

    fun <P : Playable> playlistOf(vararg playables: P): Playlist<P>
}