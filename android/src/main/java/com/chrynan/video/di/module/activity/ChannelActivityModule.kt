package com.chrynan.video.di.module.activity

import com.chrynan.video.coroutine.ActivityCoroutineScope
import com.chrynan.video.di.module.fragment.ChannelFragmentModule
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.di.scope.ActivityScope
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.presentation.core.Navigator
import com.chrynan.video.presentation.navigator.ChannelScreen
import com.chrynan.video.ui.activity.ChannelActivity
import com.chrynan.video.ui.fragment.ChannelFragment
import com.chrynan.video.utils.ActivityContext
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ChannelActivityModule {

    @Binds
    @ActivityScope
    @ActivityContextQualifier
    abstract fun bindActivityContext(activity: ChannelActivity): ActivityContext

    @Binds
    @ActivityScope
    abstract fun bindActivityCoroutineScope(activity: ChannelActivity): ActivityCoroutineScope

    @Binds
    @ActivityScope
    abstract fun bindChannelActivityNavigator(activity: ChannelActivity): Navigator<ChannelScreen>

    @FragmentScope
    @ContributesAndroidInjector(modules = [ChannelFragmentModule::class])
    abstract fun channelFragmentInjector(): ChannelFragment
}