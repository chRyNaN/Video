package com.chrynan.video.ui.widget.motion

import android.view.View
import androidx.constraintlayout.widget.ConstraintSet

class ViewConstraintBuilder internal constructor(private val thisView: View?) {

    private val connections = mutableMapOf<ConstraintSide, ConstraintConnection>()

    var width: Int? = null
    var height: Int? = null

    infix fun ConstraintSide.toStartOf(view: View?) =
        addConnection(
            fromId = thisView?.id,
            fromSide = this,
            toId = view?.id,
            toSide = ConstraintSide.START
        )

    fun ConstraintSide.toStartOfParent() =
        addConnection(
            fromId = thisView?.id,
            fromSide = this,
            toId = ConstraintSet.PARENT_ID,
            toSide = ConstraintSide.START
        )

    infix fun ConstraintSide.toEndOf(view: View?) =
        addConnection(
            fromId = thisView?.id,
            fromSide = this,
            toId = view?.id,
            toSide = ConstraintSide.END
        )

    fun ConstraintSide.toEndOfParent() =
        addConnection(
            fromId = thisView?.id,
            fromSide = this,
            toId = ConstraintSet.PARENT_ID,
            toSide = ConstraintSide.END
        )

    infix fun ConstraintSide.toTopOf(view: View?) =
        addConnection(
            fromId = thisView?.id,
            fromSide = this,
            toId = view?.id,
            toSide = ConstraintSide.TOP
        )

    fun ConstraintSide.toTopOfParent() =
        addConnection(
            fromId = thisView?.id,
            fromSide = this,
            toId = ConstraintSet.PARENT_ID,
            toSide = ConstraintSide.TOP
        )

    infix fun ConstraintSide.toBottomOf(view: View?) =
        addConnection(
            fromId = thisView?.id,
            fromSide = this,
            toId = view?.id,
            toSide = ConstraintSide.BOTTOM
        )

    fun ConstraintSide.toBottomOfParent() =
        addConnection(
            fromId = thisView?.id,
            fromSide = this,
            toId = ConstraintSet.PARENT_ID,
            toSide = ConstraintSide.BOTTOM
        )

    internal fun build(): Constraint? =
        if (thisView == null) {
            null
        } else {
            Constraint(
                id = thisView.id,
                width = width,
                height = height,
                connections = connections.map { it.value }.toSet()
            )
        }

    private fun addConnection(
        fromId: Int?,
        fromSide: ConstraintSide,
        toId: Int?,
        toSide: ConstraintSide
    ) {
        if (fromId == null || toId == null) return

        val connection = ConstraintConnection(
            fromId = fromId,
            fromSide = fromSide,
            toId = toId,
            toSide = toSide
        )

        connections[fromSide] = connection
    }
}
