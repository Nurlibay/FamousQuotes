package com.example.famousquotes.themes_menu

import android.view.LayoutInflater
import kotlinx.android.synthetic.main.themes.view.*
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.famousquotes.R

class ThemeRVAdapter: RecyclerView.Adapter<ThemeRVAdapter.ThemeListViewHolder>() {

    var navController: NavController? = null

    inner class ThemeListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModel(themeModel: ThemeModel){
            itemView.tvTheme.text = themeModel.themeName
            itemView.setOnClickListener {
                navController = Navigation.findNavController(it)
                navController!!.navigate(R.id.action_themeFragment_to_themeQuotesFragment)
            }
        }
    }

    var models: List<ThemeModel> = listOf()
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