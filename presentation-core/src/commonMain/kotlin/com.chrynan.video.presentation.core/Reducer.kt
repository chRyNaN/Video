package com.chrynan.video.presentation.core

interface Reducer<S : State, C : Change> {

    suspend fun reduce(previous: S, change: C): S

    suspend operator fun invoke(previous: S, change: C): S = reduce(previous, change)
}