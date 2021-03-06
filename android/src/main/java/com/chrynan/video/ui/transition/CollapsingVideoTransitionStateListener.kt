package com.chrynan.video.ui.transition

import com.chrynan.expandable.ExpandableState
import com.chrynan.expandable.ExpandableStateListener
import com.chrynan.video.ui.view.CollapsibleVideoView
import com.chrynan.video.utils.ApplicationContext
import javax.inject.Inject

class CollapsingVideoTransitionStateListener @Inject constructor(
    private val context: ApplicationContext,
    private val videoView: CollapsibleVideoView
) : ExpandableStateListener {

    private val expandedHeight by lazy { 250 * context.resources.displayMetrics.density }
    private val collapsedHeight by lazy { 80 * context.resources.displayMetrics.density }
    private val collapsedWidth by lazy { 120 * context.resources.displayMetrics.density }

    private val expandedWidth: Int
        get() = videoView.containerWidth

    override fun onExpandableStateChange(state: ExpandableState) {
        val videoWidth = (expandedWidth - ((1 - 4 * state.progress) * (expandedWidth - collapsedWidth))).toInt()
            .coerceAtMost(expandedWidth)
        videoView.videoWidth = videoWidth
        videoView.videoHeight = (expandedHeight - ((1 - state.progress) * (expandedHeight - collapsedHeight))).toInt()

        val alphaMaxWidth = expandedWidth - videoView.collapsedPlayIconWidth - videoView.collapsedCancelIconWidth
        val alpha = if (state.progress == 0f) 1f else 1 - (videoWidth / alphaMaxWidth.toFloat()).coerceIn(0f, 1f)

        videoView.collapsedPlayIconAlpha = alpha
        videoView.collapsedCancelIconAlpha = alpha

        videoView.contentContainerAlpha = state.progress
    }
}