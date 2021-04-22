package com.example.famousquotes.themes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.famousquotes.R
import com.example.famousquotes.data.entityes.Citata
import kotlinx.android.synthetic.main.quotes_item.view.*

class ThemeQuotesAdapter: RecyclerView.Adapter<ThemeQuotesAdapter.ThemeQuotesViewHolder>() {

    inner class ThemeQuotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModel(quotesModel: Citata){
            itemView.tvQuotes.text = quotesModel.text
            itemView.tvAuthor.text = quotesModel.authorId.toString()
        }
    }

    var models: List<Citata> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeQuotesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.quotes_item, parent, false)
        return ThemeQuotesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: ThemeQuotesViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

}