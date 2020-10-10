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

class DetailsViewModel: ViewModel() {

    private val detailsMutableLiveDate : MutableLiveData<FullNewsModel> = MutableLiveData()

    val services: ApiCalls? = ApiClient.getClient()?.create(ApiCalls::class.java)

    fun getCard(card: FullNewsModel) {
        detailsMutableLiveDate.value = card
    }

    fun getNews(): LiveData<FullNewsModel>{
        return detailsMutableLiveDate
    }


}




