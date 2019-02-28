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
import com.chrynan.video.model.VideoRecommendationViewModel
import com.chrynan.video.ui.adapter.VideoInfoAdapter
import com.chrynan.video.ui.adapter.VideoRecommendationAdapter
import com.chrynan.video.ui.view.CollapsibleVideoView
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : BaseFragment(),
    CollapsibleVideoView,
    VideoInfoAdapter.Listener,
    VideoRecommendationAdapter.Listener {

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

    override var contentContainerAlpha: Float
        get() = recyclerView?.alpha ?: 0f
        set(value) {
            recyclerView?.alpha = value
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_video, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView?.apply {
            val managerAdapter =
                ManagerRecyclerViewAdapter<UniqueAdapterItem>(
                    adapters = setOf(
                        VideoInfoAdapter(this@VideoFragment),
                        VideoRecommendationAdapter(this@VideoFragment)
                    )
                )
            layoutManager = LinearLayoutManager(context)
            adapter = managerAdapter

            managerAdapter.items = listOf(
                VideoInfoViewModel(
                    videoId = "Video Id",
                    channelId = "Channel Id",
                    providerUrl = "www.chrynan.com",
                    providerServiceName = "chRyNaN",
                    uniqueAdapterId = 0,
                    title = "A Really Cool Video",
                    viewCount = "225k",
                    description = "This is a really cool video that you may enjoy watching.",
                    publishedDate = "Today, 12pm",
                    category = "Software Development",
                    tags = listOf("Software", "Kotlin", "Android"),
                    supportsRating = true,
                    likeButtonText = "Like",
                    dislikeButtonText = "Dislike",
                    shareButtonText = "Share",
                    isLiked = false,
                    isDisliked = false,
                    channelName = "chRyNaN Codes",
                    channelImageUrl = "www.chrynan.com",
                    channelSubscriberCount = "250m",
                    isSubscribedToChannel = false,
                    showCategory = true,
                    showTags = true,
                    showChannelSubscribeCount = true
                ),
                VideoRecommendationViewModel(
                    title = "A Really Cool Video",
                    channelName = "chRyNaN Codes",
                    detailText = "Provided by chRyNaN",
                    channelId = "",
                    videoId = "",
                    providerUrl = "",
                    videoImageUrl = "",
                    videoLength = "10:00"
                )
            )
        }
    }

    override fun likeButtonSelected(videoId: String, channelId: String, providerUrl: String) {

    }

    override fun dislikeButtonSelected(videoId: String, channelId: String, providerUrl: String) {
    }

    override fun shareButtonSelected(videoId: String, channelId: String, providerUrl: String) {
    }

    override fun channelSelected(channelId: String, providerUrl: String) {
    }

    override fun subscribeButtonSelected(channelId: String, providerUrl: String) {
    }

    override fun providerSelected(providerUrl: String) {
    }

    override fun categorySelected(category: String) {
    }

    override fun tagSelected(tag: String) {
    }

    override fun playVideoSelected(videoId: String, channelId: String, providerUrl: String) {
    }

    override fun dismissSelected(videoId: String, channelId: String, providerUrl: String) {
    }

    override fun shareSelected(videoId: String, channelId: String, providerUrl: String) {
    }

    override fun reportSelected(videoId: String, channelId: String, providerUrl: String) {
    }
}