package com.example.famousquotes.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.famousquotes.R
import com.example.famousquotes.data.entities.Citata
import com.example.famousquotes.data.entities.CitataWithAuthor
import kotlinx.android.synthetic.main.favorite_item.view.*
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

//            // Fav icon clicked
//            itemView.favIcon.setOnClickListener {
//                itemView.favIcon.setImageResource(R.drawable.ic_favorite_not_marked)
//                citataModel.citata.isFavorite = 0
//            }
//
//            // Copy icon clicked
//            itemView.copyIcon.setOnClickListener {
//                onCopyIconClick.invoke(itemView.copyIcon)
//            }
        }

    }

    // fav icon clicked
    private var onFavIconClick: (citata: Citata) -> Unit = {}
    fun setOnFavIconClickListener(onFavIconClick: (citata: Citata) -> Unit) {
        this.onFavIconClick = onFavIconClick
    }

    // fav icon clicked
    private var onCopyIconClick: (view: View) -> Unit = {}
    fun setOnCopyIconClickListener(onCopyIconClick: (view: View) -> Unit) {
        this.onCopyIconClick = onCopyIconClick
    }

    fun deleteCitata(citataWithAuthor: CitataWithAuthor){

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