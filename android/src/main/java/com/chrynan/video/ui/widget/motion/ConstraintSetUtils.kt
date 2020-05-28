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
            val value = when (constraint.width) {
                ConstraintSize.WrapContent -> ConstraintSet.WRAP_CONTENT
                ConstraintSize.MatchConstraint -> ConstraintSet.MATCH_CONSTRAINT
                is ConstraintSize.ExactValue -> constraint.width.value
            }

            set.constrainWidth(constraint.id, value)
        }

        if (constraint.height != null) {
            val value = when (constraint.height) {
                ConstraintSize.WrapContent -> ConstraintSet.WRAP_CONTENT
                ConstraintSize.MatchConstraint -> ConstraintSet.MATCH_CONSTRAINT
                is ConstraintSize.ExactValue -> constraint.height.value
            }

            set.constrainHeight(constraint.id, value)
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