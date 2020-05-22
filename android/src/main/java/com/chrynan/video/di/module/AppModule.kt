package com.chrynan.video.di.module

import android.content.Context
import coil.ImageLoader
import com.chrynan.common.coroutine.CoroutineDispatchers
import com.chrynan.video.coroutine.AndroidCoroutineDispatchers
import com.chrynan.video.ui.VideoApplication
import com.chrynan.video.utils.ApplicationContext
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.noties.markwon.Markwon
import io.noties.markwon.ext.strikethrough.StrikethroughPlugin
import io.noties.markwon.ext.tables.TablePlugin
import io.noties.markwon.ext.tasklist.TaskListPlugin
import io.noties.markwon.html.HtmlPlugin
import io.noties.markwon.image.coil.CoilImagesPlugin
import io.noties.markwon.linkify.LinkifyPlugin
import javax.inject.Singleton

@Module
internal abstract class AppModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        @Singleton
        fun provideMarkWon(
            context: ApplicationContext,
            imageLoader: ImageLoader
        ) = Markwon.builder(context)
            .usePlugin(
                CoilImagesPlugin.create(
                    context,
                    imageLoader
                )
            )
            .usePlugin(HtmlPlugin.create())
            .usePlugin(StrikethroughPlugin.create())
            .usePlugin(TablePlugin.create(context))
            .usePlugin(TaskListPlugin.create(context))
            .usePlugin(LinkifyPlugin.create())
            .build() // TODO Add Syntax Highlighting Plugin
    }

    @Binds
    @Singleton
    abstract fun bindAppContext(application: VideoApplication): ApplicationContext

    @Binds
    @Singleton
    abstract fun bindCoroutineDispatchers(dispatchers: AndroidCoroutineDispatchers): CoroutineDispatchers
}