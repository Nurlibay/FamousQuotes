package com.example.famousquotes.authors_menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.famousquotes.R
import kotlinx.android.synthetic.main.fragment_author_quotes.*

class AuthorQuotesFragment : Fragment() {

    private val myAdapter : AuthorQuotesAdapter = AuthorQuotesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_author_quotes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorQuotesRV.adapter = myAdapter
        authorQuotesRV.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        setData()
    }

    private fun setData() {
        val models: MutableList<AuthorQuotesModel> = mutableListOf()
        for (i in 1..10){
            models.add(AuthorQuotesModel("Famous Quotes Text $i", "Author Name $i"))
        }
        myAdapter.models = models
    }

}