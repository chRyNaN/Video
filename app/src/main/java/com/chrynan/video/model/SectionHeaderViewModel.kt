package com.chrynan.video.model

import com.chrynan.aaaah.AdapterId
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.aaaah.asUniqueAdapterId

data class SectionHeaderViewModel(val header: String) : UniqueAdapterItem {

    override val uniqueAdapterId: AdapterId = header.asUniqueAdapterId()
}