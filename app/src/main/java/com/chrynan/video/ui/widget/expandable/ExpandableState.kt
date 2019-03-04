package com.chrynan.video.ui.widget.expandable

sealed class ExpandableState(val progress: Float) {

    companion object {

        const val EXPANDED = 1f
        const val COLLAPSED = 0f
    }

    val isExpanded: Boolean
        get() = this == ExpandableState.Expanded

    val isCollapsed: Boolean
        get() = this == ExpandableState.Collapsed

    val isExpanding: Boolean
        get() = this is ExpandableState.Expanding

    val isCollapsing: Boolean
        get() = this is ExpandableState.Collapsing

    val isNoChange: Boolean
        get() = this is ExpandableState.NoChange

    val isTransitioning: Boolean
        get() = (progress > COLLAPSED) and (progress < EXPANDED)

    object Collapsed : ExpandableState(COLLAPSED)

    object Expanded : ExpandableState(EXPANDED)

    class Collapsing(progress: Float) : ExpandableState(progress)

    class Expanding(progress: Float) : ExpandableState(progress)

    class NoChange(progress: Float) : ExpandableState(progress)
}