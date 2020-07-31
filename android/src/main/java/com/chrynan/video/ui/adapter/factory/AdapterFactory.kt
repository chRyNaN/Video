package com.chrynan.video.ui.adapter.factory

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.aaaah.DiffDispatcher
import com.chrynan.aaaah.DiffProcessor
import com.chrynan.aaaah.DiffResult
import com.chrynan.aaaah.DiffUtilCalculator
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.position.AdapterPositionManager
import com.chrynan.video.viewmodel.AdapterItem
import kotlinx.coroutines.flow.Flow

interface AdapterFactory {

    val decorators: List<RecyclerView.ItemDecoration>

    val layoutManager: LinearLayoutManager

    val positionManager: AdapterPositionManager

    val adapter: RecyclerViewAdapter

    val diffUtilCalculator: DiffUtilCalculator<AdapterItem>

    val diffProcessor: DiffProcessor<AdapterItem>

    val diffDispatcher: DiffDispatcher<AdapterItem>

    val adapterItemHandler: AdapterItemHandler<AdapterItem>
}

fun RecyclerView.bindAdapterFactory(factory: AdapterFactory) {
    layoutManager = factory.layoutManager
    adapter = factory.adapter
    factory.decorators.forEach { addItemDecoration(it) }
}

fun Flow<Collection<AdapterItem>>.calculateAndDispatchDiff(adapterFactory: AdapterFactory): Flow<DiffResult<AdapterItem>> =
    adapterFactory.adapterItemHandler.run {
        calculateAndDispatchDiff()
    }