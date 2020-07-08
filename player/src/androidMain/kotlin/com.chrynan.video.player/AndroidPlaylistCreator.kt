package com.chrynan.video.player

import com.chrynan.video.player.converter.PlayableConverter

class AndroidPlaylistCreator(private val converter: PlayableConverter) : PlaylistCreator {

    override fun <P : Playable> playlistOf(playables: Collection<P>): Playlist<P> =
        AndroidPlaylist<P>(converter).apply { addAll(playables) }

    override fun <P : Playable> playlistOf(vararg playables: P): Playlist<P> =
        AndroidPlaylist<P>(converter).apply { addAll(playables.toList()) }
}