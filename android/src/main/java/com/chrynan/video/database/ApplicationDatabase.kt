package com.chrynan.video.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chrynan.video.database.dao.DbServiceDao
import com.chrynan.video.database.model.DbService

@Database(
    version = 1,
    entities = [DbService::class],
    exportSchema = true
)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun serviceDao(): DbServiceDao

    object Migrations {


    }
}