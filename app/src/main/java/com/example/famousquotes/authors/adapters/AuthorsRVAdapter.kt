package com.example.famousquotes.authors.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.famousquotes.R
import com.example.famousquotes.data.entities.Author
import kotlinx.android.synthetic.main.authors_item.view.*

class AuthorsRVAdapter: RecyclerView.Adapter<AuthorsRVAdapter.AuthorsListViewHolder>() {

    private var onItemClick: (authorId: Int, authorName: String) -> Unit = {authorId, authorName ->  }
    fun setOnItemClickListener(onItemClick: (authorId: Int, authorName: String) -> Unit) {
        this.onItemClick = onItemClick
    }

    inner class AuthorsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModel(author: Author){
            itemView.tvAuthorName.text = author.authorName
            itemView.setOnClickListener {
                onItemClick.invoke(author.id, author.authorName)
            }

            val imageResName = "author${author.id}"
            Glide
                    .with(itemView)
                    .load(itemView.context.resources.getIdentifier(imageResName, "drawable", itemView.context.packageName))
                    .into(itemView.authorImage)

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