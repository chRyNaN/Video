package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.player.MediaController
import com.chrynan.video.player.PlaylistCreator
import com.chrynan.video.ui.view.VideoPlayerView
import javax.inject.Inject

class VideoPlayerPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    //private val mediaController: MediaController,
    //private val playlistCreator: PlaylistCreator,
    private val view: VideoPlayerView
) : BasePresenter(dispatchers) {

    fun loadVideo() {
        //val source = mediaSourceCreator.fromUri("https://www.w3schools.com/html/mov_bbb.mp4")

        //view.attachPlayer(mediaController.player)
        //mediaController.load(source)
        //mediaController.resume()
    }
}