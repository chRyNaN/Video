package com.chrynan.video.ui.widget.gesture

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat

/**
 * A wrapper around a [GestureDetector] that handles the onUp event. That event is missing from the
 * normal [GestureDetector].
 */
class CompleteGestureDetector(
    context: Context,
    private val listener: CompleteGestureListener
) {

    private val detector by lazy { GestureDetectorCompat(context, listener) }

    fun onTouch(event: MotionEvent): Boolean {
        val isUpEvent = event.action == MotionEvent.ACTION_UP

        val isHandled = detector.onTouchEvent(event)

        if (!isHandled && isUpEvent) return listener.onUp(event)

        return isHandled
    }

    interface CompleteGestureListener : GestureDetector.OnGestureListener {

        override fun onSingleTapUp(event: MotionEvent): Boolean = false

        override fun onShowPress(event: MotionEvent?) = Unit

        override fun onLongPress(event: MotionEvent?) = Unit

        fun onUp(event: MotionEvent): Boolean
    }
}