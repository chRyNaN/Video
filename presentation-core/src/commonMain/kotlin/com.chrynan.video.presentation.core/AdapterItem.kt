package com.chrynan.video.presentation.core

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.UniqueAdapterItem

interface AdapterItem : ViewModel,
    UniqueAdapterItem {

    override val uniqueAdapterId: AdapterId
        get() = uniqueId
}