package com.chrynan.video.presentation.core

interface AdapterItem : ViewModel,
    UniqueAdapterItem {

    override val uniqueAdapterId: AdapterId
        get() = uniqueId
}