package com.chrynan.video.ui.adapter.core

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.aaaah.*
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.viewmodel.AdapterItem

class AdapterComponents(
    val layoutManager: LinearLayoutManager,
    val adapter: RecyclerViewAdapter,
    val coroutineDispatchers: CoroutineDispatchers,
    val diffCalculator: DiffUtilCalculator<AdapterItem> = DiffUtilCalculator(),
    val diffProcessor: DiffProcessor<AdapterItem> = AndroidDiffProcessor(diffCalculator),
    val diffDispatcher: DiffDispatcher<AdapterItem> = AndroidDiffDispatcher(adapter)
) {

    val itemListUpdated: ItemListUpdater<AdapterItem> = adapter

    val adapterItemHandler: AdapterItemHandler<AdapterItem> = BaseAdapterItemHandler(
        diffProcessor = diffProcessor,
        diffDispatcher = diffDispatcher,
        coroutineDispatchers = coroutineDispatchers
    )
}