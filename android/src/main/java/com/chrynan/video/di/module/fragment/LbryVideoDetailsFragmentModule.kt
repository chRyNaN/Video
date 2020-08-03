package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.fragment.LbryVideoDetailsFragment
import com.chrynan.video.ui.view.LbryVideoDetailsView
import dagger.Binds
import dagger.Module

@Module
internal abstract class LbryVideoDetailsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindLbryVideoDetailsView(fragment: LbryVideoDetailsFragment): LbryVideoDetailsView
}