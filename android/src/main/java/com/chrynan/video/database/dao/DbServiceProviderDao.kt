package com.chrynan.video.database.dao

import androidx.room.*
import com.chrynan.common.model.core.UriString
import com.chrynan.video.database.model.DbServiceProvider

@Dao
abstract class DbServiceProviderDao {

    @Query("SELECT * FROM services WHERE provider_uri = :providerUri")
    abstract suspend fun getByProviderUri(providerUri: UriString): DbServiceProvider?

    @Query("SELECT * FROM services")
    abstract suspend fun getAll(): List<DbServiceProvider>

    @Query("SELECT COUNT(*) FROM services")
    abstract suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(value: DbServiceProvider)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(values: List<DbServiceProvider>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(value: DbServiceProvider)

    @Delete
    abstract suspend fun delete(value: DbServiceProvider)

    @Query("DELETE FROM services WHERE provider_uri = :providerUri")
    abstract suspend fun deleteByProviderUri(providerUri: UriString)

    @Query("DELETE FROM services")
    abstract suspend fun deleteAll()
}