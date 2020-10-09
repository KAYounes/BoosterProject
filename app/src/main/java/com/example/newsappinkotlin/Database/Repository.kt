package com.example.newsappinkotlin.Database

import androidx.lifecycle.LiveData

class Repository(private val newsDao: NewsDao) {
val readAllData: LiveData<List<DataModel>> = newsDao.readAllData()
    fun addNews(news : DataModel){
        newsDao.addSavedNews(news)
    }
}