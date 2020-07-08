package com.chrynan.video.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.chrynan.video.R
import com.chrynan.video.player.MediaPlayerView
import com.google.android.exoplayer2.ui.PlayerView

class ListVideoPlayerWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr),
    MediaPlayerView {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_list_video_player, this)
    }

    override val widget: PlayerView
        get() = TODO("Not yet implemented")

    override fun setPreviewImage(drawable: Drawable?) {
        TODO("Not yet implemented")
    }

    override fun setPreviewImage(uri: String?) {
        TODO("Not yet implemented")
    }

    override fun togglePreviewImageVisibility(isVisible: Boolean) {
        TODO("Not yet implemented")
    }
}