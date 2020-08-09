package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.presentation.navigator.LbryVideoDetailsScreen
import com.chrynan.video.presentation.navigator.Navigator
import com.chrynan.video.presentation.state.LbryVideoDetailsIntent
import com.chrynan.video.presentation.state.LbryVideoDetailsState
import com.chrynan.video.presentation.view.View
import com.chrynan.video.ui.fragment.LbryVideoDetailsFragment
import dagger.Binds
import dagger.Module

@Module
internal abstract class LbryVideoDetailsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindLbryVideoDetailsView(fragment: LbryVideoDetailsFragment): View<LbryVideoDetailsIntent, LbryVideoDetailsState>

    @Binds
    @FragmentScope
    abstract fun bindLbryVideoDetailsNavigator(fragment: LbryVideoDetailsFragment): Navigator<LbryVideoDetailsScreen>
}