package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.navigator.SettingsNavigator
import com.chrynan.video.ui.adapter.settings.SettingsItemAdapter
import com.chrynan.video.ui.fragment.SettingsFragment
import com.chrynan.video.ui.view.SettingsView
import dagger.Binds
import dagger.Module

@Module
internal abstract class SettingsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindSettingsView(fragment: SettingsFragment): SettingsView

    @Binds
    @FragmentScope
    abstract fun bindSettingsNavigator(fragment: SettingsFragment): SettingsNavigator

    @Binds
    @FragmentScope
    abstract fun bindSettingsItemSelectedListener(fragment: SettingsFragment): SettingsItemAdapter.SettingsItemSelectedListener
}