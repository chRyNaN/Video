package com.chrynan.video.ui.widget.motion

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

sealed class CloneSource {

    data class Layout(val layout: ConstraintLayout) : CloneSource()

    data class Set(val set: ConstraintSet) : CloneSource()
}