package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.navigator.NewServiceProviderNavigator
import com.chrynan.video.ui.fragment.NewServiceProviderFragment
import com.chrynan.video.ui.view.NewServiceProviderView
import dagger.Binds
import dagger.Module

@Module
internal abstract class NewServiceProviderFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindNewServiceProviderView(fragment: NewServiceProviderFragment): NewServiceProviderView

    @Binds
    @FragmentScope
    abstract fun bindNewServiceProviderNavigator(fragment: NewServiceProviderFragment): NewServiceProviderNavigator
}