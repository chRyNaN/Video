package com.chrynan.video.presentation.core

expect interface UniqueAdapterItem {

    val uniqueAdapterId: AdapterId
}

inline fun <reified T : Any> T.asUniqueAdapterId(): AdapterId = hashCode().toLong()