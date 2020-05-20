package com.chrynan.video.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.chrynan.video.R
import kotlinx.android.synthetic.main.layout_video_player_control.view.*
import kotlinx.android.synthetic.main.widget_video_player.view.*

class VideoPlayerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_video_player, this)

        videoPlayerWidgetPlayerView?.videoPlayerControlScreenSizeImageView?.setOnClickListener { }
    }

    val changeOrientationListeners = mutableSetOf<ChangeOrientationListener>()

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