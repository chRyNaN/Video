package com.chrynan.video.ui.adapter.factory

import androidx.recyclerview.widget.RecyclerView
import com.chrynan.aaaah.DiffDispatcher
import com.chrynan.aaaah.DiffProcessor
import com.chrynan.aaaah.DiffResult
import com.chrynan.aaaah.DiffUtilCalculator
import com.chrynan.video.presentation.core.AdapterItem
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.position.AdapterPositionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface AdapterFactory {

    val decorators: List<RecyclerView.ItemDecoration>

    val positionManager: AdapterPositionManager

    val adapter: RecyclerViewAdapter

    val diffUtilCalculator: DiffUtilCalculator<AdapterItem>

    val diffProcessor: DiffProcessor<AdapterItem>

    val diffDispatcher: DiffDispatcher<AdapterItem>

    val adapterItemHandler: AdapterItemHandler<AdapterItem>
}

fun RecyclerView.bindAdapterFactory(factory: AdapterFactory) {
    layoutManager = factory.positionManager.layoutManager
    adapter = factory.adapter
    factory.decorators.forEach { addItemDecoration(it) }
}

fun Flow<Collection<AdapterItem>>.calculateAndDispatchDiff(adapterFactory: AdapterFactory): Flow<DiffResult<AdapterItem>> =
    adapterFactory.adapterItemHandler.run {
        calculateAndDispatchDiff()
    }

fun AdapterFactory.calculateAndDispatchDiff(items: List<AdapterItem>): Flow<DiffResult<AdapterItem>> =
    flowOf(items).calculateAndDispatchDiff(this)