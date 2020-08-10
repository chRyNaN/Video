package com.chrynan.video.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.api.load
import com.chrynan.aaaah.Adapter
import com.chrynan.aaaah.AdapterViewType
import com.chrynan.aaaah.ViewType
import com.chrynan.aaaah.from
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.viewmodel.WatchListItemViewModel
import com.chrynan.video.R
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.common.model.core.ID
import com.chrynan.video.common.model.core.UriString
import kotlinx.android.synthetic.main.adapter_watch_list_item.view.*
import javax.inject.Inject

@Adapter
class WatchListItemAdapter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    private val listener: WatchListItemSelectedListener
) : BaseAdapter<WatchListItemViewModel>(dispatchers) {

    override val viewType = AdapterViewType.from(WatchListItemAdapter::class.java)

    override fun onHandlesItem(item: Any) = item is WatchListItemViewModel

    override fun onCreateView(
        parent: ViewGroup,
        inflater: LayoutInflater,
        viewType: ViewType
    ): View =
        LayoutInflater.from(parent.context).inflate(R.layout.adapter_watch_list_item, parent, false)

    override fun View.onBindItem(item: WatchListItemViewModel, position: Int) {
        adapterWatchlistTitleTextView?.text = item.title
        adapterWatchlistDescriptionTextView?.text = item.description
        adapterWatchlistSecondaryDescriptionTextView?.text = item.secondaryDescription

        adapterWatchlistVideoImageView?.load(item.videoImageUri)

        watchListItemContainer?.setOnClickListener {
            listener.onWatchListItemSelected(
                providerUri = item.providerUri,
                videoId = item.videoId
            )
        }
    }

    interface WatchListItemSelectedListener {

        fun onWatchListItemSelected(providerUri: UriString, videoId: ID)
    }
}