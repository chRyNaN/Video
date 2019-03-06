package com.chrynan.video.di.module.fragment

import com.chrynan.aaaah.ManagerRecyclerViewAdapter
import com.chrynan.aaaah.UniqueAdapterItem
import com.chrynan.video.ui.adapter.QueueItemAdapter
import com.chrynan.video.ui.adapter.listener.QueueOptionsListener
import com.chrynan.video.ui.fragment.QueueFragment
import com.chrynan.video.ui.view.QueueView
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
            ManagerRecyclerViewAdapter<UniqueAdapterItem>(adapters = setOf(queueItemAdapter))
    }

    @Binds
    abstract fun bindQueueView(fragment: QueueFragment): QueueView

    @Binds
    abstract fun bindQueueOptionsListener(fragment: QueueFragment): QueueOptionsListener
}