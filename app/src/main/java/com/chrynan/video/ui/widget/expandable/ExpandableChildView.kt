package com.chrynan.video.ui.widget.expandable

import android.view.View
import androidx.core.view.NestedScrollingChild2

interface ExpandableChildView : NestedScrollingChild2 {

    var collapsedInteractionView: View?
    var expandedInteractionView: View?
    var transitionOnClick: Boolean

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean

    override fun startNestedScroll(axes: Int, type: Int): Boolean

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean

    override fun stopNestedScroll(type: Int)

    override fun hasNestedScrollingParent(type: Int): Boolean

    fun performClickAction()
}