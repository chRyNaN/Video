package com.chrynan.video.ui.transition

import android.content.Context
import com.chrynan.video.ui.view.VideoView
import com.chrynan.video.ui.widget.VideoMotionLayout

class CollapsingVideoTransitionStateListener(
    private val context: Context,
    private val videoView: VideoView
) : VideoMotionLayout.VideoTransitionStateListener {

    private val expandedHeight by lazy { 250 * context.resources.displayMetrics.density }
    private val collapsedHeight by lazy { 80 * context.resources.displayMetrics.density }
    private val collapsedWidth by lazy { 120 * context.resources.displayMetrics.density }

    private val expandedWidth: Int
        get() = videoView.containerWidth

    override fun onTransitionStateChange(state: VideoMotionLayout.VideoTransitionState) {
        videoView.videoHeight = (expandedHeight - ((1 - state.progress) * (expandedHeight - collapsedHeight))).toInt()
        videoView.videoWidth = (expandedWidth - ((1 - 4 * state.progress) * (expandedWidth - collapsedWidth))).toInt()
            .coerceAtMost(expandedWidth)
    }
}