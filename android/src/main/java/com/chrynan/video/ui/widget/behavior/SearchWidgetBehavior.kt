package com.chrynan.video.ui.widget.behavior

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.TimeInterpolator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.video.ui.widget.SearchWidget
import com.google.android.material.animation.AnimationUtils
import kotlinx.android.synthetic.main.widget_search.view.*

class SearchWidgetBehavior : CoordinatorLayout.Behavior<SearchWidget> {

    companion object {

        private const val ENTER_ANIMATION_DURATION = 225L
        private const val EXIT_ANIMATION_DURATION = 175L
        private const val ENTER_WIDGET_ANIMATION_DELAY = 0L
        private const val EXIT_WIDGET_ANIMATION_DELAY = 100L
    }

    private var widgetTotalHeight = 0
    private var searchBarTotalHeight = 0
    private var chipGroupTotalHeight = 0
    private var currentState = State.EXPANDED
    private var currentAnimator: ViewPropertyAnimator? = null
    private var currentChipGroupAnimator: ViewPropertyAnimator? = null
    private var isAnimating = false

    @Suppress("unused")
    constructor() : super()

    @Suppress("unused")
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: SearchWidget,
        layoutDirection: Int
    ): Boolean {
        val widgetParams = child.layoutParams as? ViewGroup.MarginLayoutParams
        val searchBarParams =
            child.searchWidgetEditText.layoutParams as? ViewGroup.MarginLayoutParams
        val chipGroupParams =
            child.searchWidgetTagRecyclerView.layoutParams as? ViewGroup.MarginLayoutParams

        widgetTotalHeight = child.measuredHeight + (widgetParams?.topMargin ?: 0)
        searchBarTotalHeight =
            child.searchWidgetEditText.measuredHeight + (searchBarParams?.topMargin ?: 0)
        chipGroupTotalHeight =
            child.searchWidgetTagRecyclerView.measuredHeight + (chipGroupParams?.topMargin ?: 0)

        return super.onLayoutChild(parent, child, layoutDirection)
    }

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: SearchWidget,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean = nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: SearchWidget,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        if (isAnimating) return

        if (dyConsumed > 0) {
            if (currentState == State.PARTIALLY_EXPANDED) {
                slideUpEntireWidget(child)
            } else {
                slideUpChipGroup(child)
            }
        } else if (dyConsumed < 0) {
            if (currentState == State.PARTIALLY_EXPANDED) {
                slideDownChipGroup(child)
            } else {
                slideDownEntireWidget(child)
            }
        }
    }

    override fun onStopNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: SearchWidget,
        target: View,
        type: Int
    ) {
        val recyclerView = target as? RecyclerView

        if (recyclerView != null) {

            if (!recyclerView.canScrollVertically(-1)) {
                // We reached the top so show the Search Bar
                expand(child)
            } else if (!recyclerView.canScrollVertically(1)) {
                // We reached the bottom so hide the Search Bar
                collapse(child)
            }
        } else {
            val nestedScrollView = target as? NestedScrollView

            if (nestedScrollView != null && !nestedScrollView.canScrollVertically(-1)) {
                // We reached the top so show the Search Bar
                expand(child)
            } else if (nestedScrollView != null && !nestedScrollView.canScrollVertically(1)) {
                // We reached the bottom so hide the Search Bar
                collapse(child)
            }
        }
    }

    private fun slideUpEntireWidget(child: SearchWidget) {
        if (currentState != State.PARTIALLY_EXPANDED) return

        if (currentAnimator != null) {
            currentAnimator?.cancel()
            child.clearAnimation()
        }

        currentAnimator = animateWidgetTo(
            view = child,
            targetY = -widgetTotalHeight,
            duration = EXIT_ANIMATION_DURATION,
            delay = EXIT_WIDGET_ANIMATION_DELAY,
            interpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR,
            finishedState = State.COLLAPSED
        )
    }

    private fun slideDownEntireWidget(child: SearchWidget) {
        if (currentState != State.COLLAPSED) return

        if (currentAnimator != null) {
            currentAnimator?.cancel()
            child.clearAnimation()
        }

        currentAnimator = animateWidgetTo(
            view = child,
            targetY = 0,
            duration = ENTER_ANIMATION_DURATION,
            delay = ENTER_WIDGET_ANIMATION_DELAY,
            interpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR,
            finishedState = State.PARTIALLY_EXPANDED
        )
    }

    private fun slideUpChipGroup(child: SearchWidget) {
        if (currentState != State.EXPANDED) return

        val chipGroup = child.searchWidgetTagRecyclerView ?: return

        if (currentChipGroupAnimator != null) {
            currentChipGroupAnimator?.cancel()
            chipGroup.clearAnimation()
        }

        currentChipGroupAnimator = animateChipGroupTo(
            view = chipGroup,
            targetY = -chipGroupTotalHeight,
            alpha = 0F,
            duration = EXIT_ANIMATION_DURATION,
            interpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR,
            finishedState = State.PARTIALLY_EXPANDED
        )
    }

    private fun slideDownChipGroup(child: SearchWidget) {
        if (currentState != State.PARTIALLY_EXPANDED) return

        val chipGroup = child.searchWidgetTagRecyclerView ?: return

        if (currentChipGroupAnimator != null) {
            currentChipGroupAnimator?.cancel()
            chipGroup.clearAnimation()
        }

        currentChipGroupAnimator = animateChipGroupTo(
            view = chipGroup,
            targetY = 0,
            alpha = 1F,
            duration = ENTER_ANIMATION_DURATION,
            interpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR,
            finishedState = State.EXPANDED
        )
    }

    private fun slideDownEntireWidgetThenChipGroup(child: SearchWidget) {
        if (currentAnimator != null) {
            currentAnimator?.cancel()
            child.clearAnimation()
        }

        currentAnimator = animateWidgetTo(
            view = child,
            targetY = 0,
            duration = ENTER_ANIMATION_DURATION,
            delay = ENTER_WIDGET_ANIMATION_DELAY,
            interpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR,
            finishedState = State.PARTIALLY_EXPANDED
        )

        child.postDelayed({
            val chipGroup = child.searchWidgetTagRecyclerView

            if (chipGroup != null) {
                if (currentChipGroupAnimator != null) {
                    currentChipGroupAnimator?.cancel()
                    chipGroup.clearAnimation()
                }

                currentChipGroupAnimator = animateChipGroupTo(
                    view = chipGroup,
                    targetY = 0,
                    alpha = 1F,
                    duration = ENTER_ANIMATION_DURATION,
                    interpolator = AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR,
                    finishedState = State.EXPANDED
                )
            }
        }, ENTER_ANIMATION_DURATION + ENTER_WIDGET_ANIMATION_DELAY)
    }

    private fun slideUpEntireWidgetThenChipGroup(child: SearchWidget) {
        val chipGroup = child.searchWidgetTagRecyclerView

        if (currentChipGroupAnimator != null) {
            currentChipGroupAnimator?.cancel()
            chipGroup.clearAnimation()
        }

        currentChipGroupAnimator = animateChipGroupTo(
            view = chipGroup,
            targetY = -chipGroupTotalHeight,
            alpha = 0F,
            duration = EXIT_ANIMATION_DURATION,
            interpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR,
            finishedState = State.PARTIALLY_EXPANDED
        )

        child.postDelayed({
            if (currentAnimator != null) {
                currentAnimator?.cancel()
                child.clearAnimation()
            }

            currentAnimator = animateWidgetTo(
                view = child,
                targetY = -widgetTotalHeight,
                duration = EXIT_ANIMATION_DURATION,
                delay = EXIT_WIDGET_ANIMATION_DELAY,
                interpolator = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR,
                finishedState = State.COLLAPSED
            )
        }, EXIT_ANIMATION_DURATION)
    }

    private fun expand(child: SearchWidget) =
        when (currentState) {
            State.EXPANDED -> Unit
            State.PARTIALLY_EXPANDED -> slideDownChipGroup(child)
            State.COLLAPSED -> slideDownEntireWidgetThenChipGroup(child)
        }

    private fun collapse(child: SearchWidget) =
        when (currentState) {
            State.COLLAPSED -> Unit
            State.PARTIALLY_EXPANDED -> slideUpChipGroup(child)
            State.EXPANDED -> slideUpEntireWidgetThenChipGroup(child)
        }

    private fun animateWidgetTo(
        view: View,
        targetY: Int,
        duration: Long,
        delay: Long,
        interpolator: TimeInterpolator,
        finishedState: State
    ): ViewPropertyAnimator {
        isAnimating = true

        return view.animate()
            .translationY(targetY.toFloat())
            .setInterpolator(interpolator)
            .setDuration(duration)
            .setStartDelay(delay)
            .setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        currentAnimator = null
                        currentState = finishedState
                        isAnimating = false
                    }
                })
    }

    private fun animateChipGroupTo(
        view: View,
        targetY: Int,
        alpha: Float,
        duration: Long,
        interpolator: TimeInterpolator,
        finishedState: State
    ): ViewPropertyAnimator {
        isAnimating = true

        return view.animate()
            .translationY(targetY.toFloat())
            .alpha(alpha)
            .setInterpolator(interpolator)
            .setDuration(duration)
            .setListener(
                object : AnimatorListenerAdapter() {
                    override fun onAnimationCancel(animation: Animator?) {
                        isAnimating = false
                    }

                    override fun onAnimationEnd(animation: Animator) {
                        currentChipGroupAnimator = null
                        currentState = finishedState
                        isAnimating = false
                    }
                })
    }

    private enum class State {

        COLLAPSED,
        PARTIALLY_EXPANDED,
        EXPANDED
    }
}
