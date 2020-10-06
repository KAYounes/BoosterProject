package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.adapter.HeadlinesRecyclerViewAdapter
import com.example.newsappinkotlin.models.FullNewsModel
import com.example.newsappinkotlin.models.Source
import com.example.newsappinkotlin.viewmodel.headlinesViewModel
import kotlinx.android.synthetic.main.fragment_headlines.*

class HeadlinesFragment : Fragment() {
    lateinit var viewModel: headlinesViewModel
    lateinit var recyclerViewAdapter: HeadlinesRecyclerViewAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_headlines, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        newsFeedRecyclerView.adapter = HeadlinesRecyclerViewAdapter(list);
//        newsFeedRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)


//        val viewModel: headlinesViewModel= headlinesViewModel()
        recyclerViewAdapter = HeadlinesRecyclerViewAdapter(mutableListOf())
        linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        newsFeedRecyclerView.adapter = recyclerViewAdapter
//        newsFeedRecyclerView.layoutManager = linearLayoutManager

        viewModel = ViewModelProvider(this).get(headlinesViewModel::class.java)
        viewModel.headlinesMutableLiveDate.observe(viewLifecycleOwner,
        Observer { t -> createAdapter(t) })
        viewModel.getNewsList()
    }

    fun createAdapter(headliens: ArrayList<FullNewsModel>){
        recyclerViewAdapter = HeadlinesRecyclerViewAdapter(headliens)
        newsFeedRecyclerView.adapter = recyclerViewAdapter
        newsFeedRecyclerView.layoutManager = linearLayoutManager

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