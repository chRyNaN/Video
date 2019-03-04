package com.chrynan.video.ui.widget.expandable

import android.view.View

interface ExpandableContainerView {

    val currentExpandableState: ExpandableState

    val expandableChildLayout: ExpandableChildLayout?

    var expandedInteractionView: View?

    var collapsedInteractionView: View?

    fun addStateListener(listener: ExpandableStateListener)

    fun removeStateListener(listener: ExpandableStateListener)

    fun expand()

    fun collapse()
}