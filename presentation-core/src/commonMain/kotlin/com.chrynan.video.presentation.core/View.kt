package com.chrynan.video.presentation.core

import kotlinx.coroutines.flow.Flow

interface View<I : Intent, S : State> {

    fun intents(): Flow<I>

    fun render(state: S)
}