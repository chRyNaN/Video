package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.navigator.ServiceProviderDetailsNavigator
import com.chrynan.video.ui.fragment.ServiceProviderDetailsFragment
import com.chrynan.video.ui.view.ServiceProviderDetailsView
import dagger.Binds
import dagger.Module

@Module
internal abstract class ServiceProviderDetailsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderDetailsView(fragment: ServiceProviderDetailsFragment): ServiceProviderDetailsView

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderDetailsNavigator(fragment: ServiceProviderDetailsFragment): ServiceProviderDetailsNavigator
}