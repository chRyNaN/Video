package com.chrynan.video.presentation.reducer

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.core.Reducer
import com.chrynan.video.presentation.state.SearchChange
import com.chrynan.video.presentation.state.SearchState

class SearchReducer @Inject constructor() : Reducer<SearchState, SearchChange> {

    override suspend fun reduce(previous: SearchState, change: SearchChange): SearchState =
        when {
            change is SearchChange.Cleared -> SearchState.DisplayingNoInput
            change is SearchChange.Loaded && change.items.isEmpty() -> SearchState.DisplayingEmpty(
                query = change.query
            )
            change is SearchChange.Loaded -> SearchState.DisplayingLoaded(
                query = change.query,
                items = change.items
            )
            change is SearchChange.StartedSearching -> SearchState.Searching(query = change.query)
            previous is SearchState.DisplayingLoaded && change is SearchChange.StartedLoading -> SearchState.LoadingMore(
                query = change.query,
                currentItems = change.currentItems
            )
            else -> previous
        }
}