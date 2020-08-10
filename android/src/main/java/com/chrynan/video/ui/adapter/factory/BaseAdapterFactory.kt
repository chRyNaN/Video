package com.chrynan.video.ui.adapter.factory

import androidx.recyclerview.widget.RecyclerView
import com.chrynan.aaaah.*
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.viewmodel.AdapterItem
import com.chrynan.video.ui.adapter.core.AdapterItemHandler
import com.chrynan.video.ui.adapter.core.BaseAdapter
import com.chrynan.video.ui.adapter.core.BaseAdapterItemHandler
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter

abstract class BaseAdapterFactory : AdapterFactory {

    abstract val coroutineDispatchers: CoroutineDispatchers

    abstract val adapters: Set<BaseAdapter<*>>

    @Suppress("RemoveExplicitTypeArguments") // For some reason the build fails without the explicit type parameter
    override val decorators: List<RecyclerView.ItemDecoration> by lazy { emptyList<RecyclerView.ItemDecoration>() }

    @Suppress("RemoveExplicitTypeArguments") // For some reason the build fails without the explicit type parameter
    override val diffUtilCalculator: DiffUtilCalculator<AdapterItem> by lazy { DiffUtilCalculator<AdapterItem>() }

    override val diffProcessor: DiffProcessor<AdapterItem> by lazy {
        AndroidDiffProcessor(
            diffUtilCalculator
        )
    }

    override val diffDispatcher: DiffDispatcher<AdapterItem> by lazy { AndroidDiffDispatcher(adapter) }

    override val adapter: RecyclerViewAdapter by lazy {
        RecyclerViewAdapter(
            adapters = adapters,
            positionManager = positionManager
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