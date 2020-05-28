package com.chrynan.video.ui.widget.motion

sealed class ConstraintSize {

    object WrapContent : ConstraintSize()

    object MatchConstraint : ConstraintSize()

    data class ExactValue(val value: Int) : ConstraintSize()
}