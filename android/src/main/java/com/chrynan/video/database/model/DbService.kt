package com.chrynan.video.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chrynan.common.model.core.UriString

@Entity(tableName = "services")
data class DbService(
    @PrimaryKey @ColumnInfo(name = "provider_uri") val providerUri: UriString,
    @ColumnInfo(name = "api_version") val apiVersion: String,
    @ColumnInfo(name = "token") val token: String? = null
)