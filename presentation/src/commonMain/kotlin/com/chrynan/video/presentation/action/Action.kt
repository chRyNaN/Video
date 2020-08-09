package com.chrynan.video.presentation.action

import com.chrynan.video.presentation.state.Change
import com.chrynan.video.presentation.state.Intent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

typealias Action<INTENT, CHANGE> = suspend (INTENT) -> CHANGE

fun <I : Intent, C : Change> Flow<I>.perform(action: Action<I, C>): Flow<C> = map { action(it) }