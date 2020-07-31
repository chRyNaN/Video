package com.chrynan.video.di.module.fragment

import com.chrynan.video.ui.view.SearchView
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.SearchTagItemAdapter
import com.chrynan.video.ui.adapter.channel.ChannelListItemAdapter
import com.chrynan.video.ui.adapter.video.VideoRecommendationAdapter
import com.chrynan.video.ui.fragment.SearchFragment
import dagger.Binds
import dagger.Module

@Module
internal abstract class SearchFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindSearchView(fragment: SearchFragment): SearchView

    @Binds
    @FragmentScope
    abstract fun bindVideoRecommendationItemSelectedListener(fragment: SearchFragment): VideoRecommendationAdapter.VideoRecommendationItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindSearchTagItemSelectedListener(fragment: SearchFragment): SearchTagItemAdapter.SearchTagItemSelectedListener

    @Binds
    @FragmentScope
    abstract fun bindChannelListItemSelectedListener(fragment: SearchFragment): ChannelListItemAdapter.ChannelListItemSelectedListener
}