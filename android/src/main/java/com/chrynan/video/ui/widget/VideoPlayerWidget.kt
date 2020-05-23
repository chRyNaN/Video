package com.chrynan.video.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.chrynan.common.model.core.UriString
import com.chrynan.video.R
import com.chrynan.video.media.MediaPlayerView
import com.google.android.exoplayer2.Player
import kotlinx.android.synthetic.main.layout_video_player_control.view.*
import kotlinx.android.synthetic.main.widget_video_player.view.*

class VideoPlayerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr),
    MediaPlayerView {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_video_player, this)

        videoPlayerWidgetPlayerView?.videoPlayerControlScreenSizeImageView?.setOnClickListener {
            orientation =
                if (orientation == Orientation.PORTRAIT) Orientation.LANDSCAPE else Orientation.PORTRAIT

            changeOrientationListeners.notifyChangeOrientation(orientation)
        }
    }

    val changeOrientationListeners = mutableSetOf<ChangeOrientationListener>()

    // TODO update this to work correctly
    private var orientation = Orientation.PORTRAIT

    override fun attachPlayer(player: Player) {
        videoPlayerWidgetPlayerView?.player = player
        requestLayout()
    }

    override fun detachPlayer() {
        videoPlayerWidgetPlayerView?.player = null
    }

    override fun showThumbnail(thumbnailUri: UriString?) {
        // TODO
    }

    override fun showVideo() {
        videoPlayerWidgetPlayerView?.visibility = View.VISIBLE
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