package com.example.newsappinkotlin.ui.destinations

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.models.FullNewsModel
import com.example.newsappinkotlin.viewmodel.DetailsViewModel
import com.example.newsappinkotlin.viewmodel.headlinesViewModel
import kotlinx.android.synthetic.main.fragment_item_details.*

class ItemDetailsFragment : Fragment() {
        lateinit var viewModel: headlinesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?{

        return inflater.inflate(R.layout.fragment_item_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(headlinesViewModel::class.java)
        viewModel.getNews().observe(viewLifecycleOwner,
            Observer { t -> bind(t) })
    }



    fun bind(card: FullNewsModel){
        Glide.with(this).load(card.headLineThumbNail).error(R.drawable.news).into(newsThumbnail)
        newsTitle.text = card.headLineTitle
        newslPublishTime.text = card.headLinePublish
        newsSource.text = card.headLineSource.name
        newsDescription.text = card.newsDescription
        newsContent.text = card.newsContent
    }
}

//        newsTitle.text =arguments?.getString("title")
//        newslPublishTime.text =arguments?.getString("publish")
//        newsSource.text =arguments?.getString("sourceName")
//        newsDescription.text =arguments?.getString("description")
//        newsContent.text =arguments?.getString("content")
//        Glide.with(this).load(arguments?.getString("image")).error(R.drawable.news).into(newsThumbnail)