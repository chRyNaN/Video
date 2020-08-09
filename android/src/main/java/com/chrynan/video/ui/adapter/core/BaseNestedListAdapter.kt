package com.chrynan.video.ui.adapter.core

import androidx.recyclerview.widget.RecyclerView
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.logger.Logger
import com.chrynan.video.presentation.viewmodel.AdapterItem
import com.chrynan.video.ui.adapter.factory.AdapterFactory
import com.chrynan.video.ui.adapter.factory.bindAdapterFactory
import com.chrynan.video.ui.adapter.factory.calculateAndDispatchDiff
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn

abstract class BaseNestedListAdapter<VM : AdapterItem>(dispatchers: CoroutineDispatchers) :
    BaseAdapter<VM>(dispatchers) {

    private var nestedItemsJob: Job? = null

    abstract fun createNewAdapterFactory(): AdapterFactory

    fun bindNestedItems(recyclerView: RecyclerView?, items: List<AdapterItem>) {
        val adapterFactory = bindNestedRecyclerView(recyclerView)

        nestedItemsJob?.cancel()

        nestedItemsJob = flowOf(items)
            .calculateAndDispatchDiff(adapterFactory)
            .catch {
                Logger.logError(
                    throwable = it,
                    message = "Error binding nested adapter items."
                )
            }
            .launchIn(this)
    }

    private fun bindNestedRecyclerView(recyclerView: RecyclerView?): AdapterFactory {
        var adapterFactory = recyclerView?.adapterFactor

        if (adapterFactory == null) {
            adapterFactory = createNewAdapterFactory()
            recyclerView?.adapterFactor = adapterFactory
        }

        recyclerView?.bindAdapterFactory(adapterFactory)

        return adapterFactory
    }

    private var RecyclerView.adapterFactor: AdapterFactory?
        get() = try {
            tag as? AdapterFactory
        } catch (throwable: Throwable) {
            null
        }
        set(value) {
            tag = value
        }
}