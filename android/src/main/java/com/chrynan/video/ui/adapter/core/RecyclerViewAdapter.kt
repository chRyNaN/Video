package com.chrynan.video.ui.adapter.core

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.viewmodel.AdapterItem

open class RecyclerViewAdapter(
    adapters: Set<BaseAdapter<*>> = emptySet(),
    layoutManager: LinearLayoutManager
) : BaseManagerAdapter<AdapterItem>(adapters = adapters, layoutManager = layoutManager)
