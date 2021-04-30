package com.example.famousquotes.themes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.famousquotes.R
import com.example.famousquotes.data.entities.CitataWithAuthor
import com.example.famousquotes.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.quotes_item.view.*

class ThemeQuotesAdapter: RecyclerView.Adapter<ThemeQuotesAdapter.ThemeQuotesViewHolder>() {

    inner class ThemeQuotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModelCitata(citataModel: CitataWithAuthor){
            itemView.tvQuotes.text = citataModel.citata.text
            itemView.tvAuthor.text = citataModel.author.authorName
            itemView.favoriteIcon.setOnClickListener {
                onFavIconClick.invoke(it)
            }
        }
    }

    private var onFavIconClick: (view: View) -> Unit = {}
    fun setOnFavIconClickListener(onFavIconClick: (view: View) -> Unit) {
        this.onFavIconClick = onFavIconClick
    }

    var models: List<CitataWithAuthor> = listOf()
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
        holder.populateModelCitata(models[position])
    }

}