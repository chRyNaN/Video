package com.chrynan.video.di.module.fragment

import com.chrynan.presentation.view.UserContentView
import com.chrynan.video.ui.fragment.UserContentFragment
import dagger.Binds
import dagger.Module

@Module
internal abstract class UserContentFragmentModule {

    @Binds
    abstract fun bindUserContentView(fragment: UserContentFragment): UserContentView
}