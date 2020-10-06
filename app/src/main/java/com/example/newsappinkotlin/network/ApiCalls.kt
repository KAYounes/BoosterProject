package com.example.newsappinkotlin.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCalls {
    @GET("v2/sources")

    fun getNews(@Query("apiKey") apiKey: String = "cac7619eebd84f24a1e30ace5c13708b", @Query("country") country: String = "us"): Call<NewsResponse>
}