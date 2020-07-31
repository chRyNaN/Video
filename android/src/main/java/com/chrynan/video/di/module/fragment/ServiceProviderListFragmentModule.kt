package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.navigator.ServiceProviderListNavigator
import com.chrynan.video.ui.adapter.provider.ServiceProviderListItemAdapter
import com.chrynan.video.ui.fragment.ServiceProviderListFragment
import com.chrynan.video.ui.view.ServiceProviderListView
import dagger.Binds
import dagger.Module

@Module
internal abstract class ServiceProviderListFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderListView(fragment: ServiceProviderListFragment): ServiceProviderListView

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderListNavigator(fragment: ServiceProviderListFragment): ServiceProviderListNavigator

    @Binds
    @FragmentScope
    abstract fun bindServiceProviderListItemSelectedListener(fragment: ServiceProviderListFragment): ServiceProviderListItemAdapter.ServiceProviderListItemSelectedListener
}