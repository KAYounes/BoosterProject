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

class HeadlinesRecyclerViewAdapter(var headLinesList: MutableList<FullNewsModel>?, private var clickListener: CardClickListener): RecyclerView.Adapter<HeadlinesRecyclerViewAdapter.HeadLineHolder>(){

    class HeadLineHolder(headlineCard: View): RecyclerView.ViewHolder(headlineCard){

        fun onBind(headline: FullNewsModel, action:CardClickListener){
            itemView.HeadLineTitle.text = headline.headLineTitle.split(" - ")[0]
            itemView.HeadLineSource.text = "${getSource(headline.headLineSource?.name)} • ${getHoursAgo(headline.headLinePublish)}h"
            Glide.with(itemView)
                .load(headline.headLineThumbNail)
                .error(R.drawable.news)
                .into(itemView.HeadLineThumbNail)

            itemView.setOnClickListener{
                action.onCardClick(headline, adapterPosition)
            }

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

    override fun getItemCount() = headLinesList!!.size

    override fun onBindViewHolder(holder: HeadLineHolder, position: Int) {
        var headline = headLinesList!![position]
        holder.onBind(headline,clickListener)
    }

    fun nextPage(headlines: ArrayList<FullNewsModel>){
        this.headLinesList?.addAll(headlines)
        notifyItemRangeChanged(this.headLinesList!!.size, (headLinesList!!.size) -1)
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

private  fun getSource(sourceName: String): String{
    var split = sourceName.split(".")
    if("Www" in split[0]){
        return split[1]
    }
    return split[0]
}

interface CardClickListener{
    fun onCardClick(card: FullNewsModel, position: Int)
}
