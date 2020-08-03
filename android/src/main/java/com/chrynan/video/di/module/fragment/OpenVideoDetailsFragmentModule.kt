package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.video.*
import com.chrynan.video.ui.fragment.OpenVideoDetailsFragment
import com.chrynan.video.ui.view.OpenVideoDetailsView
import dagger.Binds
import dagger.Module

@Module
internal abstract class OpenVideoDetailsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindVideoDetailsView(fragment: OpenVideoDetailsFragment): OpenVideoDetailsView

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