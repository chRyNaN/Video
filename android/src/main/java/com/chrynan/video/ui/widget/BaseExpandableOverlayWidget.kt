package com.chrynan.video.ui.widget

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.constraintlayout.motion.widget.TransitionBuilder
import androidx.constraintlayout.widget.ConstraintSet
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
    }

    abstract val expandedHeight: Int

    abstract val collapsedHeight: Int

    val isExpanded: Boolean
        get() = progress == PROGRESS_EXPANDED

    val isCollapsed: Boolean
        get() = progress == PROGRESS_COLLAPSED

    val expandableProgressChangedListeners = mutableSetOf<ExpandableProgressChangedListener>()

    private val gestureDetector by lazy { CompleteGestureDetector(context, this) }

    private val interpolator by lazy { AccelerateDecelerateInterpolator() }

    private val maxVerticalMotionDistance: Float
        get() = (expandedHeight - collapsedHeight).toFloat()

    private val touchSlop by lazy { ViewConfiguration.get(context).scaledTouchSlop }
    private val roundedSlop by lazy { 0.01F }

    private var downY = 0
    private var lastY = 0
    private var downProgress = 0F
    private var isScrolling = false

    abstract fun isInExpandableWidgetBounds(x: Float, y: Float, progress: Float): Boolean

    abstract fun getExpandedConstraints(): ConstraintSet

    abstract fun getCollapsedConstraints(): ConstraintSet

    fun setup() {
        setupScene()
    }

    fun expand() {
        animateToTransitionState(
            isExpanding = true,
            velocity = 10F
        ) // TODO update arbitrarily high velocity
    }

    fun collapse() {
        animateToTransitionState(isExpanding = false, velocity = 10F)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        // We have to check this in both onTouchEvent and here in onInterceptTouchEvent, because
        // while returning false from this function will allow lower Views on the z-axis to receive
        // touch events, child views will delegate back up to the onTouchEvent function.
        val isInBounds = isInExpandableWidgetBounds(x = event.x, y = event.y, progress = progress)

        if (!isInBounds && event.action == MotionEvent.ACTION_DOWN) return false

        return true
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        // We have to check this in both onInterceptTouchEvent and here in onTouchEvent, because
        // while returning false from onInterceptTouchEvent will allow lower Views on the z-axis to
        // receive touch events, child views will delegate back up to this function.
        val isInBounds = isInExpandableWidgetBounds(x = event.x, y = event.y, progress = progress)

        if (!isInBounds && event.action == MotionEvent.ACTION_DOWN) return false

        return gestureDetector.onTouch(event)
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

            val oldProgress = progress

            progress = newProgress

            expandableProgressChangedListeners.notifyExpandableProgressChanged(
                oldProgress = oldProgress,
                newProgress = newProgress
            )
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

    private fun setupScene() {
        val scene = MotionScene(this)

        val expandedConstraints = getExpandedConstraints()
        val collapsedConstraints = getCollapsedConstraints()

        collapsedConstraints.applyTo(this)

        val transition = TransitionBuilder.buildTransition(
            scene, // Motion Scene
            View.generateViewId(), // Transition ID - Could be anything
            View.generateViewId(), // Start ConstraintSet ID - Could be anything
            collapsedConstraints, // Start ConstraintSet
            View.generateViewId(), // End ConstraintSet ID - Could be anything
            expandedConstraints // End ConstraintSet
        )

        transition.setEnable(true)

        scene.addTransition(transition)
        scene.setTransition(transition)

        setScene(scene)

        progress = PROGRESS_COLLAPSED
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

        ValueAnimator.ofFloat(progress, endProgressValue).apply {
            interpolator = this@BaseExpandableOverlayWidget.interpolator
            startDelay = 0L
            duration = 250L
            addUpdateListener { progress = it.animatedValue as Float }
            start()
        }
    }

    private fun Collection<ExpandableProgressChangedListener>.notifyExpandableProgressChanged(
        oldProgress: Float,
        newProgress: Float
    ) = forEach {
        it.onExpandableProgressChanged(
            oldProgress = oldProgress,
            newProgress = newProgress
        )
    }

    interface ExpandableProgressChangedListener {

        fun onExpandableProgressChanged(oldProgress: Float, newProgress: Float)
    }
}