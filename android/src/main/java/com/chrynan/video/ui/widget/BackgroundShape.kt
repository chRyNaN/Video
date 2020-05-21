package com.chrynan.video.ui.widget

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

sealed class BackgroundShape {

    object Round : BackgroundShape()

    object Rectangle : BackgroundShape()

    data class RoundedRectangle(val cornerRadius: Float) : BackgroundShape()
}

fun View.setBackgroundShape(shape: BackgroundShape) {
    val outline = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            when (shape) {
                BackgroundShape.Round -> {
                    outline.setRoundRect(
                        0,
                        0,
                        view.width,
                        view.height,
                        (view.height / 2).toFloat()
                    )
                }
                BackgroundShape.Rectangle -> {
                    outline.setRect(0, 0, view.width, view.height)
                }
                is BackgroundShape.RoundedRectangle -> {
                    outline.setRoundRect(
                        0,
                        0,
                        view.width,
                        view.height,
                        shape.cornerRadius
                    )
                }
            }
        }
    }

    clipToOutline = true
    outlineProvider = outline
}