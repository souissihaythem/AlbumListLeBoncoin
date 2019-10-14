package com.lordroid.albumlist.data.local


import androidx.room.Database
import androidx.room.RoomDatabase
import com.lordroid.albumlist.data.entities.Album
import com.lordroid.albumlist.data.local.dao.AlbumDao


@Database(entities = [Album::class], version = 1, exportSchema = false)
abstract class AlbumDatabae : RoomDatabase() {
    abstract fun albumsDao(): AlbumDao
}