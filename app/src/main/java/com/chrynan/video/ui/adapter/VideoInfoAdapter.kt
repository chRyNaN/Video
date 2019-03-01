package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.AnotherAdapter
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.video.R
import com.chrynan.video.model.VideoInfo
import com.chrynan.video.model.VideoInfoViewModel
import kotlinx.android.synthetic.main.adapter_video_info.view.*

class VideoInfoAdapter(private val listener: VideoInfoAdapter.Listener) : AnotherAdapter<VideoInfoViewModel>() {

    override val viewType = AdapterViewType.from(this::class.java)

    override fun onHandlesItem(item: Any) = item is VideoInfoViewModel

    override fun onCreateView(parent: ViewGroup, viewType: ViewType): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_video_info, parent, false)

    override fun onBindItem(view: View, item: VideoInfoViewModel) {
        view.apply {
            titleTextView?.text = item.title
            viewCountTextView?.text = item.viewCount
            descriptionTextView?.text = item.description

            expandDescriptionBackgroundView?.setOnClickListener {
                val wasVisible = descriptionGroup?.visibility == View.VISIBLE
                descriptionGroup?.visibility = if (wasVisible) View.GONE else View.VISIBLE
                expandIconImageView?.isActivated = !wasVisible
            }

            likeButton?.text = item.likeButtonText
            likeButton?.isActivated = item.isLiked
            dislikeButton?.text = item.dislikeButtonText
            dislikeButton?.isActivated = item.isDisliked
            shareButton?.text = item.shareButtonText

            channelNameTextView?.apply {
                text = item.channelName
                setOnClickListener {
                    listener.channelSelected(videoInfo = item.videoInfo)
                }
            }
            channelImageView?.apply {
                Glide.with(context)
                    .load(item.channelImageUrl)
                    .into(this)
            }
            channelSubscribeCountTextView?.text = item.channelSubscriberCount
            channelSubscribeCountTextView?.visibility = if (item.showChannelSubscribeCount) View.VISIBLE else View.GONE
            channelSubscribeButton?.isActivated = item.isSubscribedToChannel

            providerNameTextView?.apply {
                text = item.providerServiceName
                setOnClickListener { listener.providerSelected(videoInfo = item.videoInfo) }
            }

            publishedTimeTextView?.text = item.publishedDate

            categoryTextView?.apply {
                text = item.category
                visibility = if (item.showCategory) View.VISIBLE else View.GONE
                categoryHeaderTextView?.visibility = if (item.showCategory) View.VISIBLE else View.GONE
                setOnClickListener { listener.categorySelected(category = item.category) }
            }

            tagsTextView?.apply {
                text = item.tags.toString()
                visibility = if (item.showTags) View.VISIBLE else View.GONE
                tagsHeaderTextView?.visibility = if (item.showTags) View.VISIBLE else View.GONE
            }
        }
    }

    interface Listener {

        fun likeButtonSelected(videoInfo: VideoInfo)

        fun dislikeButtonSelected(videoInfo: VideoInfo)

        fun shareButtonSelected(videoInfo: VideoInfo)

        fun channelSelected(videoInfo: VideoInfo)

        fun subscribeButtonSelected(videoInfo: VideoInfo)

        fun providerSelected(videoInfo: VideoInfo)

        fun categorySelected(category: String)

        fun tagSelected(tag: String)
    }
}