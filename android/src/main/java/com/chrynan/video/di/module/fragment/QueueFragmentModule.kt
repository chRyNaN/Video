package com.chrynan.video.di.module.fragment

import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.presentation.view.QueueView
import com.chrynan.presentation.viewmodel.UniqueListItem
import com.chrynan.video.ui.adapter.QueueItemAdapter
import com.chrynan.video.ui.adapter.listener.QueueOptionsListener
import com.chrynan.video.ui.fragment.QueueFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class QueueFragmentModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideAdapter(queueItemAdapter: QueueItemAdapter) =
            ManagerRecyclerViewAdapter<UniqueListItem>(adapters = setOf(queueItemAdapter))
    }

    @Binds
    abstract fun bindQueueView(fragment: QueueFragment): QueueView

    @Binds
    abstract fun bindQueueOptionsListener(fragment: QueueFragment): QueueOptionsListener
}