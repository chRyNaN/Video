package com.chrynan.video.player

import com.chrynan.video.player.converter.PlayableConverter
import com.chrynan.video.player.exception.UnsupportedPlayableException
import com.google.android.exoplayer2.SimpleExoPlayer

class AndroidMediaController(
    private val exoPlayer: SimpleExoPlayer,
    private val playableConverter: PlayableConverter
) : MediaController {

    private var view: MediaPlayerView? = null

    private var unmutedVolumeLevel: Float = normalizeVolumeLevel(exoPlayer.volume)

    override var volume: Float = normalizeVolumeLevel(exoPlayer.volume)
        get() = exoPlayer.volume
        set(value) {
            val formattedValue = normalizeVolumeLevel(value)

            field = formattedValue

            exoPlayer.volume = formattedValue
            unmutedVolumeLevel = formattedValue
        }

    override val isPlaying: Boolean
        get() = exoPlayer.isPlaying

    override val isPlayingAd: Boolean
        get() = exoPlayer.isPlayingAd

    override val isMuted: Boolean
        get() = volume == MediaVolumeController.MUTED_VOLUME

    override fun attach(view: MediaPlayerView) {
        this.view = view
        view.widget.player = exoPlayer
    }

    override fun detach() {
        view?.widget?.player = null
        view = null
    }

    override fun release() {
        exoPlayer.release()
    }

    override fun load(source: Playable) {
        val mediaSource =
            playableConverter.convert(source) ?: throw UnsupportedPlayableException(source)

        exoPlayer.prepare(mediaSource)
    }

    override fun resume() {
        exoPlayer.playWhenReady = true
    }

    override fun pause() {
        exoPlayer.playWhenReady = false
    }

    override fun seekToDefaultPosition() {
        exoPlayer.seekToDefaultPosition()
    }

    override fun mute() {
        val currentVolume = volume

        if (currentVolume != MediaVolumeController.MUTED_VOLUME) {
            unmutedVolumeLevel = currentVolume
            volume = MediaVolumeController.MUTED_VOLUME
        }
    }

    override fun unmute() {
        volume = unmutedVolumeLevel
    }

    private fun normalizeVolumeLevel(volume: Float): Float =
        volume.coerceIn(MediaVolumeController.MIN_VOLUME, MediaVolumeController.MAX_VOLUME)
}