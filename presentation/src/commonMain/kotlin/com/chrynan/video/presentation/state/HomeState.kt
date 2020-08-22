package com.chrynan.video.presentation.state

import com.chrynan.video.presentation.viewmodel.AdapterItem

sealed class HomeState : State {

    object LoadingInitial : HomeState()

    data class LoadingMore(val currentItems: List<AdapterItem> = emptyList()) : HomeState()

    data class Refreshing(val currentItems: List<AdapterItem> = emptyList()) : HomeState()

    object DisplayingEmpty : HomeState()

    data class DisplayingLoaded(val items: List<AdapterItem>) : HomeState()
}

sealed class HomeIntent : Intent {

    object LoadInitial : HomeIntent()

    data class LoadMore(val currentItems: List<AdapterItem> = emptyList()) : HomeIntent()

    data class Refresh(val currentItems: List<AdapterItem> = emptyList()) : HomeIntent()
}

sealed class HomeChange : Change {

    data class Loaded(val items: List<AdapterItem> = emptyList()) : HomeChange()

    data class StartedLoading(val currentItems: List<AdapterItem> = emptyList()) : HomeChange()

    data class StartedRefreshing(val currentItems: List<AdapterItem> = emptyList()) : HomeChange()
}

val HomeState.currentItems: List<AdapterItem>
    get() = when (this) {
        is HomeState.LoadingInitial -> emptyList()
        is HomeState.Refreshing -> currentItems
        is HomeState.LoadingMore -> currentItems
        is HomeState.DisplayingLoaded -> items
        is HomeState.DisplayingEmpty -> emptyList()
    }