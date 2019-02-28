package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.kotlinutils.runThenTrue
import com.chrynan.video.R
import com.chrynan.video.model.VideoRecommendationViewModel
import kotlinx.android.synthetic.main.adapter_video_recommendation.view.*

class VideoRecommendationAdapter(private val listener: VideoRecommendationAdapter.Listener) :
    AnotherAdapter<VideoRecommendationViewModel>() {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is VideoRecommendationViewModel

    override fun onCreateView(parent: ViewGroup, viewType: ViewType): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_recommendation, parent, false)

    override fun onBindItem(view: View, item: VideoRecommendationViewModel) {
        view.apply {
            titleTextView?.text = item.title
            channelNameTextView?.text = item.channelName
            detailsTextView?.text = item.detailText
            videoLengthTextView?.text = item.videoLength

            videoRecommendationBackgroundView?.setOnClickListener {
                listener.playVideoSelected(
                    videoId = item.videoId,
                    channelId = item.channelId,
                    providerUrl = item.providerUrl
                )
            }

            val optionsMenu = PopupMenu(context, overflowOptionsImageView).apply {
                inflate(R.menu.menu_video_recommendation_options)
                setOnMenuItemClickListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.action_dismiss -> runThenTrue {
                            listener.dismissSelected(
                                videoId = item.videoId,
                                channelId = item.channelId,
                                providerUrl = item.providerUrl
                            )
                        }
                        R.id.action_share -> runThenTrue {
                            listener.shareSelected(
                                videoId = item.videoId,
                                channelId = item.channelId,
                                providerUrl = item.providerUrl
                            )
                        }
                        R.id.action_report -> runThenTrue {
                            listener.reportSelected(
                                videoId = item.videoId,
                                channelId = item.channelId,
                                providerUrl = item.providerUrl
                            )
                        }
                        else -> false
                    }
                }
            }

            overflowOptionsImageView?.setOnClickListener { optionsMenu?.show() }
        }
    }

    interface Listener {

        fun playVideoSelected(videoId: String, channelId: String, providerUrl: String)

        fun dismissSelected(videoId: String, channelId: String, providerUrl: String)

        fun shareSelected(videoId: String, channelId: String, providerUrl: String)

        fun reportSelected(videoId: String, channelId: String, providerUrl: String)
    }
}