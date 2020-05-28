package com.chrynan.video.ui.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.motion.widget.MotionLayout
import com.chrynan.video.model.ResourceID
import com.chrynan.video.ui.widget.gesture.CompleteGestureDetector
import kotlin.math.absoluteValue
import kotlin.math.roundToInt

abstract class BaseExpandableOverlayWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MotionLayout(context, attrs, defStyleAttr),
    CompleteGestureDetector.CompleteGestureListener {

    companion object {

        const val PROGRESS_EXPANDED = 1F
        const val PROGRESS_COLLAPSED = 0F

        private const val VELOCITY_MAX = 10F
    }

    abstract val layoutResourceID: ResourceID

    abstract val sceneResourceID: ResourceID

    abstract val expandedHeight: Int

    abstract val collapsedHeight: Int

    val isExpanded: Boolean
        get() = progress == PROGRESS_EXPANDED

    val isCollapsed: Boolean
        get() = progress == PROGRESS_COLLAPSED

    var progressChangedListener: ProgressChangedListener? = null
        set(value) {
            field = value

            if (value != null) {
                setTransitionListener(ProgressChangedTransitionListener(value))
            } else {
                setTransitionListener(null)
            }
        }

    private val gestureDetector by lazy { CompleteGestureDetector(context, this) }

    private val interpolator by lazy { AccelerateDecelerateInterpolator() }

    private val maxVerticalMotionDistance: Float
        get() = (expandedHeight - collapsedHeight).toFloat()

    private val touchSlop by lazy { ViewConfiguration.get(context).scaledTouchSlop }
    private val roundedSlop by lazy { 0.01F }

    private var downY = 0
    private var downIsInBounds = false
    private var lastY = 0
    private var downProgress = 0F
    private var isScrolling = false

    abstract fun isInExpandableWidgetBounds(x: Float, y: Float, progress: Float): Boolean

    fun setup() {
        LayoutInflater.from(context).inflate(layoutResourceID, this)

        progress == PROGRESS_COLLAPSED

        loadLayoutDescription(sceneResourceID)
    }

    fun expand() = animateToTransitionState(isExpanding = true, velocity = VELOCITY_MAX)

    fun collapse() = animateToTransitionState(isExpanding = false, velocity = VELOCITY_MAX)

    @SuppressLint("ClickableViewAccessibility")
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        // We have to check this in both onTouchEvent and here in onInterceptTouchEvent, because
        // while returning false from this function will allow lower Views on the z-axis to receive
        // touch events, child views will delegate back up to the onTouchEvent function.
        val isInBounds = isInExpandableWidgetBounds(x = event.x, y = event.y, progress = progress)

        //if (!isInBounds && event.action == MotionEvent.ACTION_DOWN) return false

        return true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // We have to check this in both onInterceptTouchEvent and here in onTouchEvent, because
        // while returning false from onInterceptTouchEvent will allow lower Views on the z-axis to
        // receive touch events, child views will delegate back up to this function.
        val isInBounds = isInExpandableWidgetBounds(x = event.x, y = event.y, progress = progress)

        if (isInBounds && event.action == MotionEvent.ACTION_DOWN) {
            downIsInBounds = true
            return gestureDetector.onTouch(event)
        } else if (!isInBounds && event.action == MotionEvent.ACTION_DOWN) {
            downIsInBounds = false
            return super.onTouchEvent(event)
        }

        if (downIsInBounds) {
            if (event.action != MotionEvent.ACTION_MOVE) {
                super.onTouchEvent(event)
            }

            return gestureDetector.onTouch(event)
        }

        return super.onTouchEvent(event)
    }

    override fun onDown(event: MotionEvent): Boolean {
        isScrolling = false
        downY = event.rawY.roundToInt()
        lastY = downY
        downProgress = progress

        return true
    }

    override fun onUp(event: MotionEvent): Boolean {
        if (isScrolling and !(isExpanded or isCollapsed)) {
            // Finish scrolling to either state
            val currentY = event.rawY.roundToInt()
            val isExpanding = currentY - downY < 0

            animateToTransitionState(isExpanding = isExpanding)
        } else if (!isScrolling) {
            // Only allow a click if we weren't dragging the card
            val currentY = event.rawY.roundToInt()
            val isExpanding = currentY - downY < 0

            animateToTransitionState(isExpanding = isExpanding)
        }

        isScrolling = false
        downIsInBounds = false

        return true
    }

    override fun onScroll(
        event1: MotionEvent,
        event2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        val currentY = event2.rawY.roundToInt()

        val rawDiff = currentY - downY

        // The Android View Coordinate Plane begins at the top left corner and has increasing
        // positive values going downward. So, a negative value means we are increasing.
        val isExpanding = rawDiff < 0
        val isCollapsing = rawDiff >= 0
        val isPastTouchSlop = rawDiff.absoluteValue > touchSlop

        // We shouldn't handle the scroll operation if we are expanding when we are already
        // expanded or we are collapsing when we are already collapsed.
        val shouldHandleScroll =
            ((isExpanding and !isExpanded) or (isCollapsing and !isCollapsed)) and isPastTouchSlop

        // Avoids scrolling until we've moved a bit and we can scroll.
        if (shouldHandleScroll) {
            lastY = currentY
            isScrolling = true

            // Need to coerce this value within the range to avoid rounding errors.
            var newProgress =
                interpolator.getInterpolation(downProgress + (rawDiff / maxVerticalMotionDistance))
                    .coerceIn(PROGRESS_COLLAPSED, PROGRESS_EXPANDED)

            // Because we are dealing with floating point values, we give a little room for
            // values that are reasonably close to either expanded or collapsed state.
            // Essentially this rounds the value.
            if (isCollapsing and (newProgress < roundedSlop)) {
                newProgress = PROGRESS_COLLAPSED
            } else if (isExpanding and (newProgress > PROGRESS_EXPANDED - roundedSlop)) {
                newProgress = PROGRESS_EXPANDED
            }

            progress = newProgress
        }

        return true
    }

    override fun onFling(
        event1: MotionEvent,
        event2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        val currentY = event2.rawY.roundToInt()
        val isExpanding = currentY - downY < 0

        animateToTransitionState(isExpanding = isExpanding, velocity = velocityY)

        return true
    }

    private fun animateToTransitionState(isExpanding: Boolean, velocity: Float = 1F) {
        val currentVerticalPosition = maxVerticalMotionDistance * progress
        val midVerticalPosition = maxVerticalMotionDistance / 2F
        val velocityForDuration = velocity / 4F

        val endProgressValue = when {
            isExpanding and (currentVerticalPosition - velocityForDuration > midVerticalPosition) -> PROGRESS_EXPANDED
            !isExpanding and (currentVerticalPosition + velocityForDuration > midVerticalPosition) -> PROGRESS_COLLAPSED
            currentVerticalPosition < midVerticalPosition -> PROGRESS_COLLAPSED
            else -> PROGRESS_EXPANDED
        }

        if (endProgressValue == PROGRESS_COLLAPSED) {
            transitionToStart()
        } else {
            transitionToEnd()
        }
    }

    interface ProgressChangedListener {

        fun onProgressChanged(progress: Float)
    }

    private class ProgressChangedTransitionListener(private val listener: ProgressChangedListener) :
        TransitionListener {

        override fun onTransitionTrigger(
            layout: MotionLayout?,
            triggerId: Int,
            positive: Boolean,
            progress: Float
        ) {
        }

        override fun onTransitionStarted(layout: MotionLayout?, startId: Int, endId: Int) {}

        override fun onTransitionChange(
            layout: MotionLayout?,
            startId: Int,
            endId: Int,
            progress: Float
        ) {
            listener.onProgressChanged(progress = progress)
        }

        override fun onTransitionCompleted(layout: MotionLayout, currentId: Int) {
            listener.onProgressChanged(progress = layout.progress)
        }
    }
}