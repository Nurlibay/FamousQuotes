package uz.texnopos.famousquotes.authors.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.texnopos.famousquotes.R
import uz.texnopos.famousquotes.data.entities.Citata
import kotlinx.android.synthetic.main.authors_quotes.view.*

class AuthorQuotesAdapter: RecyclerView.Adapter<AuthorQuotesAdapter.AuthorQuotesViewHolder>() {

    inner class AuthorQuotesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun populateModel(citata: Citata){
            itemView.quotesText.text = citata.text
        }
    }

    var models: List<Citata> = listOf()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AuthorQuotesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.authors_quotes, parent, false)
        return AuthorQuotesViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onBindViewHolder(holder: AuthorQuotesViewHolder, position: Int) {
        holder.populateModel(models[position])
    }
}