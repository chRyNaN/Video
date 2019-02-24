package com.chrynan.video.ui.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout

class VideoMotionLayout(context: Context, attrs: AttributeSet? = null) : MotionLayout(context, attrs),
    MotionLayout.TransitionListener {

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

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if ((event.actionMasked == MotionEvent.ACTION_UP) or (event.actionMasked == MotionEvent.ACTION_CANCEL)) {
            touchStarted = false
            return super.onTouchEvent(event)
        }

        if (!touchStarted) {
            videoContainerView?.getHitRect(viewRect)
            touchStarted = viewRect.contains(event.x.toInt(), event.y.toInt())
        }

        return touchStarted and super.onTouchEvent(event)
    }

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) =
        updateListenersWithTransitionState()

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) = updateListenersWithTransitionState()

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) =
        updateListenersWithTransitionState()

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) = updateListenersWithTransitionState()

    private fun updateListenersWithTransitionState() {
        val state = when {
            progress == 0f -> VideoTransitionState.Collapsed
            progress == 1f -> VideoTransitionState.Expanded
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

        object Collapsed : VideoTransitionState(0f)

        object Expanded : VideoTransitionState(1f)

        class Collapsing(progress: Float) : VideoTransitionState(progress)

        class Expanding(progress: Float) : VideoTransitionState(progress)

        class Steady(progress: Float) : VideoTransitionState(progress)
    }

    interface VideoTransitionStateListener {

        fun onTransitionStateChange(state: VideoTransitionState)
    }
}