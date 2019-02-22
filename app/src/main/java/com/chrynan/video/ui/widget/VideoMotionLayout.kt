package com.chrynan.video.ui.widget

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import com.chrynan.video.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.layout_video_motion.view.*

class VideoMotionLayout(context: Context, attrs: AttributeSet? = null) : MotionLayout(context, attrs),
    MotionLayout.TransitionListener {

    val baseContainerView: ViewGroup?
        get() = baseContainerView

    val videoContainerView: ViewGroup?
        get() = videoFragmentContainer

    val bottomNavBar: BottomNavigationView
        get() = bottomNavigationView

    var videoTransitionStateListeners: List<VideoTransitionStateListener> = emptyList()

    private val viewRect = Rect()

    private var touchStarted = false

    init {
        LayoutInflater.from(context).inflate(R.layout.layout_video_motion, this)
        setTransitionListener(this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if ((event.actionMasked == MotionEvent.ACTION_UP) or (event.actionMasked == MotionEvent.ACTION_CANCEL)) {
            touchStarted = false
            return super.onTouchEvent(event)
        }

        if (!touchStarted) {
            videoFragmentContainer?.getHitRect(viewRect)
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
        val state = when (progress) {
            0f -> VideoTransitionState.Collapsed
            1f -> VideoTransitionState.Expanded
            else -> VideoTransitionState.Transitioning(progress = progress)
        }

        for (listener in videoTransitionStateListeners) {
            listener.onTransitionStateChange(state)
        }
    }

    sealed class VideoTransitionState(val progress: Float) {

        object Collapsed : VideoTransitionState(0f)

        object Expanded : VideoTransitionState(1f)

        class Transitioning(progress: Float) : VideoTransitionState(progress)
    }

    interface VideoTransitionStateListener {

        fun onTransitionStateChange(state: VideoTransitionState)
    }
}