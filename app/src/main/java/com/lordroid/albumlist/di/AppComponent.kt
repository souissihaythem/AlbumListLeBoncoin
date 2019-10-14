package com.lordroid.albumlist.di

import android.app.Application
import com.lordroid.albumlist.AlbumApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuilderModule::class, ViewModelModule::class, AppModule::class])
interface AppComponent : AndroidInjector<AlbumApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}