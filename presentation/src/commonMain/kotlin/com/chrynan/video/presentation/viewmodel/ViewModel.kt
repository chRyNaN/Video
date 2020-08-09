package com.chrynan.video.presentation.viewmodel

interface ViewModel {

    val uniqueId: Long
        get() = hashCode().toLong()
}

inline fun <reified T : Any> T.asUniqueId() = hashCode().toLong()