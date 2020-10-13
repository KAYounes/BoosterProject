package com.example.newsappinkotlin.ui.destinations

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.adapter.CardClickListener
import com.example.newsappinkotlin.adapter.HeadlinesRecyclerViewAdapter
import com.example.newsappinkotlin.models.FullNewsModel
import com.example.newsappinkotlin.network.ApiClient
import com.example.newsappinkotlin.viewmodel.DetailsViewModel
import com.example.newsappinkotlin.viewmodel.headlinesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_headlines.*

class HeadlinesFragment : Fragment(), CardClickListener {
    var currentPage = 1
    lateinit var viewModel: headlinesViewModel
    lateinit var recyclerViewAdapter: HeadlinesRecyclerViewAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_headlines, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("On Functions: onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        recyclerViewAdapter = HeadlinesRecyclerViewAdapter(mutableListOf(), this)
        linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        newsFeedRecyclerView.adapter = recyclerViewAdapter
        newsFeedRecyclerView.layoutManager = linearLayoutManager
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        println("On Functions: onActivityCreated")
        super.onActivityCreated(savedInstanceState)
        currentPage = 1
        viewModel = ViewModelProvider(requireActivity()).get(headlinesViewModel::class.java)
        viewModel.getHeadlines().observe(viewLifecycleOwner,
            Observer { t -> fetchPage(t) })
        viewModel.getNewsList(currentPage)

    }

    fun fetchPage(headlines: ArrayList<FullNewsModel>){
        recyclerViewAdapter.nextPage(headlines)
        attachOnScrollListener()
    }

    fun attachOnScrollListener(){
        newsFeedRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItems = linearLayoutManager.itemCount
                val visibleItemsCount = linearLayoutManager.childCount
                val firstVisibleItem = linearLayoutManager.findLastVisibleItemPosition()

                if(firstVisibleItem + visibleItemsCount >= totalItems/2) {
                    newsFeedRecyclerView.removeOnScrollListener(this)
                    if(viewModel.getHeadlines().value?.size != 0){
                        currentPage++
                        viewModel.getNewsList(currentPage)
                    }

                }
            }
        })
    }

    override fun onCardClick(card: FullNewsModel, position: Int) {
        findNavController().navigate(R.id.action_headlinesFragment_to_itemDetailsFragment)
        viewModel.updateNews(card)
    }


//    var list = arrayListOf<FullNewsModel>(
//        FullNewsModel(Source(null,"mySource"),"myAuthor", "myDescription", "myHeadline","https://images.unsplash.com/photo-1584940120743-8981ca35b012?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "2020-10-05T01:25:58Z","myContent"),
//        FullNewsModel(Source(null,"mySource2"),"myAuthor2", "myDescription2", "myHeadline2","https://images.unsplash.com/photo-1584940120743-8981ca35b012?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "2020-10-05T02:25:58Z","myContent2"),
//        FullNewsModel(Source(null,"mySource3"),"myAuthor3", "myDescription3", "myHeadline3","https://images.unsplash.com/photo-1584940120743-8981ca35b012?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "2020-10-05T03:25:58Z","myContent3"),
//        FullNewsModel(Source(null,"mySource4"),"myAuthor4", "myDescription4", "myHeadline4","https://images.unsplash.com/photo-1584940120743-8981ca35b012?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "2020-10-05T04:25:58Z","myContent4"),
//        FullNewsModel(Source(null,"mySource5"),"myAuthor5", "myDescription5", "myHeadline5","https://images.unsplash.com/photo-1584940120743-8981ca35b012?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "2020-10-05T05:25:58Z","myContent5"),
//        FullNewsModel(Source(null,"mySource"),"myAuthor", "myDescription", "myHeadline","https://images.unsplash.com/photo-1584940120743-8981ca35b012?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "2020-10-05T01:25:58Z","myContent"),
//        FullNewsModel(Source(null,"mySource2"),"myAuthor2", "myDescription2", "myHeadline2","https://images.unsplash.com/photo-1584940120743-8981ca35b012?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "2020-10-05T02:25:58Z","myContent2"),
//        FullNewsModel(Source(null,"mySource3"),"myAuthor3", "myDescription3", "myHeadline3","https://images.unsplash.com/photo-1584940120743-8981ca35b012?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "2020-10-05T03:25:58Z","myContent3"),
//        FullNewsModel(Source(null,"mySource4"),"myAuthor4", "myDescription4", "myHeadline4","https://images.unsplash.com/photo-1584940120743-8981ca35b012?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "2020-10-05T04:25:58Z","myContent4"),
//        FullNewsModel(Source(null,"mySource5"),"myAuthor5", "myDescription5", "myHeadline5","https://images.unsplash.com/photo-1584940120743-8981ca35b012?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=500&q=60", "2020-10-05T05:25:58Z","myContent5")
//    )

}