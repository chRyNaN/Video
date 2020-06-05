package com.chrynan.video.di.module

import androidx.room.Room
import com.chrynan.video.database.ApplicationDatabase
import com.chrynan.video.di.qualifier.ApplicationContextQualifier
import com.chrynan.video.utils.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
internal abstract class DatabaseModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideApplicationDatabase(@ApplicationContextQualifier context: ApplicationContext): ApplicationDatabase =
            Room.databaseBuilder(context, ApplicationDatabase::class.java, "video")
                .build()

        @JvmStatic
        @Provides
        fun provideDbServiceDao(database: ApplicationDatabase) = database.serviceDao()
    }
}