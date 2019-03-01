package com.chrynan.video.ui.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout

class VideoMotionLayout(context: Context, attrs: AttributeSet? = null) : MotionLayout(context, attrs),
    MotionLayout.TransitionListener {

    companion object {

        private const val EXPANDED = 1f
        private const val COLLAPSED = 0f
    }

    val isVideoExpanded: Boolean
        get() = progress == EXPANDED

    val isVideoCollapsed: Boolean
        get() = progress == COLLAPSED

    var videoContainerView: ViewGroup? = null

    var currentState: VideoTransitionState = VideoTransitionState.Collapsed
        set(value) {
            field = value

            if (progress != value.progress) {
                progress = value.progress
            }
        }

    var videoTransitionStateListeners: List<VideoTransitionStateListener> = emptyList()

    private val viewRect = Rect()

    private var touchStarted = false

    init {
        setTransitionListener(this)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (videoContainerView == null) return super.onInterceptTouchEvent(event)

        if (event.action == MotionEvent.ACTION_DOWN) {
            touchStarted = event.isInVideoTouchBounds() and !isTransitionInProgress()
        }

        if ((event.action == MotionEvent.ACTION_CANCEL) or (event.action == MotionEvent.ACTION_UP)) {
            touchStarted = false
        }

        return super.onInterceptTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (videoContainerView == null) return super.onTouchEvent(event)

        if (event.action == MotionEvent.ACTION_DOWN) {
            touchStarted = event.isInVideoTouchBounds() and !isTransitionInProgress()
        }

        if ((event.action == MotionEvent.ACTION_CANCEL) or (event.action == MotionEvent.ACTION_UP)) {
            touchStarted = false
        }

        return super.onTouchEvent(event)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        if (touchStarted) super.onNestedPreScroll(target, dx, dy, consumed, type)
    }

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) =
        updateListenersWithTransitionState()

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) = updateListenersWithTransitionState()

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) =
        updateListenersWithTransitionState()

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) = updateListenersWithTransitionState()

    fun expand() {
        transitionToEnd()
    }

    fun collapse() {
        transitionToStart()
    }

    private fun isTransitionInProgress() = (progress > COLLAPSED) and (progress < EXPANDED)

    private fun MotionEvent.isInVideoTouchBounds(): Boolean {
        videoContainerView?.getHitRect(viewRect)
        return viewRect.contains(x.toInt(), y.toInt())
    }

    private fun updateListenersWithTransitionState() {
        val state = when {
            progress == COLLAPSED -> VideoTransitionState.Collapsed
            progress == EXPANDED -> VideoTransitionState.Expanded
            progress < currentState.progress -> VideoTransitionState.Collapsing(progress)
            progress > currentState.progress -> VideoTransitionState.Expanding(progress)
            else -> VideoTransitionState.Steady(progress = progress)
        }

        currentState = state

        for (listener in videoTransitionStateListeners) {
            listener.onTransitionStateChange(state)
        }
    }

    sealed class VideoTransitionState(val progress: Float) {

        object Collapsed : VideoTransitionState(COLLAPSED)

        object Expanded : VideoTransitionState(EXPANDED)

        class Collapsing(progress: Float) : VideoTransitionState(progress)

        class Expanding(progress: Float) : VideoTransitionState(progress)

        class Steady(progress: Float) : VideoTransitionState(progress)
    }

    interface VideoTransitionStateListener {

        fun onTransitionStateChange(state: VideoTransitionState)
    }
}