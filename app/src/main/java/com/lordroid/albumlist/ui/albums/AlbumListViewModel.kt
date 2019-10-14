package com.lordroid.albumlist.ui.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lordroid.albumlist.data.entities.Album
import com.lordroid.albumlist.data.repository.AlbumRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class AlbumListViewModel @Inject constructor(private val recipeRepository: AlbumRepository) :
    ViewModel() {

    private lateinit var recipeList: LiveData<List<Album>>

    fun init(page: Int) {
        recipeList = recipeRepository.getAllAlbums(page = page)
    }

    fun getAlbumsListObservable(): LiveData<List<Album>> {
        return recipeList
    }
}