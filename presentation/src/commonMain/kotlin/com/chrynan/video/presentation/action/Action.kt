package com.chrynan.video.presentation.action

import com.chrynan.video.presentation.state.Change
import com.chrynan.video.presentation.state.Intent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface Action<I : Intent, C : Change> {

    suspend fun perform(intent: I): C

    suspend operator fun invoke(intent: I): C
}

fun <I : Intent, C : Change> Flow<I>.perform(action: Action<I, C>): Flow<C> = map { action(it) }