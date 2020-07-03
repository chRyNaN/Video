package com.chrynan.video.presenter

import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.repository.FeedItemRepository
import com.chrynan.video.di.qualifier.HomeQualifier
import com.chrynan.video.mapper.video.VideoShowcaseMapper
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.viewmodel.AdapterItem
import javax.inject.Inject

class HomePresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val feedRepository: FeedItemRepository,
    private val mapper: VideoShowcaseMapper,
    @HomeQualifier.AdapterItemHandler private val adapterItemHandler: AdapterItemHandler<AdapterItem>
) : BasePresenter(dispatchers) {

    fun loadFeed() {

    }
}