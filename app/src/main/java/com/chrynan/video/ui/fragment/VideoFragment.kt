package com.chrynan.video.ui.fragment

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.video.R
import com.chrynan.video.model.SectionHeaderViewModel
import com.chrynan.video.model.VideoInfo
import com.chrynan.video.model.VideoInfoViewModel
import com.chrynan.video.model.VideoRecommendationViewModel
import com.chrynan.video.ui.adapter.SectionHeaderAdapter
import com.chrynan.video.ui.adapter.VideoInfoAdapter
import com.chrynan.video.ui.adapter.VideoRecommendationAdapter
import com.chrynan.video.ui.adapter.VideoShowcaseAdapter
import com.chrynan.video.ui.adapter.listener.VideoOptionsListener
import com.chrynan.video.ui.dialog.MenuBottomSheetDialogFragment
import com.chrynan.video.ui.view.CollapsibleVideoView
import kotlinx.android.synthetic.main.fragment_video.*

class VideoFragment : BaseFragment(),
    CollapsibleVideoView,
    VideoInfoAdapter.Listener,
    VideoOptionsListener {

    companion object {

        fun newInstance() = VideoFragment()
    }

    private val videoOptionsMenuBottomSheet by lazy { MenuBottomSheetDialogFragment.newInstance(menuResId = R.menu.menu_video_options) }

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
                        SectionHeaderAdapter(),
                        VideoRecommendationAdapter(this@VideoFragment),
                        VideoShowcaseAdapter(this@VideoFragment)
                    )
                )
            layoutManager = LinearLayoutManager(context)
            adapter = managerAdapter

            val videoInfo = VideoInfo(
                videoId = "VideoId",
                channelId = "ChannelId",
                providerUri = Uri.parse("ProviderUri"),
                videoUri = Uri.parse("VideoUri")
            )

            managerAdapter.items = listOf(
                VideoInfoViewModel(
                    videoInfo = videoInfo,
                    providerServiceName = "chRyNaN",
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
                SectionHeaderViewModel(header = "Recommended Videos"),
                VideoRecommendationViewModel(
                    title = "A Really Cool Video",
                    channelName = "chRyNaN Codes",
                    detailText = "Provided by chRyNaN",
                    videoInfo = videoInfo,
                    videoImageUrl = "",
                    videoLength = "10:00"
                )
            )
        }
    }

    override fun likeButtonSelected(videoInfo: VideoInfo) {

    }

    override fun dislikeButtonSelected(videoInfo: VideoInfo) {
    }

    override fun shareButtonSelected(videoInfo: VideoInfo) {
    }

    override fun channelSelected(videoInfo: VideoInfo) {
    }

    override fun subscribeButtonSelected(videoInfo: VideoInfo) {
    }

    override fun providerSelected(videoInfo: VideoInfo) {
    }

    override fun categorySelected(category: String) {
    }

    override fun tagSelected(tag: String) {
    }

    override fun videoOptionsMenuSelected(videoInfo: VideoInfo) {
        videoOptionsMenuBottomSheet.show(childFragmentManager, null)
    }
}