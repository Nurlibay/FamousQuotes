package com.example.famousquotes.themes

import android.view.LayoutInflater
import kotlinx.android.synthetic.main.themes.view.*
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.famousquotes.R
import com.example.famousquotes.data.entityes.Theme

class ThemeRVAdapter: RecyclerView.Adapter<ThemeRVAdapter.ThemeListViewHolder>() {

    var navController: NavController? = null

    private var onItemClick: (themeId: Int) -> Unit = {}
    fun setOnItemClickListener(onItemClick: (themeId: Int) -> Unit) {
        this.onItemClick = onItemClick
    }

    inner class ThemeListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModel(theme: Theme) {
            itemView.tvTheme.text = theme.themeName
            itemView.setOnClickListener {
                onItemClick.invoke(theme.id)
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