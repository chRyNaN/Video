package com.chrynan.video.ui.widget.motion

data class Constraint(
    val id: Int,
    val width: Int? = null,
    val height: Int? = null,
    val connections: Set<ConstraintConnection> = emptySet()
)