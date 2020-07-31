package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.video.*
import com.chrynan.video.ui.fragment.VideoDetailsFragment
import com.chrynan.video.ui.view.VideoDetailsView
import dagger.Binds
import dagger.Module

@Module
internal abstract class VideoDetailsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindVideoDetailsView(fragment: VideoDetailsFragment): VideoDetailsView

    @Binds
    @FragmentScope
    abstract fun bindVideoRecommendationItemSelectedListener(fragment: VideoDetailsFragment): VideoRecommendationAdapter.VideoRecommendationItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindVideoShowcaseItemSelectedListener(fragment: VideoDetailsFragment): VideoShowcaseAdapter.VideoShowcaseItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindVideoActionSelectedListener(fragment: VideoDetailsFragment): VideoInfoActionAdapter.VideoActionSelectedListener
}