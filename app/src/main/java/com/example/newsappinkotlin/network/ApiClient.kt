package com.example.newsappinkotlin.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val baseURL = "https://newsapi.org/"
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit?{
        if(retrofit == null){
            retrofit = Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build()
        }
        return retrofit
    }




}