package com.chrynan.video.ui.widget.motion

import androidx.constraintlayout.widget.ConstraintSet

fun Constraints.toConstraintLayoutSet(): ConstraintSet {
    val set = ConstraintSet()

    if (cloneFrom != null) {
        if (cloneFrom is CloneSource.Layout) {
            set.clone(cloneFrom.layout)
        } else if (cloneFrom is CloneSource.Set) {
            set.clone(cloneFrom.set)
        }
    }

    constraints.forEach { constraint ->
        if (constraint.width != null) {
            set.constrainWidth(constraint.id, constraint.width)
        }

        if (constraint.height != null) {
            set.constrainHeight(constraint.id, constraint.height)
        }

        constraint.connections.forEach { connection ->
            set.connect(
                connection.fromId,
                connection.fromSide.value,
                connection.toId,
                connection.toSide.value
            )
        }
    }

    return set
}