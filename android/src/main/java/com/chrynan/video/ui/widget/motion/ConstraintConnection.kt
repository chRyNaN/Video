package com.chrynan.video.ui.widget.motion

data class ConstraintConnection(
    val fromId: Int,
    val fromSide: ConstraintSide,
    val toId: Int,
    val toSide: ConstraintSide
)