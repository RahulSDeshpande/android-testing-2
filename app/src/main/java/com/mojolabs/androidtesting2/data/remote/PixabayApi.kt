package com.mojolabs.androidtesting2.data.remote

import com.mojolabs.androidtesting2.BuildConfig
import com.mojolabs.androidtesting2.data.remote.responses.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("/api/")
    suspend fun searchImage(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = BuildConfig.PIXABAY_API_KEY
    ): Response<ImageResponse>
}
