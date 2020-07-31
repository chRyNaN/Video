package com.chrynan.video.ui.adapter.factory

import androidx.recyclerview.widget.RecyclerView
import com.chrynan.aaaah.*
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.core.BaseAdapterItemHandler
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.viewmodel.AdapterItem

abstract class BaseAdapterFactory : AdapterFactory {

    abstract val coroutineDispatchers: CoroutineDispatchers

    abstract val adapters: Set<BaseAdapter<*>>

    override val decorators: List<RecyclerView.ItemDecoration> by lazy { emptyList() }

    override val diffUtilCalculator: DiffUtilCalculator<AdapterItem> by lazy { DiffUtilCalculator() }

    override val diffProcessor: DiffProcessor<AdapterItem> by lazy {
        AndroidDiffProcessor(
            diffUtilCalculator
        )
    }

    override val diffDispatcher: DiffDispatcher<AdapterItem> by lazy { AndroidDiffDispatcher(adapter) }

    override val adapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter(
            adapters = adapters,
            layoutManager = layoutManager
        )
    }

    override val adapterItemHandler: AdapterItemHandler<AdapterItem> by lazy {
        BaseAdapterItemHandler(
            diffProcessor = diffProcessor,
            diffDispatcher = diffDispatcher,
            coroutineDispatchers = coroutineDispatchers
        )
    }
}