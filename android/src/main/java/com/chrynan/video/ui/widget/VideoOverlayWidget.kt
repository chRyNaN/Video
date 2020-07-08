package com.chrynan.video.ui.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.view.OverlayView
import com.chrynan.video.ui.view.VideoOverlayView
import com.chrynan.video.ui.view.VideoPlayerView
import com.google.android.exoplayer2.ui.PlayerView
import kotlinx.android.synthetic.main.widget_video_overlay.view.*

// Don't get rid of this class, it will come back and be used again
class VideoOverlayWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseExpandableOverlayWidget(context, attrs, defStyleAttr),
    OverlayView,
    VideoOverlayView,
    VideoPlayerView {

    override val layoutResourceID = R.layout.widget_video_overlay

    override val sceneResourceID = R.xml.scene_video_overlay

    override val expandedHeight: Int
        get() = (parent as View).height

    override val collapsedHeight by lazy { context.resources.getDimensionPixelSize(R.dimen.video_collapsed_default_height) }

    override fun isInExpandableWidgetBounds(x: Float, y: Float, progress: Float): Boolean {
        val top = videoOverlayVideoPlayerWidget?.top ?: 0
        val bottom = videoOverlayVideoPlayerWidget?.bottom ?: 0

        return y.toInt() in top..bottom
    }

    override fun setupAdapter(
        adapter: RecyclerViewAdapter,
        layoutManager: RecyclerView.LayoutManager,
        decorator: RecyclerView.ItemDecoration
    ) {
        videoOverlayRecyclerView?.layoutManager = layoutManager
        videoOverlayRecyclerView?.adapter = adapter
        videoOverlayRecyclerView?.addItemDecoration(decorator)
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