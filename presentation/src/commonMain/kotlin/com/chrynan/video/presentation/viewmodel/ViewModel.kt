package com.chrynan.video.presentation.viewmodel

interface ViewModel {

    val uniqueId: String
        get() = hashCode().toString()
}