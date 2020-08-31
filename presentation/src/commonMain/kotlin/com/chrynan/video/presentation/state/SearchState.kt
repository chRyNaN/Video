package com.chrynan.video.presentation.state

import com.chrynan.video.presentation.core.AdapterItem
import com.chrynan.video.presentation.core.Change
import com.chrynan.video.presentation.core.Intent
import com.chrynan.video.presentation.core.State

sealed class SearchState : State {

    object DisplayingNoInput : SearchState()

    data class DisplayingEmpty(val query: String) : SearchState()

    data class DisplayingLoaded(
        val query: String,
        val items: List<AdapterItem>
    ) : SearchState()

    data class Searching(val query: String) : SearchState()

    data class LoadingMore(
        val query: String,
        val currentItems: List<AdapterItem> = emptyList()
    ) : SearchState()
}

sealed class SearchIntent : Intent {

    data class Search(val query: String) : SearchIntent()

    data class LoadMore(
        val query: String,
        val currentItems: List<AdapterItem> = emptyList()
    ) : SearchIntent()

    object Clear : SearchIntent()
}

sealed class SearchChange : Change {

    data class StartedSearching(val query: String) : SearchChange()

    data class StartedLoading(
        val query: String,
        val currentItems: List<AdapterItem> = emptyList()
    ) : SearchChange()

    data class Loaded(
        val query: String,
        val items: List<AdapterItem> = emptyList()
    ) : SearchChange()

    object Cleared : SearchChange()
}

val SearchState.currentItems: List<AdapterItem>
    get() = when (this) {
        is SearchState.DisplayingNoInput -> emptyList()
        is SearchState.DisplayingEmpty -> emptyList()
        is SearchState.DisplayingLoaded -> items
        is SearchState.Searching -> emptyList()
        is SearchState.LoadingMore -> currentItems
    }