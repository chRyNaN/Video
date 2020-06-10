package com.chrynan.video.ui.adapter.binder

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.ui.adapter.SearchTagItemAdapter
import com.chrynan.video.ui.adapter.core.AdapterComponentsBinder
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.utils.ActivityContext
import javax.inject.Inject

class SearchTagAdapterComponentsBinder @Inject constructor(
    @ActivityContextQualifier override val context: ActivityContext,
    override val coroutineDispatchers: CoroutineDispatchers,
    private val searchTagItemAdapter: SearchTagItemAdapter
) : AdapterComponentsBinder() {

    override fun createLayoutManager(): LinearLayoutManager =
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

    override fun createAdapter(layoutManager: LinearLayoutManager): RecyclerViewAdapter =
        RecyclerViewAdapter(adapters = setOf(searchTagItemAdapter), layoutManager = layoutManager)
}