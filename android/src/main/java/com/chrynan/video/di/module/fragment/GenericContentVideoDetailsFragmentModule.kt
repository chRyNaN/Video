package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.presentation.core.Navigator
import com.chrynan.video.presentation.core.View
import com.chrynan.video.presentation.navigator.GenericContentVideoDetailsScreen
import com.chrynan.video.presentation.state.GenericContentVideoDetailsIntent
import com.chrynan.video.presentation.state.GenericContentVideoDetailsState
import com.chrynan.video.ui.fragment.GenericContentVideoDetailsFragment
import dagger.Binds
import dagger.Module

@Module
internal abstract class GenericContentVideoDetailsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindGenericContentVideoDetailsView(fragment: GenericContentVideoDetailsFragment): View<GenericContentVideoDetailsIntent, GenericContentVideoDetailsState>

    @Binds
    @FragmentScope
    abstract fun bindGenericContentVideoDetailsNavigator(fragment: GenericContentVideoDetailsFragment): Navigator<GenericContentVideoDetailsScreen>
}