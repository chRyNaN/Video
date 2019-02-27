package com.chrynan.video.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.video.R
import com.chrynan.video.model.VideoInfoViewModel
import com.chrynan.video.ui.adapter.VideoInfoAdapter
import com.chrynan.video.ui.view.VideoView
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : BaseFragment(),
    VideoView {

    companion object {

        fun newInstance() = VideoFragment()
    }

    override val containerWidth: Int
        get() = containerView?.measuredWidth ?: 0

    override val collapsedPlayIconWidth: Int
        get() = playIconImageView?.measuredWidth ?: 0

    override val collapsedCancelIconWidth: Int
        get() = cancelIconImageView?.measuredWidth ?: 0

    override var videoHeight: Int
        get() = videoImageView?.measuredHeight ?: 0
        set(value) {
            videoImageView?.layoutParams?.height = value
        }

    override var videoWidth: Int
        get() = videoImageView?.measuredWidth ?: 0
        set(value) {
            val params = videoImageView?.layoutParams as? ConstraintLayout.LayoutParams
            params?.width = value
            videoImageView?.layoutParams = params
        }

    override var collapsedPlayIconAlpha: Float
        get() = playIconImageView?.alpha ?: 0f
        set(value) {
            playIconImageView?.alpha = value
        }

    override var collapsedCancelIconAlpha: Float
        get() = cancelIconImageView?.alpha ?: 0f
        set(value) {
            cancelIconImageView?.alpha = value
        }

    override var collapsedPlayIconIsVisible: Boolean
        get() = playIconImageView?.visibility == View.VISIBLE
        set(value) {
            playIconImageView?.visibility = if (value) View.VISIBLE else View.GONE
        }

    override var collapsedCancelIconIsVisible: Boolean
        get() = cancelIconImageView?.visibility == View.VISIBLE
        set(value) {
            cancelIconImageView?.visibility = if (value) View.VISIBLE else View.GONE
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView?.apply {
            val managerAdapter = ManagerRecyclerViewAdapter<UniqueAdapterItem>(adapters = setOf(VideoInfoAdapter()))
            layoutManager = LinearLayoutManager(context)
            adapter = managerAdapter

            managerAdapter.items = listOf(
                VideoInfoViewModel(
                    uniqueAdapterId = 0,
                    title = "Testing 123",
                    viewCount = "225k",
                    description = "Description",
                    publishedDate = "",
                    category = "",
                    tags = emptyList(),
                    supportsRating = false,
                    likeCount = null,
                    dislikeCount = null,
                    isLiked = false,
                    isDisliked = false,
                    channelName = "",
                    channelImageUrl = "",
                    channelSubscriberCount = "",
                    isSubscribedToChannel = false
                )
            )
        }
    }
}