package com.chrynan.video.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintSet
import com.chrynan.video.R
import kotlinx.android.synthetic.main.widget_video_overlay.view.*

class VideoOverlayWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseExpandableOverlayWidget(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.widget_video_overlay, this)
    }

    override val expandedHeight: Int
        get() = 1000 // TODO update

    override val collapsedHeight by lazy { context.resources.getDimensionPixelSize(R.dimen.video_collapsed_default_height) }

    private val videoPlayerExpandedHeight by lazy { context.resources.getDimensionPixelSize(R.dimen.video_expanded_default_height) }

    override fun isInExpandableWidgetBounds(x: Float, y: Float, progress: Float): Boolean {
        val top = videoOverlayVideoPlayerWidget?.top ?: 0
        val bottom = videoOverlayVideoPlayerWidget?.bottom ?: 0

        return y.toInt() in top..bottom
    }

    override fun getExpandedConstraints(): ConstraintSet =
        ConstraintSet().apply {
            clone(this@VideoOverlayWidget)

            // Background
            videoOverlayBackgroundView?.let { background ->
                constrainHeight(background.id, ConstraintSet.MATCH_CONSTRAINT)

                connect(
                    background.id,
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START
                )
                connect(
                    background.id,
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.END
                )
                connect(
                    background.id,
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM
                )
                connect(
                    background.id,
                    ConstraintSet.TOP,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
            }

            // Video Player
            videoOverlayVideoPlayerWidget?.let { videoPlayer ->
                clear(videoPlayer.id, ConstraintSet.BOTTOM)

                constrainHeight(videoPlayer.id, videoPlayerExpandedHeight)
                constrainWidth(videoPlayer.id, ConstraintSet.MATCH_CONSTRAINT)

                connect(
                    videoPlayer.id,
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START
                )
                connect(
                    videoPlayer.id,
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.END
                )
                connect(
                    videoPlayer.id,
                    ConstraintSet.TOP,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
            }

            // Title
            videoOverlayTitleTextView?.let { title ->
                clear(title.id, ConstraintSet.END)
                connect(
                    title.id,
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.END
                )
                connect(
                    title.id,
                    ConstraintSet.TOP,
                    videoOverlayVideoPlayerWidget?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
                connect(
                    title.id,
                    ConstraintSet.BOTTOM,
                    videoOverlayVideoPlayerWidget?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM
                )
            }

            // Play / Pause Button
            videoOverlayPlayPauseImageView?.let { play ->
                clear(play.id, ConstraintSet.END)
                connect(
                    play.id,
                    ConstraintSet.START,
                    videoOverlayVideoPlayerWidget?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.END
                )
                connect(
                    play.id,
                    ConstraintSet.TOP,
                    videoOverlayVideoPlayerWidget?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
                connect(
                    play.id,
                    ConstraintSet.BOTTOM,
                    videoOverlayVideoPlayerWidget?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM
                )
            }

            // Close Button
            videoOverlayCloseImageView?.let { close ->
                clear(close.id, ConstraintSet.END)
                connect(
                    close.id,
                    ConstraintSet.START,
                    videoOverlayPlayPauseImageView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.END
                )
                connect(
                    close.id,
                    ConstraintSet.TOP,
                    videoOverlayVideoPlayerWidget?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
                connect(
                    close.id,
                    ConstraintSet.BOTTOM,
                    videoOverlayVideoPlayerWidget?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM
                )
            }

            // Bottom Navbar
            videoOverlayBottomNavigationView?.let { navbar ->
                clear(navbar.id, ConstraintSet.BOTTOM)
                connect(
                    navbar.id,
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START
                )
                connect(navbar.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
                connect(navbar.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM)
            }
        }

    override fun getCollapsedConstraints(): ConstraintSet =
        ConstraintSet().apply {
            clone(this@VideoOverlayWidget)

            // Background
            videoOverlayBackgroundView?.let { background ->
                constrainHeight(background.id, collapsedHeight)

                connect(
                    background.id,
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START
                )
                connect(
                    background.id,
                    ConstraintSet.END,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.END
                )
                connect(
                    background.id,
                    ConstraintSet.BOTTOM,
                    videoOverlayBottomNavigationView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
            }

            // Video Player
            videoOverlayVideoPlayerWidget?.let { videoPlayer ->
                clear(videoPlayer.id, ConstraintSet.END)

                constrainHeight(videoPlayer.id, ConstraintSet.MATCH_CONSTRAINT)

                connect(
                    videoPlayer.id,
                    ConstraintSet.START,
                    videoOverlayBackgroundView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.START
                )
                connect(
                    videoPlayer.id,
                    ConstraintSet.TOP,
                    videoOverlayBackgroundView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
                connect(
                    videoPlayer.id,
                    ConstraintSet.BOTTOM,
                    videoOverlayBackgroundView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM
                )
            }

            // Title
            videoOverlayTitleTextView?.let { title ->
                connect(
                    title.id,
                    ConstraintSet.START,
                    videoOverlayVideoPlayerWidget?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.END
                )
                connect(
                    title.id,
                    ConstraintSet.END,
                    videoOverlayPlayPauseImageView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.START
                )
                connect(
                    title.id,
                    ConstraintSet.TOP,
                    videoOverlayBackgroundView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
                connect(
                    title.id,
                    ConstraintSet.BOTTOM,
                    videoOverlayBackgroundView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM
                )
            }

            // Play / Pause Button
            videoOverlayPlayPauseImageView?.let { play ->
                clear(play.id, ConstraintSet.START)
                connect(
                    play.id,
                    ConstraintSet.END,
                    videoOverlayCloseImageView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.START
                )
                connect(
                    play.id,
                    ConstraintSet.TOP,
                    videoOverlayBackgroundView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
                connect(
                    play.id,
                    ConstraintSet.BOTTOM,
                    videoOverlayBackgroundView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM
                )
            }

            // Close Button
            videoOverlayCloseImageView?.let { close ->
                clear(close.id, ConstraintSet.START)
                connect(close.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
                connect(
                    close.id,
                    ConstraintSet.TOP,
                    videoOverlayBackgroundView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
                connect(
                    close.id,
                    ConstraintSet.BOTTOM,
                    videoOverlayBackgroundView?.id ?: ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM
                )
            }

            // Bottom Navbar
            videoOverlayBottomNavigationView?.let { navbar ->
                connect(
                    navbar.id,
                    ConstraintSet.START,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.START
                )
                connect(navbar.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
                connect(
                    navbar.id,
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.BOTTOM
                )
            }
        }
}