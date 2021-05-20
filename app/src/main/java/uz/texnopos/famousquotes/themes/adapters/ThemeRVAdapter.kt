package uz.texnopos.famousquotes.themes.adapters

import android.view.LayoutInflater
import kotlinx.android.synthetic.main.themes.view.*
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.texnopos.famousquotes.R
import uz.texnopos.famousquotes.data.entities.Theme

class ThemeRVAdapter: RecyclerView.Adapter<ThemeRVAdapter.ThemeListViewHolder>() {

    private var onItemClick: (themeId: Int, themeName: String) -> Unit = {themeId, themeName ->  }
    fun setOnItemClickListener(onItemClick: (themeId: Int, themeName: String) -> Unit) {
        this.onItemClick = onItemClick
    }

    inner class ThemeListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModel(theme: Theme) {
            itemView.tvTheme.text = theme.themeName

            val imageResName = "theme${theme.id}"
            Glide
                    .with(itemView)
                    .load(itemView.context.resources.getIdentifier(imageResName, "drawable", itemView.context.packageName))
                    .into(itemView.themeIcon)

            itemView.setOnClickListener {
                onItemClick.invoke(theme.id, theme.themeName)
            }
        }
    }

    var models: List<Theme> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ThemeListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.themes, parent, false)
        return ThemeListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: ThemeListViewHolder, position: Int) {
        holder.populateModel(models[position])
    }

}