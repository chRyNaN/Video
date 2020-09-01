package com.chrynan.video.presentation.reducer

import com.chrynan.inject.Inject
import com.chrynan.video.presentation.core.Reducer
import com.chrynan.video.presentation.state.HomeChange
import com.chrynan.video.presentation.state.HomeState

class HomeReducer @Inject constructor() : Reducer<HomeState, HomeChange> {

    override suspend fun reduce(previous: HomeState, change: HomeChange): HomeState =
        when {
            change is HomeChange.Loaded && change.items.isNullOrEmpty() -> HomeState.DisplayingEmpty
            change is HomeChange.Loaded -> HomeState.DisplayingLoaded(items = change.items)
            previous is HomeState.DisplayingEmpty && change is HomeChange.StartedLoading -> HomeState.LoadingMore(
                currentItems = change.currentItems
            )
            previous is HomeState.DisplayingLoaded && change is HomeChange.StartedLoading -> HomeState.LoadingMore(
                currentItems = change.currentItems
            )
            previous is HomeState.DisplayingEmpty && change is HomeChange.StartedRefreshing -> HomeState.Refreshing(
                currentItems = change.currentItems
            )
            previous is HomeState.DisplayingLoaded && change is HomeChange.StartedRefreshing -> HomeState.Refreshing(
                currentItems = change.currentItems
            )
            else -> previous
        }
}