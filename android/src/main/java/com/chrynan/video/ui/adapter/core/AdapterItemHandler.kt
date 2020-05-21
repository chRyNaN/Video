package com.chrynan.video.ui.adapter.core

import com.chrynan.aaaah.DiffResult
import com.chrynan.video.viewmodel.AdapterItem
import kotlinx.coroutines.flow.Flow

interface AdapterItemHandler<VM : AdapterItem> {

    fun Flow<Collection<VM>>.calculateAndDispatchDiff(): Flow<DiffResult<VM>>
}

fun <VM : AdapterItem> Flow<Collection<VM>>.calculateAndDispatchDiff(itemHandler: AdapterItemHandler<VM>): Flow<DiffResult<VM>> =
    itemHandler.run {
        calculateAndDispatchDiff()
    }