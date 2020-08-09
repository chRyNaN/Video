package com.chrynan.video.presentation.presenter

import com.chrynan.common.Inject
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.common.repository.SettingsInfoRepository
import com.chrynan.video.presentation.reducer.SettingsReducer
import com.chrynan.video.presentation.state.SettingsChange
import com.chrynan.video.presentation.state.SettingsIntent
import com.chrynan.video.presentation.state.SettingsState
import com.chrynan.video.presentation.view.View

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