package com.lordroid.albumlist.data.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.lordroid.albumlist.data.entities.Album
import com.lordroid.albumlist.data.local.dao.AlbumDao
import com.lordroid.albumlist.data.remote.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AlbumRepository @Inject constructor(
    private val AlbumDao: AlbumDao,
    private val apiService: ApiService
) {

    private val executor = Executors.newSingleThreadExecutor()

    fun getAllAlbums(page: Int): LiveData<List<Album>> {
        if (page == 1)
            refreshAlbumList()

        return AlbumDao.getAllAlbums(page = page)
    }

    fun getAlbumDetail(albumId: Int): LiveData<Album> {

        return AlbumDao.getAlbum(albumId = albumId)
    }

    @SuppressLint("CheckResult")
    private fun refreshAlbumList() {

        apiService.getAllAlbums()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ albums -> albums.forEach { insert(it) } },
                {

                })
    }

    private fun insert(album: Album) {
        executor.execute {
            AlbumDao.insert(album)
        }
    }
}