package com.chrynan.video.presentation.view

import com.chrynan.video.presentation.state.Intent
import com.chrynan.video.presentation.state.State
import kotlinx.coroutines.flow.Flow

interface View<I : Intent, S : State> {

    fun intents(): Flow<I>

    fun render(state: S)
}