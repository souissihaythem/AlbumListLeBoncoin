package com.lordroid.albumlist.di


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lordroid.albumlist.ui.albums.AlbumListViewModel
import com.lordroid.albumlist.ui.detail.AlbumDetailViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AlbumListViewModel::class)
    abstract fun bindAlbumListViewModel(albumListViewModel: AlbumListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AlbumDetailViewModel::class)
    abstract fun bindAlbumDetailViewModel(albumDetailViewModel: AlbumDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}