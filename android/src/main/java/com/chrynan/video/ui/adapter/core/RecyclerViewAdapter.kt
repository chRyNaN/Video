package com.chrynan.video.ui.adapter.core

import com.chrynan.video.ui.adapter.position.AdapterPositionManager
import com.chrynan.video.viewmodel.AdapterItem

open class RecyclerViewAdapter(
    adapters: Set<BaseAdapter<*>> = emptySet(),
    positionManager: AdapterPositionManager
) : BaseManagerAdapter<AdapterItem>(adapters = adapters, positionManager = positionManager)
