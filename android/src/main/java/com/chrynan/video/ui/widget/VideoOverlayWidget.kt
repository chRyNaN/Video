package com.chrynan.video.ui.widget

import android.content.Context
import android.util.AttributeSet
import com.chrynan.video.R
import kotlinx.android.synthetic.main.widget_video_overlay.view.*

class VideoOverlayWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseExpandableOverlayWidget(context, attrs, defStyleAttr) {

    override val layoutResourceID = R.layout.widget_video_overlay

    override val sceneResourceID = R.xml.scene_video_overlay

    override val expandedHeight: Int
        get() = 1000 // TODO update

    override val collapsedHeight by lazy { context.resources.getDimensionPixelSize(R.dimen.video_collapsed_default_height) }

    override fun isInExpandableWidgetBounds(x: Float, y: Float, progress: Float): Boolean {
        val top = videoOverlayVideoPlayerWidget?.top ?: 0
        val bottom = videoOverlayVideoPlayerWidget?.bottom ?: 0

        return y.toInt() in top..bottom
    }
}