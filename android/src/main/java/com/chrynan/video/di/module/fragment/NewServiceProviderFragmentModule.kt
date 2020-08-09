package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.presentation.navigator.Navigator
import com.chrynan.video.presentation.navigator.NewServiceProviderScreen
import com.chrynan.video.presentation.state.NewServiceProviderIntent
import com.chrynan.video.presentation.state.NewServiceProviderState
import com.chrynan.video.presentation.view.View
import com.chrynan.video.ui.fragment.NewServiceProviderFragment
import dagger.Binds
import dagger.Module

@Module
internal abstract class NewServiceProviderFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindNewServiceProviderView(fragment: NewServiceProviderFragment): View<NewServiceProviderIntent, NewServiceProviderState>

    @Binds
    @FragmentScope
    abstract fun bindNewServiceProviderNavigator(fragment: NewServiceProviderFragment): Navigator<NewServiceProviderScreen>
}