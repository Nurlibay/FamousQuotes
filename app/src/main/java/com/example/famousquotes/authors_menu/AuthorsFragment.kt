package com.example.famousquotes.authors_menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.famousquotes.R
import kotlinx.android.synthetic.main.fragment_authors.*

class AuthorsFragment : Fragment() {

    private val myAdapter : AuthorsRVAdapter = AuthorsRVAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorsRV.adapter = myAdapter
        authorsRV.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        setData()
    }

    private fun setData() {
        val models: MutableList<AuthorsModel> = mutableListOf()
        for (i in 1..35){
            models.add(AuthorsModel("Author Name $i"))
        }
        myAdapter.models = models
    }

}