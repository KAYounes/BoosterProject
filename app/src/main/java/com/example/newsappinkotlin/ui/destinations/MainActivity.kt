package com.example.newsappinkotlin.ui.destinations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.models.FullNewsModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_nav_view.setupWithNavController(findNavController(R.id.nav_host_fragment_container))

//        ApiClient2.callNewsApi(::response, ::fail)
    }

    private fun fail() {

    }

    private fun response(arrayList: ArrayList<FullNewsModel>) {

    }
}