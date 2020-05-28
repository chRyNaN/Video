package com.chrynan.video.ui.widget.motion

data class Constraints(
    val cloneFrom: CloneSource? = null,
    val constraints: Set<Constraint> = emptySet()
)