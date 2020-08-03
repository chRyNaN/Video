package com.chrynan.video.di.module.fragment

import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.fragment.GenericContentVideoDetailsFragment
import com.chrynan.video.ui.view.GenericContentVideoDetailsView
import dagger.Binds
import dagger.Module

@Module
internal abstract class GenericContentVideoDetailsFragmentModule {

    @Binds
    @FragmentScope
    abstract fun bindContentVideoDetailsView(fragment: GenericContentVideoDetailsFragment): GenericContentVideoDetailsView
}