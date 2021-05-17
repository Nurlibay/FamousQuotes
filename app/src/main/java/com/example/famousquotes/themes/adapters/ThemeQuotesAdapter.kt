package com.example.famousquotes.themes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.famousquotes.R
import com.example.famousquotes.data.entities.Citata
import com.example.famousquotes.data.entities.CitataWithAuthor
import kotlinx.android.synthetic.main.quotes_item.view.*

class ThemeQuotesAdapter : RecyclerView.Adapter<ThemeQuotesAdapter.ThemeQuotesViewHolder>() {

    //private var lastPosition = -1
//    var textSize = 0f
//    set(value) {
//        field = value
//        notifyDataSetChanged()
//    }

    inner class ThemeQuotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun populateModelCitata(citataModel: CitataWithAuthor, onFavIconClick: (citata: Citata) -> Unit) {
            itemView.tvQuotes.text = HtmlCompat.fromHtml(citataModel.citata.text, HtmlCompat.FROM_HTML_MODE_LEGACY)
            itemView.tvAuthor.text = citataModel.author.authorName
//            itemView.tvQuotes.textSize = textSize
//            itemView.tvAuthor.textSize = textSize
            if (citataModel.citata.isFavorite == 0) {
                itemView.favoriteIcon.setImageResource(R.drawable.ic_favorite_not_marked)

            } else {
                itemView.favoriteIcon.setImageResource(R.drawable.ic_favorite_marked)
            }

            // Fav icon clicked
            itemView.favoriteIcon.setOnClickListener {
                if (citataModel.citata.isFavorite == 0) {
                    itemView.favoriteIcon.setImageResource(R.drawable.ic_favorite_marked)
                } else {
                    itemView.favoriteIcon.setImageResource(R.drawable.ic_favorite_not_marked)
                }
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

    // fav icon lambda
    private var onFavIconClick: (citata: Citata) -> Unit = {}
    fun setOnFavIconClickListener(onFavIconClick: (citata: Citata) -> Unit) {
        this.onFavIconClick = onFavIconClick
    }

    // copy icon lambda
    private var onCopyIconClick: (citataName: String, authorName: String) -> Unit = { citataName, authorName -> }
    fun setOnCopyIconClickListener(onCopyIconClick: (citataName: String, authorName: String) -> Unit) {
        this.onCopyIconClick = onCopyIconClick
    }

    // share icon lambda
    private var onshareIconClick: (citataText: String, authorName: String) -> Unit = { citataText, authorName -> }
    fun setOnShareIconClickListener(onShareIconClick: (citataText: String, authorName: String) -> Unit) {
        this.onshareIconClick = onShareIconClick
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

//        val animation = AnimationUtils.loadAnimation(holder.itemView.context,
//                if (position > lastPosition) R.anim.up_from_bottom else R.anim.down_from_top)
//        holder.itemView.startAnimation(animation)
//        lastPosition = position

    }

//    override fun onViewDetachedFromWindow(holder: ThemeQuotesViewHolder) {
//        super.onViewDetachedFromWindow(holder)
//        holder.itemView.clearAnimation()
//    }

}