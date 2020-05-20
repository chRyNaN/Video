package com.chrynan.video.media

import com.chrynan.common.model.core.UriString
import com.chrynan.presentation.view.View
import com.google.android.exoplayer2.Player

interface MediaPlayerView : View {

    fun attachPlayer(player: Player)

    fun detachPlayer()

    fun showThumbnail(thumbnailUri: UriString?)

    fun showVideo()
}