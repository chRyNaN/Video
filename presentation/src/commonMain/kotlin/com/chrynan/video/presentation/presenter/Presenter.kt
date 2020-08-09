package com.chrynan.video.presentation.presenter

import com.chrynan.video.presentation.reducer.Reducer
import com.chrynan.video.presentation.state.Change
import com.chrynan.video.presentation.state.Intent
import com.chrynan.video.presentation.state.State
import com.chrynan.video.presentation.view.View

interface Presenter<I : Intent, S : State, C : Change> {

    val currentState: S

    val view: View<I, S>

    val reducer: Reducer<S, C>

    fun bind()

    fun unbind()
}