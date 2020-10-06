package com.example.newsappinkotlin.viewmodel

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

    var headlinesMutableLiveDate : MutableLiveData<ArrayList<FullNewsModel>> = MutableLiveData()

    val services: ApiCalls? = ApiClient.getClient()?.create(ApiCalls::class.java)

    fun callGetNews() {
        services?.getNews()!!.enqueue(object : retrofit2.Callback<NewsResponse?>{
            override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
                println("Error source: ViewModel")
            }

            override fun onResponse(call: Call<NewsResponse?>, response: Response<NewsResponse?>) {
                println("success from callGetNews ${response.body()?.articles}")
                headlinesMutableLiveDate.value = response.body()?.articles
            }

        })
    }

    fun getNewsList(){
        println("calling callGetNews")
        callGetNews()
    }
}




