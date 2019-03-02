package com.chrynan.video.ui.widget

class ExpandableStateManager {

    companion object {

        private const val EXPANDED = 1f
        private const val COLLAPSED = 0f
    }

    val expandableStateListeners = mutableListOf<ExpandableStateListener>()

    val isExpanded: Boolean
        get() = currentExpandableState == ExpandableState.Expanded

    val isCollapsed: Boolean
        get() = currentExpandableState == ExpandableState.Collapsed

    val isExpanding: Boolean
        get() = currentExpandableState is ExpandableState.Expanding

    val isCollapsing: Boolean
        get() = currentExpandableState is ExpandableState.Collapsing

    val isNoChange: Boolean
        get() = currentExpandableState is ExpandableState.NoChange

    var currentExpandableState: ExpandableState = ExpandableState.Collapsed
        private set

    private fun getNewTransitionState(newProgress: Float, previousProgress: Float): ExpandableState =
        when {
            newProgress == COLLAPSED -> ExpandableState.Collapsed
            newProgress == EXPANDED -> ExpandableState.Expanded
            newProgress < previousProgress -> ExpandableState.Collapsing(progress = newProgress)
            newProgress > previousProgress -> ExpandableState.Expanding(progress = newProgress)
            else -> ExpandableState.NoChange(progress = newProgress)
        }

    sealed class ExpandableState(val progress: Float) {

        object Collapsed : ExpandableState(COLLAPSED)

        object Expanded : ExpandableState(EXPANDED)

        class Collapsing(progress: Float) : ExpandableState(progress)

        class Expanding(progress: Float) : ExpandableState(progress)

        class NoChange(progress: Float) : ExpandableState(progress)
    }

    interface ExpandableStateListener {

        fun onExpandableStateChange(state: ExpandableState)
    }
}