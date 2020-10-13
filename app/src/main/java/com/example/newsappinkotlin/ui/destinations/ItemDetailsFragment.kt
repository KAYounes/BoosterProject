package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.newsappinkotlin.Database.DataModel
import com.example.newsappinkotlin.Database.ViewModel
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.models.FullNewsModel
import com.example.newsappinkotlin.viewmodel.headlinesViewModel
import kotlinx.android.synthetic.main.fragment_item_details.*
import kotlinx.android.synthetic.main.fragment_item_details.view.*

class ItemDetailsFragment : Fragment() {

    lateinit var newsViewModel: ViewModel
    private lateinit var viewModel: headlinesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     val view  = inflater.inflate(R.layout.fragment_item_details, container, false)

        viewModel = ViewModelProvider(this).get(headlinesViewModel::class.java)

        view.saveNews.setOnClickListener{
            insertDatatoDatabase()
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(headlinesViewModel::class.java)
        viewModel.getNews().observe(viewLifecycleOwner, Observer {  t -> bind(t) })
    }


    private fun insertDatatoDatabase() {

        val description = newsTitle.text.toString()
        val source = newsSource.text.toString()

        val news = DataModel(0, description, source)

        newsViewModel.saveNews(news)

        Toast.makeText(requireContext(), "sucessfully saved!", Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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