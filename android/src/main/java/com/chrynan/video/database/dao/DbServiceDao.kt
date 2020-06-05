package com.chrynan.video.database.dao

import androidx.room.*
import com.chrynan.common.model.core.UriString
import com.chrynan.video.database.model.DbService

@Dao
abstract class DbServiceDao {

    @Query("SELECT * FROM services WHERE provider_uri = :providerUri")
    abstract suspend fun getByProviderUri(providerUri: UriString): DbService?

    @Query("SELECT * FROM services")
    abstract suspend fun getAll(): List<DbService>

    @Query("SELECT COUNT(*) FROM services")
    abstract suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(value: DbService)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(values: List<DbService>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(value: DbService)

    @Delete
    abstract suspend fun delete(value: DbService)

    @Query("DELETE FROM services WHERE provider_uri = :providerUri")
    abstract suspend fun deleteByProviderUri(providerUri: UriString)

    @Query("DELETE FROM services")
    abstract suspend fun deleteAll()
}