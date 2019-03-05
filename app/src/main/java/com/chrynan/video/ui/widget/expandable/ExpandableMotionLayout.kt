package com.chrynan.video.ui.widget.expandable

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import com.chrynan.kotlinutils.delegates.alias

class ExpandableMotionLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) :
    MotionLayout(context, attrs),
    MotionLayout.TransitionListener,
    ExpandableContainerView {

    override val expandableChildLayout: ExpandableChildLayout?
        get() = null

    override var expandedInteractionView: View? = null
        get() = expandableChildLayout?.expandedInteractionView
        set(value) {
            field = value
            expandableChildLayout?.expandedInteractionView = value
        }

    override var collapsedInteractionView: View? = null
        get() = expandableChildLayout?.expandedInteractionView
        set(value) {
            field = value
            expandableChildLayout?.collapsedInteractionView = value
        }

    private val stateManager = ExpandableStateManager()

    override val currentExpandableState by alias(stateManager::currentExpandableState)

    val expandableStateListeners by alias(stateManager::expandableStateListeners)

    init {
        setTransitionListener(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent) = false

    override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) = updateProgress()

    override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) = updateProgress()

    override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) = updateProgress()

    override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) = updateProgress()

    override fun addStateListener(listener: ExpandableStateListener) {
        expandableStateListeners.add(listener)
    }

    override fun removeStateListener(listener: ExpandableStateListener) {
        expandableStateListeners.remove(listener)
    }

    override fun expand() {
        if (!currentExpandableState.isExpanded) {
            transitionToEnd()
        }
    }

    override fun collapse() {
        if (!currentExpandableState.isCollapsed) {
            transitionToStart()
        }
    }

    private fun updateProgress() {
        stateManager.currentExpandableProgress = progress
    }
}