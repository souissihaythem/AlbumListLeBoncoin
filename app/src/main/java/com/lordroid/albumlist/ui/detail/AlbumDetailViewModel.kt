package com.lordroid.albumlist.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lordroid.albumlist.data.entities.Album
import com.lordroid.albumlist.data.repository.AlbumRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AlbumDetailViewModel @Inject constructor(private val recipeRepository: AlbumRepository) :
    ViewModel() {

    private lateinit var album: LiveData<Album>

    fun init(albumId: Int) {
        album = recipeRepository.getAlbumDetail(albumId = albumId)
    }

    fun getRecipeObservable(): LiveData<Album> {
        return album
    }
}