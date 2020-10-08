package com.example.newsappinkotlin.models

import com.google.gson.annotations.SerializedName

data class FullNewsModel (
    @SerializedName("source") var headLineSource: Source,
    @SerializedName("author") var newsAuthor: String,
    @SerializedName("description") var newsDescription: String,
    @SerializedName("title") var headLineTitle: String,
    @SerializedName("urlToImage") var headLineThumbNail: String,
    @SerializedName("publishedAt") var headLinePublish: String,
    @SerializedName("content") var newContent: String
)

class Source(
    val id: String?,
    val name: String
)