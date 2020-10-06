package com.example.newsappinkotlin.network

import com.example.newsappinkotlin.models.FullNewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient2 {
    val baseUrl = "https://newsapi.org/"
    val service: ApiCalls

    init{
        val retrofit = Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()

        service = retrofit.create(ApiCalls::class.java)
    }

        fun callNewsApi(onSuccess: (moviesList: ArrayList<FullNewsModel>) -> Unit, onError: () -> Unit){

            service.getNews().enqueue(object : Callback<NewsResponse> {
                override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                    println("Error source: ApiClient_2 onFailure fun $t")
//                    onError.invoke()
                }

                override fun onResponse(
                    call: Call<NewsResponse>,
                    response: Response<NewsResponse>
                ) {
                    if(response.body() != null)
                    {
                        println("Success source: ApiClient_2 if block")
                        println(response.body()!!.articles)
//                        onSuccess.invoke(response.body()!!.articles)

                    }
                    else
                    {
                        println("Error source: ApiClient_2 else block")
//                        onError.invoke()
                    }
                }
            })

        }


}