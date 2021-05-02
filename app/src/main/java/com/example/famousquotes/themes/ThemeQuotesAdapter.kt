package com.example.famousquotes.themes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.famousquotes.R
import com.example.famousquotes.data.entities.CitataWithAuthor
import kotlinx.android.synthetic.main.quotes_item.view.*

class ThemeQuotesAdapter: RecyclerView.Adapter<ThemeQuotesAdapter.ThemeQuotesViewHolder>() {

    inner class ThemeQuotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModelCitata(citataModel: CitataWithAuthor, onFavIconClick: (view: View) -> Unit){
            itemView.tvQuotes.text = citataModel.citata.text
            itemView.tvAuthor.text = citataModel.author.authorName

            // Fav icon clicked
            itemView.favoriteIcon.setOnClickListener {
                if (citataModel.citata.isFavorite == 0 || citataModel.citata.isFavorite == null) {
                    it.setBackgroundResource(R.drawable.ic_favorite_marked)
                }else{
                    it.setBackgroundResource(R.drawable.ic_favorite_not_marked)
                }
            }

            // Copy icon clicked
            itemView.copyIcon.setOnClickListener {
                onCopyIconClick.invoke(itemView.copyIcon)
            }
        }
    }

    // fav icon clicked
    private var onFavIconClick: (view: View) -> Unit = {}
    fun setOnFavIconClickListener(onFavIconClick: (view: View) -> Unit) {
        this.onFavIconClick = onFavIconClick
    }

    // fav icon clicked
    private var onCopyIconClick: (view: View) -> Unit = {}
    fun setOnCopyIconClickListener(onCopyIconClick: (view: View) -> Unit) {
        this.onCopyIconClick = onCopyIconClick
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

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: ThemeQuotesViewHolder, position: Int) {
        holder.populateModelCitata(models[position], onFavIconClick)
    }

}