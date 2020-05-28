package com.chrynan.video.ui.widget.motion

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

class ConstraintSetBuilder internal constructor() {

    private val constraints = mutableMapOf<Int, Constraint>()

    private var cloneFrom: CloneSource? = null

    fun cloneFrom(constraintLayout: ConstraintLayout) {
        cloneFrom = CloneSource.Layout(constraintLayout)
    }

    fun cloneFrom(constraintSet: ConstraintSet) {
        cloneFrom = CloneSource.Set(constraintSet)
    }

    fun constraintsFor(view: View?, builder: ViewConstraintBuilder.() -> Unit) {
        val viewConstraintBuilder = ViewConstraintBuilder(view)
        builder.invoke(viewConstraintBuilder)
        val constraint = viewConstraintBuilder.build()
        if (constraint != null) {
            constraints[constraint.id] = constraint
        }
    }

    internal fun build(): Constraints =
        Constraints(
            cloneFrom = cloneFrom,
            constraints = constraints.map { it.value }.toSet()
        )
}

fun constraintSet(builder: ConstraintSetBuilder.() -> Unit): Constraints {
    val constraintSetBuilder = ConstraintSetBuilder()
    builder.invoke(constraintSetBuilder)
    return constraintSetBuilder.build()
}