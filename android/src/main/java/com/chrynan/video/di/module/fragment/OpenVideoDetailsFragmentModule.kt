package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.presentation.navigator.Navigator
import com.chrynan.video.presentation.navigator.OpenVideoDetailsScreen
import com.chrynan.video.presentation.state.OpenVideoDetailsIntent
import com.chrynan.video.presentation.state.OpenVideoDetailsState
import com.chrynan.video.presentation.view.View
import com.chrynan.video.ui.adapter.video.*
import com.chrynan.video.ui.fragment.OpenVideoDetailsFragment
import dagger.Binds
import dagger.Module

@Module
internal abstract class OpenVideoDetailsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindOpenVideoDetailsView(fragment: OpenVideoDetailsFragment): View<OpenVideoDetailsIntent, OpenVideoDetailsState>

    @Binds
    @FragmentScope
    abstract fun bindOpenVideoDetailsNavigator(fragment: OpenVideoDetailsFragment): Navigator<OpenVideoDetailsScreen>

    @Binds
    @FragmentScope
    abstract fun bindVideoRecommendationItemSelectedListener(fragment: OpenVideoDetailsFragment): VideoRecommendationAdapter.VideoRecommendationItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindVideoShowcaseItemSelectedListener(fragment: OpenVideoDetailsFragment): VideoShowcaseAdapter.VideoShowcaseItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindVideoActionSelectedListener(fragment: OpenVideoDetailsFragment): VideoInfoActionAdapter.VideoActionSelectedListener
}