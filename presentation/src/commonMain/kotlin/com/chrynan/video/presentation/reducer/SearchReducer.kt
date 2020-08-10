package com.chrynan.video.presentation.reducer

import com.chrynan.video.common.Inject
import com.chrynan.video.presentation.state.SearchChange
import com.chrynan.video.presentation.state.SearchState

class SearchReducer @Inject constructor(): Reducer<SearchState, SearchChange> {

    override suspend fun reduce(previous: SearchState, change: SearchChange): SearchState {
        TODO("Not yet implemented")
    }
}