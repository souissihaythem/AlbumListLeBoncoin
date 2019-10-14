package com.lordroid.albumlist.data.remote

import com.lordroid.albumlist.data.entities.Album
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers


interface ApiService {

    companion object {
        const val ENDPOINT = "https://static.leboncoin.fr"
    }

    @Headers("Content-Type: application/json", "Accept: application/json")
    @GET("/img/shared/technical-test.json")
    fun getAllAlbums(): Single<List<Album>>

}