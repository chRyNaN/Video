package com.chrynan.video.presentation.reducer

import com.chrynan.inject.Inject
import com.chrynan.video.presentation.core.Reducer
import com.chrynan.video.presentation.state.SettingsChange
import com.chrynan.video.presentation.state.SettingsState

class SettingsReducer @Inject constructor() : Reducer<SettingsState, SettingsChange> {

    override suspend fun reduce(previous: SettingsState, change: SettingsChange): SettingsState {
        TODO("Not yet implemented")
    }
}