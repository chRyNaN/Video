package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.model.core.ID
import com.chrynan.common.model.core.UriString
import com.chrynan.common.repository.ChannelRepository
import com.chrynan.common.utils.flowFrom
import com.chrynan.video.ui.view.ChannelView
import javax.inject.Inject

class ChannelPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val view: ChannelView,
    private val channelRepository: ChannelRepository
) : BasePresenter(dispatchers) {

    fun loadData(providerUri: UriString, channelId: ID) {
        flowFrom { channelRepository.getById(providerUri = providerUri, channelId = channelId) }
    }
}