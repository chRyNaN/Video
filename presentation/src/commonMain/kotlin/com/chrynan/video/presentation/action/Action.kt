package com.chrynan.video.presentation.action

import com.chrynan.video.presentation.state.Change
import com.chrynan.video.presentation.state.Intent
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat

interface Action<I : Intent, C : Change>

@OptIn(FlowPreview::class)
fun <I : Intent, C : Change> Flow<I>.perform(action: suspend (I) -> Flow<C>): Flow<C> =
    flatMapConcat { action(it) }