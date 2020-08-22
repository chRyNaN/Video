package com.chrynan.video.presentation.presenter

import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.presentation.coroutine.PresenterCoroutineScope
import com.chrynan.video.presentation.state.Change
import com.chrynan.video.presentation.state.Intent
import com.chrynan.video.presentation.state.State
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

abstract class BasePresenter<I : Intent, S : State, C : Change>(
    initialState: S,
    protected val dispatchers: CoroutineDispatchers
) : Presenter<I, S, C>,
    PresenterCoroutineScope {

    override var currentState: S = initialState
        protected set

    override val coroutineContext: CoroutineContext
        get() = job + dispatchers.main

    var isBound = false
        private set

    private lateinit var job: Job

    override fun bind() {
        if (!isBound) onBind()
    }

    override fun unbind() {
        if (isBound) onUnbind()
    }

    protected open fun onBind() {
        job = SupervisorJob()
        isBound = true
    }

    protected open fun onUnbind() {
        job.cancel()
        isBound = false
    }

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun Flow<C>.reduce(): Flow<S> =
        map { reducer(currentState, it) }
            .flowOn(dispatchers.io)

    @Suppress("MemberVisibilityCanBePrivate")
    protected fun Flow<S>.render(): Flow<S> =
        onEach { currentState = it }
            .onEach { view.render(it) }
            .flowOn(dispatchers.main)

    protected fun Flow<C>.reduceAndRender(): Flow<S> = reduce().render()
}