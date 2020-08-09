package com.chrynan.video.presentation.reducer

import com.chrynan.video.presentation.state.Change
import com.chrynan.video.presentation.state.State

interface Reducer<S : State, C : Change> {

    suspend fun reduce(previous: S, change: C): S

    suspend operator fun invoke(previous: S, change: C): S = reduce(previous, change)
}