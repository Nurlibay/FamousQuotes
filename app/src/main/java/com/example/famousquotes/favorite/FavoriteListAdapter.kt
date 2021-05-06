package com.example.famousquotes.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.famousquotes.R
import com.example.famousquotes.data.entities.Citata
import com.example.famousquotes.data.entities.CitataWithAuthor
import kotlinx.android.synthetic.main.quotes_item.view.*

class FavoriteListAdapter: RecyclerView.Adapter<FavoriteListAdapter.FavoriteListViewHolder>() {

    inner class FavoriteListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun populateModelCitata(citataModel: CitataWithAuthor) {
            itemView.tvQuotes.text = citataModel.citata.text
            itemView.tvAuthor.text = citataModel.author.authorName
            if (citataModel.citata.isFavorite == 0) {
                itemView.favoriteIcon.setImageResource(R.drawable.ic_favorite_not_marked)

            }else{
                itemView.favoriteIcon.setImageResource(R.drawable.ic_favorite_marked)
            }
            itemView.favoriteIcon.setOnClickListener {
                    itemView.favoriteIcon.setImageResource(R.drawable.ic_favorite_not_marked)
                onFavIconClick.invoke(citataModel.citata)
            }

            // Copy icon clicked
            itemView.copyIcon.setOnClickListener {
                onCopyIconClick.invoke(citataModel.citata.text, citataModel.author.authorName)
            }

            // Share icon clicked
            itemView.shareIcon.setOnClickListener {
                onshareIconClick.invoke(citataModel.citata.text, citataModel.author.authorName)
            }
        }

    }

    // fav icon clicked
    private var onFavIconClick: (citata: Citata) -> Unit = {}
    fun setOnFavIconClickListener(onFavIconClick: (citata: Citata) -> Unit) {
        this.onFavIconClick = onFavIconClick
    }

    // fav icon clicked
    private var onCopyIconClick: (citataName: String, authorName: String) -> Unit = {citataName, authorName ->  }
    fun setOnCopyIconClickListener(onCopyIconClick: (citataName: String, authorName: String) -> Unit) {
        this.onCopyIconClick = onCopyIconClick
    }

    // share icon lambda
    private var onshareIconClick: (citataText: String, authorName: String) -> Unit = {citataText, authorName ->  }
    fun setOnShareIconClickListener(onShareIconClick: (citataText: String, authorName: String) -> Unit) {
        this.onshareIconClick = onShareIconClick
    }

    var models: List<CitataWithAuthor> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.quotes_item, parent, false)
        return FavoriteListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return  models.size
    }

    override fun onBindViewHolder(holder: FavoriteListViewHolder, position: Int) {
        holder.populateModelCitata(models[position])
    }
}