package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.presentation.navigator.Navigator
import com.chrynan.video.presentation.navigator.ServiceProviderListScreen
import com.chrynan.video.presentation.state.ServiceProviderListIntent
import com.chrynan.video.presentation.state.ServiceProviderListState
import com.chrynan.video.presentation.view.View
import com.chrynan.video.ui.adapter.provider.ServiceProviderListItemAdapter
import com.chrynan.video.ui.fragment.ServiceProviderListFragment
import dagger.Binds
import dagger.Module

@Module
internal abstract class ServiceProviderListFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderListView(fragment: ServiceProviderListFragment): View<ServiceProviderListIntent, ServiceProviderListState>

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderListNavigator(fragment: ServiceProviderListFragment): Navigator<ServiceProviderListScreen>

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderListItemSelectedListener(fragment: ServiceProviderListFragment): ServiceProviderListItemAdapter.ServiceProviderListItemSelectedListener
}