package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.presentation.navigator.Navigator
import com.chrynan.video.presentation.navigator.ServiceProviderDetailsScreen
import com.chrynan.video.presentation.state.ServiceProviderDetailsIntent
import com.chrynan.video.presentation.state.ServiceProviderDetailsState
import com.chrynan.video.presentation.view.View
import com.chrynan.video.ui.fragment.ServiceProviderDetailsFragment
import dagger.Binds
import dagger.Module

@Module
internal abstract class ServiceProviderDetailsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderDetailsView(fragment: ServiceProviderDetailsFragment): View<ServiceProviderDetailsIntent, ServiceProviderDetailsState>

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderDetailsNavigator(fragment: ServiceProviderDetailsFragment): Navigator<ServiceProviderDetailsScreen>
}