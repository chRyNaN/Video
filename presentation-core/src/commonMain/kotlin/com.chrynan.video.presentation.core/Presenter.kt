package com.chrynan.video.presentation.core

interface Presenter<I : Intent, S : State, C : Change> : PresenterCoroutineScope {

    val currentState: S

    val view: View<I, S>

    val reducer: Reducer<S, C>

    fun bind()

    fun unbind()
}