package com.example.newsappinkotlin.adapter


import android.icu.util.Calendar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsappinkotlin.R
import com.example.newsappinkotlin.models.FullNewsModel
import kotlinx.android.synthetic.main.activity_news_card_view.view.*

class HeadlinesRecyclerViewAdapter(var headLines: MutableList<FullNewsModel>?): RecyclerView.Adapter<HeadlinesRecyclerViewAdapter.HeadLineHolder>(){

    class HeadLineHolder(headlineCard: View): RecyclerView.ViewHolder(headlineCard){

        fun onBind(headline: FullNewsModel){
            itemView.HeadLineTitle.text =headline.headLineTitle
            itemView.HeadLineSource.text = "${headline.headLineSource.name} • ${getHoursAgo(headline.headLinePublish)}"

            Glide.with(itemView)
                .load(headline.headLineThumbNail)
                .into(itemView.HeadLineThumbNailCardView.HeadLineThumbNail)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadLineHolder =
        HeadLineHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.activity_news_card_view,
                parent,
                false
            )
        )

    override fun getItemCount() = headLines!!.size

    override fun onBindViewHolder(holder: HeadLineHolder, position: Int) {
        var headline = headLines!![position]
        holder.onBind(headline)
    }


}

private fun getHoursAgo(published: String): String{
    var publishedHours = published.split("T","Z")[1].split(":")[0].toInt()
    var publishedDay = published.split("T","Z")[0].split("-")[2].toInt()

    var today = Calendar.getInstance();
    var currentDay = today.time.toString().split(" ")[2].toInt()
    var currentHours = today.time.toString().split(" ")[3].split(":")[0].toInt()

    return (currentHours - (publishedHours - 24*(currentDay - publishedDay) )).toString()
}
