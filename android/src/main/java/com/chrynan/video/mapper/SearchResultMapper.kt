package com.chrynan.video.mapper

import com.chrynan.common.mapper.Mapper
import com.chrynan.common.model.wrapper.SearchResultItemWrapper
import com.chrynan.video.viewmodel.AdapterItem
import com.chrynan.video.viewmodel.ChannelListItemViewModel
import com.chrynan.video.viewmodel.VideoRecommendationViewModel
import javax.inject.Inject

class SearchResultMapper @Inject constructor() : Mapper<SearchResultItemWrapper, AdapterItem> {

    override suspend fun map(model: SearchResultItemWrapper): AdapterItem =
        when (model) {
            is SearchResultItemWrapper.Video ->
                VideoRecommendationViewModel(
                    title = model.videoResult.video.name,
                    channelName = model.videoResult.channel.name,
                    detailText = model.videoResult.video.description,
                    videoInfo = model.videoResult.info,
                    videoLength = model.videoResult.video.lengthInMilliseconds.toString()
                )
            is SearchResultItemWrapper.Channel ->
                ChannelListItemViewModel(
                    channelId = model.channelResult.channel.id,
                    providerUri = model.channelResult.provider.uri,
                    title = model.channelResult.channel.name,
                    description = model.channelResult.channel.description,
                    channelImageUri = model.channelResult.channel.images.thumbnail,
                    isSubscribed = model.channelResult.channel.isSubscribed
                )
        }
}