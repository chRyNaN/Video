package com.chrynan.video.presentation.presenter

import com.chrynan.inject.Inject
import com.chrynan.video.common.coroutine.CoroutineDispatchers
import com.chrynan.video.common.repository.SettingsInfoRepository
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.reducer.SettingsReducer
import com.chrynan.video.presentation.state.SettingsChange
import com.chrynan.video.presentation.state.SettingsIntent
import com.chrynan.video.presentation.state.SettingsState

class SettingsPresenter @Inject constructor(
    dispatchers: CoroutineDispatchers,
    override val view: View<SettingsIntent, SettingsState>,
    override val reducer: SettingsReducer,
    private val settingsInfoRepository: SettingsInfoRepository
) : BasePresenter<SettingsIntent, SettingsState, SettingsChange>(
    initialState = SettingsState.Initial,
    dispatchers = dispatchers
) {

    override fun onBind() {
        super.onBind()

        // TODO
        view.intents()
    }
}