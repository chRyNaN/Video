package com.chrynan.video.di.module

import androidx.room.Room
import com.chrynan.video.database.ApplicationDatabase
import com.chrynan.video.di.qualifier.ApplicationContextQualifier
import com.chrynan.video.utils.ApplicationContext
import dagger.Module
import dagger.Provides
import net.sqlcipher.database.SupportFactory

@Module
internal abstract class DatabaseModule {

    @Module
    companion object {

        @JvmStatic
        @Provides
        fun provideEncryptionDatabaseFactory() =
            SupportFactory("".toByteArray()) // TODO update passphrase

        @JvmStatic
        @Provides
        fun provideApplicationDatabase(
            @ApplicationContextQualifier context: ApplicationContext,
            encryptionDatabaseFactory: SupportFactory
        ): ApplicationDatabase =
            Room.databaseBuilder(context, ApplicationDatabase::class.java, "video")
                .openHelperFactory(encryptionDatabaseFactory)
                .build()

        @JvmStatic
        @Provides
        fun provideDbServiceDao(database: ApplicationDatabase) = database.serviceDao()
    }
}