package com.chrynan.video.presentation.core

interface ViewModel {

    val uniqueId: Long
        get() = hashCode().toLong()
}

inline fun <reified T : Any> T.asUniqueId() = hashCode().toLong()