package com.example.newsappinkotlin.ui.destinations

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.models.FullNewsModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController =  Navigation.findNavController(this, R.id.nav_host_fragment_container)

        bottom_nav_view.setupWithNavController(navController)

        syncNavBar()



    }

    private fun syncNavBar() {
//        NavigationUI.setupWithNavController(bottom_nav_view, navController)

        navController.addOnDestinationChangedListener { _, dest, _ ->
            if (dest.id == R.id.itemDetailsFragment || dest.id == R.id.splashFragment){
                bottom_nav_view.visibility = View.GONE
            }
            else{
                bottom_nav_view.visibility = View.VISIBLE
            }

        }
    }
}