package com.chrynan.video.di.module.fragment

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.presentation.view.QueueView
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.QueueItemAdapter
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.listener.QueueOptionsListener
import com.chrynan.video.ui.fragment.QueueFragment
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
internal abstract class QueueFragmentModule {

    @Module
    companion object {

        @Provides
        @JvmStatic
        @FragmentScope
        fun provideLayoutManager(context: Context) = LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideAdapter(queueItemAdapter: QueueItemAdapter, layoutManager: LinearLayoutManager) =
            RecyclerViewAdapter(
                adapters = setOf(queueItemAdapter),
                layoutManager = layoutManager
            )
    }

    @Binds
    @FragmentScope
    abstract fun bindQueueView(fragment: QueueFragment): QueueView

    @Binds
    @FragmentScope
    abstract fun bindQueueOptionsListener(fragment: QueueFragment): QueueOptionsListener
}