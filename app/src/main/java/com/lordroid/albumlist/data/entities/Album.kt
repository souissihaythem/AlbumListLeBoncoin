package com.lordroid.albumlist.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album_table")
data class Album(
    @ColumnInfo(name = "albumId") val albumId: Int,
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "thumbnailUrl") val thumbnailUrl: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url") val url: String
)