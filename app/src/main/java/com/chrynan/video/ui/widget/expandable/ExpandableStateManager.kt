package com.chrynan.video.ui.widget.expandable

import com.chrynan.video.ui.widget.expandable.ExpandableState.Companion.COLLAPSED
import com.chrynan.video.ui.widget.expandable.ExpandableState.Companion.EXPANDED

class ExpandableStateManager {

    val expandableStateListeners = mutableListOf<ExpandableStateListener>()

    var currentExpandableProgress: Float =
        COLLAPSED
        get() = currentExpandableState.progress
        set(value) {
            val valueInRange = value.coerceIn(
                COLLAPSED,
                EXPANDED
            )
            currentExpandableState = getNewTransitionState(newProgress = valueInRange, oldProgress = field)
            field = valueInRange
            expandableStateListeners.forEach { it.onExpandableStateChange(state = currentExpandableState) }
        }

    var currentExpandableState: ExpandableState =
        ExpandableState.Collapsed
        private set

    private fun getNewTransitionState(newProgress: Float, oldProgress: Float): ExpandableState =
        when {
            newProgress == COLLAPSED -> ExpandableState.Collapsed
            newProgress == EXPANDED -> ExpandableState.Expanded
            newProgress < oldProgress -> ExpandableState.Collapsing(
                progress = newProgress
            )
            newProgress > oldProgress -> ExpandableState.Expanding(
                progress = newProgress
            )
            else -> ExpandableState.NoChange(progress = newProgress)
        }
}