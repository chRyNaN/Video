package com.chrynan.video.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.SeekBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.chrynan.video.R
import kotlinx.android.synthetic.main.layout_video_queue_control.view.*

class VideoQueueControlWidget : ConstraintLayout,
    SeekBar.OnSeekBarChangeListener {

    var listener: VideoQueueControlListener? = null

    var currentMainAction: VideoQueueControlMainAction = VideoQueueControlMainAction.PAUSE
        set(value) {
            field = value

            updateMainActionIcon(currentAction = value)
        }

    private val playActionDrawable by lazy { resources.getDrawable(R.drawable.ic_play, null) }
    private val pauseActionDrawable by lazy { resources.getDrawable(R.drawable.ic_pause, null) }
    private val replayActionDrawable by lazy { resources.getDrawable(R.drawable.ic_replay, null) }

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        LayoutInflater.from(context).inflate(R.layout.layout_video_queue_control, this)

        mainActionImageView?.setOnClickListener {
            val action = currentMainAction
            currentMainAction = getNewActionState(currentAction = action)
            listener?.onMainActionSelected(action = action)
        }
        previousImageView?.setOnClickListener { listener?.onNavigationActionSelected(action = VideoQueueControlNavigationAction.PREVIOUS) }
        nextImageView?.setOnClickListener { listener?.onNavigationActionSelected(action = VideoQueueControlNavigationAction.NEXT) }
        videoPositionSeekBar?.setOnSeekBarChangeListener(this)
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        if (fromUser) {
            listener?.onProgressChanged(progress = progress)
        }
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

    override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit

    private fun getNewActionState(currentAction: VideoQueueControlMainAction): VideoQueueControlMainAction =
        when (currentAction) {
            VideoQueueControlMainAction.PLAY -> VideoQueueControlMainAction.PAUSE
            VideoQueueControlMainAction.PAUSE -> VideoQueueControlMainAction.PLAY
            VideoQueueControlMainAction.REPLAY -> VideoQueueControlMainAction.PLAY
        }

    private fun updateMainActionIcon(currentAction: VideoQueueControlMainAction) {
        val drawable = when (currentAction) {
            VideoQueueControlMainAction.PLAY -> playActionDrawable
            VideoQueueControlMainAction.PAUSE -> pauseActionDrawable
            VideoQueueControlMainAction.REPLAY -> replayActionDrawable
        }

        mainActionImageView?.setImageDrawable(drawable)
    }

    interface VideoQueueControlListener {

        fun onMainActionSelected(action: VideoQueueControlMainAction)

        fun onNavigationActionSelected(action: VideoQueueControlNavigationAction)

        fun onProgressChanged(progress: Int)
    }

    enum class VideoQueueControlMainAction {

        PLAY,
        PAUSE,
        REPLAY
    }

    enum class VideoQueueControlNavigationAction {

        PREVIOUS,
        NEXT
    }
}