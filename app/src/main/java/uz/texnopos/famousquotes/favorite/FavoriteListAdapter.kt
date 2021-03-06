package uz.texnopos.famousquotes.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.famousquotes.data.entities.Citata
import uz.texnopos.famousquotes.data.entities.CitataWithAuthor
import kotlinx.android.synthetic.main.quotes_item.view.*
import uz.texnopos.famousquotes.R

class FavoriteListAdapter: RecyclerView.Adapter<FavoriteListAdapter.FavoriteListViewHolder>() {

    //private var lastPosition = -1

    inner class FavoriteListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun populateModelCitata(citataModel: CitataWithAuthor, position: Int) {
            itemView.tvQuotes.text = citataModel.citata.text
            itemView.tvAuthor.text = citataModel.author.authorName
            if (citataModel.citata.isFavorite == 0) {
                itemView.favoriteIcon.setImageResource(R.drawable.ic_favorite_not_marked)
            }else{
                itemView.favoriteIcon.setImageResource(R.drawable.ic_favorite_marked)
            }
            itemView.favoriteIcon.setOnClickListener {
                itemView.favoriteIcon.setImageResource(R.drawable.ic_favorite_not_marked)
                onFavIconClick.invoke(citataModel.citata, position)
                //notifyItemRemoved(position)
                //notifyItemRangeChanged(position, models.size)
                //notifyItemChanged(position)
                //notifyDataSetChanged()
            }

            // Copy icon clicked
            itemView.copyIcon.setOnClickListener {
                onCopyIconClick.invoke(citataModel.citata.text, citataModel.author.authorName)
            }

            // Share icon clicked
            itemView.shareIcon.setOnClickListener {
                onShareIconClick.invoke(citataModel.citata.text, citataModel.author.authorName)
            }

        }
    }

    // fav icon clicked
    private var onFavIconClick: (citata: Citata, position: Int) -> Unit = {citata, position ->  }
    fun setOnFavIconClickListener(onFavIconClick: (citata: Citata, position: Int) -> Unit) {
        this.onFavIconClick = onFavIconClick
    }

    // fav icon clicked
    private var onCopyIconClick: (citataName: String, authorName: String) -> Unit = {citataName, authorName ->  }
    fun setOnCopyIconClickListener(onCopyIconClick: (citataName: String, authorName: String) -> Unit) {
        this.onCopyIconClick = onCopyIconClick
    }
    
    // share icon lambda
    private var onShareIconClick: (citataText: String, authorName: String) -> Unit = {citataText, authorName ->  }
    fun setOnShareIconClickListener(onShareIconClick: (citataText: String, authorName: String) -> Unit) {
        this.onShareIconClick = onShareIconClick
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
        holder.populateModelCitata(models[position], position)

//        val animation = AnimationUtils.loadAnimation(holder.itemView.context,
//            if (position > lastPosition) R.anim.up_from_bottom else R.anim.down_from_top)
//        holder.itemView.startAnimation(animation)
//        lastPosition = position

    }

//    override fun onViewDetachedFromWindow(holder: FavoriteListViewHolder) {
//        super.onViewDetachedFromWindow(holder)
//        holder.itemView.clearAnimation()
//    }

}