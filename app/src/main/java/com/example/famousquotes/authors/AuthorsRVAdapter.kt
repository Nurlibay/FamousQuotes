package com.example.famousquotes.authors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.famousquotes.R
import com.example.famousquotes.data.entityes.Author
import kotlinx.android.synthetic.main.authors_item.view.*

class AuthorsRVAdapter: RecyclerView.Adapter<AuthorsRVAdapter.AuthorsListViewHolder>() {

    var navController: NavController? = null

    inner class AuthorsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModel(authors: Author){
            itemView.tvAuthorName.text = authors.authorName
            itemView.setOnClickListener {
                navController = Navigation.findNavController(it)
                navController!!.navigate(R.id.action_authorsFragment_to_authorQuotesFragment)
            }
        }
    }

    var models : List<Author> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorsListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.authors_item, parent, false)
        return AuthorsListViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: AuthorsListViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
}