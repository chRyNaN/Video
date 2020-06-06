package com.chrynan.video.di.component

import com.chrynan.video.di.module.ActivityModule
import com.chrynan.video.di.module.AppModule
import com.chrynan.video.di.module.DatabaseModule
import com.chrynan.video.di.module.MediaModule
import com.chrynan.video.di.module.RepositoryModule
import com.chrynan.video.di.module.ValidatorModule
import com.chrynan.video.di.module.WebModule
import com.chrynan.video.ui.VideoApplication
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