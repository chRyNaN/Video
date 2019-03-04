package com.chrynan.video.ui.widget.expandable

import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import androidx.core.view.ViewCompat
import com.chrynan.kotlinutils.runThenTrue
import kotlin.math.abs
import kotlin.math.roundToInt

class ExpandableTouchProcessor(private val touchScrollThreshold: Float = 10f) {

    private val viewRect = Rect()

    private val transitionConsumed = intArrayOf(0, 0)
    private val transitionOffset = intArrayOf(0, 0)

    private var lastTouchY = 0
    private var transitionDistanceY = 0
    private var isTransitioning = false
    private var touchStartedInRange = false

    fun processTouchEvent(
        event: MotionEvent, view: ExpandableChildView, collapsedInteractionView: View, expandedInteractionView: View,
        currentState: ExpandableState
    ): Boolean {
        if (event.actionMasked == MotionEvent.ACTION_DOWN) {
            touchStartedInRange = (currentState.isCollapsed and event.isInViewBounds(view = collapsedInteractionView)) or
                    (currentState.isExpanded and event.isInViewBounds(view = expandedInteractionView))
        }

        return if (touchStartedInRange) {
            handleExpandingAndCollapsingTransition(event = event, view = view)
        } else {
            false
        }
    }

    private fun handleExpandingAndCollapsingTransition(event: MotionEvent, view: ExpandableChildView) =
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> runThenTrue {
                transitionDistanceY = 0
                lastTouchY = event.y.roundToInt()

                view.startNestedScroll(ViewCompat.SCROLL_AXIS_VERTICAL, ViewCompat.TYPE_TOUCH)
            }
            MotionEvent.ACTION_MOVE -> runThenTrue {
                val currentY = event.y.roundToInt()
                val dy = lastTouchY - currentY
                transitionDistanceY += dy

                if (isTransitioning) view.dispatchNestedPreScroll(
                    0,
                    dy,
                    transitionConsumed,
                    transitionOffset,
                    ViewCompat.TYPE_TOUCH
                )

                if (abs(transitionDistanceY) > touchScrollThreshold) {
                    lastTouchY = currentY - transitionOffset[1]
                    isTransitioning = true
                }
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> runThenTrue {
                view.stopNestedScroll(ViewCompat.TYPE_TOUCH)

                if (!isTransitioning) view.performClickAction()

                isTransitioning = false
                touchStartedInRange = false
            }
            else -> false
        }

    private fun MotionEvent.isInViewBounds(view: View? = null): Boolean {
        viewRect.reset()
        view?.getLocalVisibleRect(viewRect)
        return viewRect.contains(x.toInt(), y.toInt())
    }

    private fun Rect.reset() = set(0, 0, 0, 0)
}