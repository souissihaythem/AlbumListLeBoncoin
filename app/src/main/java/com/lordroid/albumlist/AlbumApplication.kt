package com.lordroid.albumlist

import com.lordroid.albumlist.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class AlbumApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}