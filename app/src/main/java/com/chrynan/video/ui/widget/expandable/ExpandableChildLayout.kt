package com.chrynan.video.ui.widget.expandable

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.NestedScrollingChildHelper
import com.chrynan.video.R

class ExpandableChildLayout : FrameLayout,
    ExpandableChildView {

    override var collapsedInteractionView: View? = null
    override var expandedInteractionView: View? = null
    override var transitionOnClick = false

    private val nestedScrollingChildHelper by lazy {
        NestedScrollingChildHelper(this).apply {
            isNestedScrollingEnabled = true
        }
    }

    private val touchManager = ExpandableTouchProcessor()

    private val parentExpandableMotionLayout: ExpandableMotionLayout?
        get() = (parent as? ExpandableMotionLayout)

    private val currentExpandableState: ExpandableState
        get() = parentExpandableMotionLayout?.currentExpandableState ?: ExpandableState.Collapsed

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet? = null) : super(context, attrs) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableChildLayout)

        typedArray.apply {
            val collapsedViewId = getResourceId(R.styleable.ExpandableChildLayout_collapsedInteractionView, 0)
            val expandedViewId = getResourceId(R.styleable.ExpandableChildLayout_expandedInteractionView, 0)
            transitionOnClick = getBoolean(R.styleable.ExpandableChildLayout_transitionOnClick, false)
            collapsedInteractionView = findViewById(collapsedViewId)
            expandedInteractionView = findViewById(expandedViewId)
            recycle()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if ((collapsedInteractionView == null) or (expandedInteractionView == null)) return super.onTouchEvent(event)

        return touchManager.processTouchEvent(
            event = event,
            view = this,
            collapsedInteractionView = collapsedInteractionView!!,
            expandedInteractionView = expandedInteractionView!!,
            currentState = currentExpandableState
        )
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int
    ) = nestedScrollingChildHelper.dispatchNestedScroll(
        dxConsumed,
        dyConsumed,
        dxUnconsumed,
        dyUnconsumed,
        offsetInWindow,
        type
    )

    override fun startNestedScroll(axes: Int, type: Int) = nestedScrollingChildHelper.startNestedScroll(axes, type)

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int
    ) = nestedScrollingChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)

    override fun stopNestedScroll(type: Int) = nestedScrollingChildHelper.stopNestedScroll(type)

    override fun hasNestedScrollingParent(type: Int) = nestedScrollingChildHelper.hasNestedScrollingParent(type)

    override fun performClickAction() {
        if (transitionOnClick) {
            val state = currentExpandableState

            if (state.isExpanded) {
                parentExpandableMotionLayout?.collapse()
            } else if (state.isCollapsed) {
                parentExpandableMotionLayout?.expand()
            }
        }

        super.performClick()
    }
}