package com.chrynan.video.ui.view

interface VideoView {

    val containerWidth: Int

    val collapsedPlayIconWidth: Int

    val collapsedCancelIconWidth: Int

    var videoHeight: Int

    var videoWidth: Int

    var collapsedPlayIconAlpha: Float

    var collapsedCancelIconAlpha: Float

    var collapsedPlayIconIsVisible: Boolean

    var collapsedCancelIconIsVisible: Boolean
}