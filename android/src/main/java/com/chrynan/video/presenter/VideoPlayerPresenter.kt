package com.chrynan.video.presenter

import android.net.Uri
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.player.MediaController
import com.chrynan.video.player.PlaylistCreator
import com.chrynan.video.player.mediaSource
import com.chrynan.video.player.play
import com.chrynan.video.ui.view.VideoPlayerView
import javax.inject.Inject

class VideoPlayerPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val mediaController: MediaController,
    private val playlistCreator: PlaylistCreator,
    private val view: VideoPlayerView
) : BasePresenter(dispatchers) {

    fun loadVideo(videoUri: Uri?) {
        if (videoUri != null) {
            mediaController.attach(view)

            mediaController.play(mediaSource(uri = videoUri))
        }
    }
}