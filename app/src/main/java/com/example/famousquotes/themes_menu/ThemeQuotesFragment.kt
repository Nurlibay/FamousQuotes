package com.example.famousquotes.themes_menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.famousquotes.R
import kotlinx.android.synthetic.main.fragment_theme_quotes.*

class ThemeQuotesFragment : Fragment() {

    private val myAdapter : ThemeQuotesAdapter = ThemeQuotesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theme_quotes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        themeQuotesRV.adapter = myAdapter
        themeQuotesRV.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        setData()
    }

    private fun setData() {
        val models : MutableList<QuotesModel> = mutableListOf()
        for (i in 1..50){
          models.add(QuotesModel("Famous Quotes Text $i", "Author Name $i"))
        }
        myAdapter.models = models
    }

}