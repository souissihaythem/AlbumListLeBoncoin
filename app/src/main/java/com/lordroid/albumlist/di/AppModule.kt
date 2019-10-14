package com.lordroid.albumlist.di

import android.app.Application
import androidx.room.Room
import com.lordroid.albumlist.data.local.AlbumDatabae
import com.lordroid.albumlist.data.local.dao.AlbumDao
import com.lordroid.albumlist.data.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    @Singleton
    fun provideClient(): Retrofit {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okhttp = OkHttpClient.Builder()
        okhttp.addInterceptor(httpLoggingInterceptor)

        return Retrofit.Builder().baseUrl(ApiService.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okhttp.build())
            .build()
    }

    @Provides
    @Singleton
    fun provideService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDb(application: Application): AlbumDatabae {
        return Room.databaseBuilder(
            application.applicationContext, AlbumDatabae::class.java,
            "albums_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAlbumDao(db: AlbumDatabae): AlbumDao {
        return db.albumsDao()
    }
}