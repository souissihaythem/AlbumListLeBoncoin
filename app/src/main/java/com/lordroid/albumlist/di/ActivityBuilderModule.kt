package com.lordroid.albumlist.di

import com.lordroid.albumlist.ui.albums.AlbumListActivity
import com.lordroid.albumlist.ui.detail.AlbumDetailActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {
    @ContributesAndroidInjector
    abstract fun contributeAlbumListActivity(): AlbumListActivity

    @ContributesAndroidInjector
    abstract fun contributeAlbumDetailActivity(): AlbumDetailActivity
}