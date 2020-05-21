package com.chrynan.video.viewmodel

import com.chrynan.aaaah.asUniqueAdapterId

data class SectionHeaderViewModel(val header: String) : AdapterItem {

    override val uniqueAdapterId = "SectionHeader:$header".asUniqueAdapterId()
}