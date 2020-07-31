package com.chrynan.video.di.module.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.chrynan.video.di.qualifier.ActivityContextQualifier
import com.chrynan.video.ui.view.QueueView
import com.chrynan.video.di.scope.FragmentScope
import com.chrynan.video.ui.adapter.QueueItemAdapter
import com.chrynan.video.ui.adapter.core.RecyclerViewAdapter
import com.chrynan.video.ui.adapter.listener.QueueOptionsListener
import com.chrynan.video.ui.adapter.position.LinearLayoutPositionManager
import com.chrynan.video.ui.fragment.QueueFragment
import com.chrynan.video.utils.ActivityContext
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
        fun provideLayoutManager(@ActivityContextQualifier context: ActivityContext) =
            LinearLayoutManager(context)

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideAdapter(queueItemAdapter: QueueItemAdapter, layoutManager: LinearLayoutManager) =
            RecyclerViewAdapter(
                adapters = setOf(queueItemAdapter),
                positionManager = LinearLayoutPositionManager(layoutManager)
            )
    }

    @Binds
    @FragmentScope
    abstract fun bindQueueView(fragment: QueueFragment): QueueView

    @Binds
    @FragmentScope
    abstract fun bindQueueOptionsListener(fragment: QueueFragment): QueueOptionsListener
}