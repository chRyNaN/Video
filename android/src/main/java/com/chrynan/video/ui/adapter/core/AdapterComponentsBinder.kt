package com.chrynan.video.ui.adapter.core

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.utils.ActivityContext

abstract class AdapterComponentsBinder {

    abstract val context: ActivityContext

    abstract val coroutineDispatchers: CoroutineDispatchers

    abstract fun createLayoutManager(): LinearLayoutManager

    abstract fun createAdapter(layoutManager: LinearLayoutManager): RecyclerViewAdapter

    fun bindRecyclerView(view: RecyclerView): AdapterComponents {
        var adapterComponents = view.tag as? AdapterComponents

        var adapter = adapterComponents?.adapter ?: view.adapter as? RecyclerViewAdapter
        var layoutManager =
            adapterComponents?.layoutManager ?: view.layoutManager as? LinearLayoutManager

        if (layoutManager == null) {
            layoutManager = createLayoutManager()
        }

        if (adapter == null) {
            adapter = createAdapter(layoutManager)
        }

        if (adapterComponents == null) {
            adapterComponents = AdapterComponents(
                layoutManager = layoutManager,
                adapter = adapter,
                coroutineDispatchers = coroutineDispatchers
            )

            view.adapter = adapter
            view.layoutManager = layoutManager
        }

        view.tag = adapterComponents

        return adapterComponents
    }
}