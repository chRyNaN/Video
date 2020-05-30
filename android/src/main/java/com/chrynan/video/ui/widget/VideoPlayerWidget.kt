package com.chrynan.video.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import com.chrynan.common.model.core.UriString
import com.chrynan.video.R
import com.chrynan.video.media.MediaPlayerView
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.android.synthetic.main.layout_video_player_control.view.*
import kotlinx.android.synthetic.main.widget_video_player.view.*

class VideoPlayerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : PlayerView(context, attrs, defStyleAttr),
    MediaPlayerView {

    init {

    }

    val changeOrientationListeners = mutableSetOf<ChangeOrientationListener>()

    // TODO update this to work correctly
    private var orientation = Orientation.PORTRAIT

    override fun attachPlayer(player: Player) {
        this.player = player
        requestLayout()
    }

    override fun detachPlayer() {
        player = null
    }

    override fun showPreviewImage(previewImageUri: UriString?) {
        // TODO
    }

    override fun showVideo() {
        visibility = View.VISIBLE
    }

    private fun Collection<ChangeOrientationListener>.notifyChangeOrientation(orientation: Orientation) =
        forEach { it.onChangeOrientation(orientation) }

    interface ChangeOrientationListener {

        fun onChangeOrientation(orientation: Orientation)
    }

    enum class Orientation {

        PORTRAIT,
        LANDSCAPE
    }
}