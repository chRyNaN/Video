package com.chrynan.video.ui.widget.motion

data class Constraint(
    val id: Int,
    val width: ConstraintSize? = null,
    val height: ConstraintSize? = null,
    val connections: Set<ConstraintConnection> = emptySet()
)