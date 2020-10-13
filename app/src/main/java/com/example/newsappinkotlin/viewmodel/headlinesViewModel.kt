package com.example.newsappinkotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsappinkotlin.models.FullNewsModel
import com.example.newsappinkotlin.network.ApiCalls
import com.example.newsappinkotlin.network.ApiClient
import com.example.newsappinkotlin.network.NewsResponse
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class headlinesViewModel: ViewModel() {

    private val headlinesMutableLiveDate : MutableLiveData<ArrayList<FullNewsModel>> = MutableLiveData()
    private val news : MutableLiveData<FullNewsModel> = MutableLiveData()

    val services: ApiCalls? = ApiClient.getClient()?.create(ApiCalls::class.java)

    fun callGetNews(currentPage: Int) {
        services?.getNews(currentPage = currentPage)!!.enqueue(object : retrofit2.Callback<NewsResponse?>{
            override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
            }

            override fun onResponse(call: Call<NewsResponse?>, response: Response<NewsResponse?>) {
                if( response.body() == null){
                }
                headlinesMutableLiveDate.value = response.body()?.articles
            }

        })
    }

    fun getHeadlines(): LiveData<ArrayList<FullNewsModel>>{
        return headlinesMutableLiveDate
    }

    fun updateNews(card: FullNewsModel){
        news.value = card
    }

    fun getNews(): LiveData<FullNewsModel>{
        return news
    }

    fun getNewsList(currentPage: Int){
        callGetNews(currentPage)
    }
}




