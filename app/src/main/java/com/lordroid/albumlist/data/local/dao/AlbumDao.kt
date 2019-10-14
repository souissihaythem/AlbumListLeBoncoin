package com.lordroid.albumlist.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lordroid.albumlist.data.entities.Album
import com.lordroid.albumlist.utils.Constants


@Dao
interface AlbumDao {

    @Query("SELECT * FROM album_table LIMIT (:page * ${Constants.pages}) ")
    fun getAllAlbums( page: Int): LiveData<List<Album>>

    @Query(value = "SELECT * FROM album_table WHERE id = :albumId")
    fun getAlbum(albumId: Int): LiveData<Album>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(album: Album)
}