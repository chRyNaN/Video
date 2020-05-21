package com.chrynan.video.ui.adapter.core

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.presentation.viewmodel.AdapterItem

open class RecyclerViewAdapter(
    adapters: Set<BaseAdapter<*>> = emptySet(),
    layoutManager: LinearLayoutManager
) : BaseManagerAdapter<AdapterItem>(adapters = adapters, layoutManager = layoutManager)
