package com.chrynan.video.ui.widget

import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

class ExpandableBehavior<V : View> : CoordinatorLayout.Behavior<V>() {

    private val stateManager = ExpandableStateManager()

    private val expandedHeight: Int
        get() = coordinatorLayoutHeight
    private val expandedWidth: Int
        get() = coordinatorLayoutWidth

    private var coordinatorLayoutHeight = 0
    private var coordinatorLayoutWidth = 0
    private var bottomBarHeight = 0
    private var bottomBarVerticalOffset = 0

    private var collapsedHeight = 0
    private var collapsedWidth = 0

    override fun onMeasureChild(
        parent: CoordinatorLayout, child: V, parentWidthMeasureSpec: Int, widthUsed: Int,
        parentHeightMeasureSpec: Int, heightUsed: Int
    ): Boolean {
        coordinatorLayoutHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec)
        coordinatorLayoutWidth = View.MeasureSpec.getSize(parentWidthMeasureSpec)

        return super.onMeasureChild(
            parent,
            child,
            parentWidthMeasureSpec,
            widthUsed,
            parentHeightMeasureSpec,
            heightUsed
        )
    }

    override fun onLayoutChild(parent: CoordinatorLayout, child: V, layoutDirection: Int): Boolean {
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: V, ev: MotionEvent): Boolean {
        return super.onInterceptTouchEvent(parent, child, ev)
    }

    override fun onTouchEvent(parent: CoordinatorLayout, child: V, ev: MotionEvent): Boolean {
        return super.onTouchEvent(parent, child, ev)
    }

    override fun blocksInteractionBelow(parent: CoordinatorLayout, child: V): Boolean {
        return super.blocksInteractionBelow(parent, child)
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }
}