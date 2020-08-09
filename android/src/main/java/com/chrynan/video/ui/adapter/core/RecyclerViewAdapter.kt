package com.chrynan.video.ui.adapter.core

import com.chrynan.video.presentation.viewmodel.AdapterItem
import com.chrynan.video.ui.adapter.position.AdapterPositionManager

open class RecyclerViewAdapter(
    adapters: Set<BaseAdapter<*>> = emptySet(),
    positionManager: AdapterPositionManager
) : BaseManagerAdapter<AdapterItem>(adapters = adapters, positionManager = positionManager)
