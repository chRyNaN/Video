package com.chrynan.video.di.component

import com.chrynan.video.di.module.*
import com.chrynan.video.di.module.ActivityModule
import com.chrynan.video.di.module.AppModule
import com.chrynan.video.di.module.DatabaseModule
import com.chrynan.video.di.module.GraphQLModule
import com.chrynan.video.di.module.MediaModule
import com.chrynan.video.di.module.ValidatorModule
import com.chrynan.video.di.module.WebModule
import com.chrynan.video.VideoApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ActivityModule::class,
        MediaModule::class,
        WebModule::class,
        GraphQLModule::class,
        RepositoryModule::class,
        DatabaseModule::class,
        ValidatorModule::class,
        AndroidSupportInjectionModule::class
    ]
)
interface AppComponent : AndroidInjector<VideoApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: VideoApplication): Builder

        fun build(): AppComponent
    }
}