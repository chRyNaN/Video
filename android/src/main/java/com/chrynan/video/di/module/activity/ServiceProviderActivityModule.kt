package com.chrynan.video.di.module.activity

import com.chrynan.video.di.module.fragment.ServiceProviderDetailsFragmentModule
import com.chrynan.video.di.module.fragment.NewServiceProviderFragmentModule
import com.chrynan.video.di.module.fragment.ServiceProviderListFragmentModule
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.navigator.ServiceProviderNavigator
import com.chrynan.video.ui.activity.ServiceProviderActivity
import com.chrynan.video.ui.fragment.ServiceProviderDetailsFragment
import com.chrynan.video.ui.fragment.NewServiceProviderFragment
import com.chrynan.video.ui.fragment.ServiceProviderListFragment
import com.chrynan.video.utils.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ServiceProviderActivityModule {

    @Binds
    @ActivityScope
    @ActivityContextQualifier
    abstract fun bindActivityContext(activity: ServiceProviderActivity): ActivityContext

    @Binds
    @ActivityScope
    abstract fun bindServiceProviderNavigator(activity: ServiceProviderActivity): ServiceProviderNavigator

    @FragmentScope
    @ContributesAndroidInjector(modules = [ServiceProviderListFragmentModule::class])
    abstract fun serviceProviderListFragmentInjector(): ServiceProviderListFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [NewServiceProviderFragmentModule::class])
    abstract fun newServiceProviderFragmentInjector(): NewServiceProviderFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [ServiceProviderDetailsFragmentModule::class])
    abstract fun serviceProviderDetailsFragmentInjector(): ServiceProviderDetailsFragment
}