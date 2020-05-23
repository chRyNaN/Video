package com.chrynan.video.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import coil.api.load
import com.chrynan.common.model.core.UriString
import com.chrynan.video.R
import com.chrynan.video.media.MediaPlayerView
import com.google.android.exoplayer2.Player
import kotlinx.android.synthetic.main.widget_list_video_player.view.*

class ListVideoPlayerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr),
    MediaPlayerView {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_list_video_player, this)
    }

    override fun attachPlayer(player: Player) {
        widgetListVideoPlayerView?.player = player
        requestLayout()
    }

    override fun detachPlayer() {
        widgetListVideoPlayerView?.player = null
    }

    override fun showPreviewImage(previewImageUri: UriString?) {
        widgetListVideoPlayerPreviewImageView?.load(previewImageUri)
        widgetListVideoPlayerPreviewImageView?.visibility = View.VISIBLE
        widgetListVideoPlayerView?.visibility = View.GONE
    }

    override fun showVideo() {
        widgetListVideoPlayerView?.visibility = View.VISIBLE
        widgetListVideoPlayerPreviewImageView?.visibility = View.GONE
    }
}