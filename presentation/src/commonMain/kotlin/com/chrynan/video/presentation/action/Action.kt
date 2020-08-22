package com.chrynan.video.presentation.action

import com.chrynan.video.presentation.state.Change
import com.chrynan.video.presentation.state.Intent
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

interface Action<I : Intent, C : Change> {

    fun perform(intent: I): Flow<C>

    operator fun invoke(intent: I): Flow<C> = perform(intent)
}

@OptIn(FlowPreview::class)
fun <I : Intent, C : Change> Flow<I>.perform(action: Action<I, C>): Flow<C> =
    flatMapConcat { action(it) }

@OptIn(FlowPreview::class)
fun <I : Intent, C : Change> Flow<I>.perform(action: (I) -> Flow<C>): Flow<C> =
    flatMapConcat { action(it) }