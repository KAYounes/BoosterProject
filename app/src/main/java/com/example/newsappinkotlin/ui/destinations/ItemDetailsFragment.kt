package com.example.newsappinkotlin.ui.destinations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.newsappinkotlin.Database.DataModel
import com.example.newsappinkotlin.Database.ViewModel
import com.example.newsappinkotlin.R
import kotlinx.android.synthetic.main.fragment_item_details.*
import kotlinx.android.synthetic.main.fragment_item_details.view.*

class ItemDetailsFragment : Fragment() {

    private lateinit var newsViewModel: ViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
     val view  = inflater.inflate(R.layout.fragment_item_details, container, false)

        newsViewModel = ViewModelProvider(this).get(ViewModel::class.java)

        view.button6.setOnClickListener{
            insertDatatoDatabase()
        }

        return view
    }


    private fun insertDatatoDatabase() {

        val description = detailTitle.text.toString()
        val source = detailSource.text.toString()

        val news = DataModel(0, description, source)

        newsViewModel.saveNews(news)

        Toast.makeText(requireContext(), "sucessfully saved!", Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(headlinesViewModel::class.java)
        viewModel.getNews().observe(viewLifecycleOwner,
            Observer { t -> bind(t) })
    }

}