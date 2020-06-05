package com.chrynan.video.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chrynan.video.database.dao.DbServiceProviderDao
import com.chrynan.video.database.model.DbServiceProvider

@Database(
    version = 1,
    entities = [DbServiceProvider::class],
    exportSchema = true
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun serviceDao(): DbServiceProviderDao

    object Migrations {


    }
}