package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.presentation.core.Navigator
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.navigator.SettingsScreen
import com.chrynan.video.presentation.state.SettingsIntent
import com.chrynan.video.presentation.state.SettingsState
import com.chrynan.video.ui.adapter.settings.SettingsItemAdapter
import com.chrynan.video.ui.fragment.SettingsFragment
import dagger.Binds
import dagger.Module

@Module
internal abstract class SettingsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindSettingsView(fragment: SettingsFragment): View<SettingsIntent, SettingsState>

    @Binds
    @FragmentScope
    abstract fun bindSettingsNavigator(fragment: SettingsFragment): Navigator<SettingsScreen>

    @Binds
    @FragmentScope
    abstract fun bindSettingsItemSelectedListener(fragment: SettingsFragment): SettingsItemAdapter.SettingsItemSelectedListener
}