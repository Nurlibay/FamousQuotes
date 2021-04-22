package com.example.famousquotes.themes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.famousquotes.R
import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.database.CitataDatabase
import kotlinx.android.synthetic.main.fragment_theme.*

class ThemeFragment : Fragment() {

    private val myAdapter: ThemeRVAdapter = ThemeRVAdapter()
    private lateinit var dao: CitataDao

    companion object{
        const val TYPE_ID = "typeId"
        const val DEATH = 1
        const val EDUCATION = 2
        const val MONEY = 3
        const val AGE = 4
        const val FAMILY = 5
        const val LOVE = 6
        const val SUCCESS = 7
        const val SCIENCE = 8
        const val SPORTS = 9
        const val CHANGE = 10
        const val JOBS = 11
        const val LIFE = 12
        const val WAR = 13
        const val TIME = 14
    }

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
        dao = CitataDatabase.getInstance(requireContext()).dao()
        setData()
    }

    private fun setData(){
        myAdapter.models = dao.getAllTheme()
    }

}