package com.example.famousquotes.themes_menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.famousquotes.R
import kotlinx.android.synthetic.main.fragment_theme.*

class ThemeFragment : Fragment() {

    private val myAdapter: ThemeRVAdapter = ThemeRVAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theme, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        themeRV.adapter = myAdapter
        themeRV.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        themeRV.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL))
        setData()
    }

    private fun setData(){
        val models: MutableList<ThemeModel> = mutableListOf()
        for (i in 1..30){
            models.add(ThemeModel("Theme $i"))
        }
        myAdapter.models = models
    }

}