package com.example.newsappinkotlin.network

import com.example.newsappinkotlin.models.FullNewsModel
import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @SerializedName("articles") val articles: ArrayList<FullNewsModel>
)