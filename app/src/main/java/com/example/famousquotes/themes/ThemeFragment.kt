package com.example.famousquotes.themes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.famousquotes.R
import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.database.CitataDatabase
import kotlinx.android.synthetic.main.fragment_theme.*

class ThemeFragment : Fragment() {

    private lateinit var navController: NavController
    private val myAdapter: ThemeRVAdapter = ThemeRVAdapter()
    private lateinit var dao: CitataDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set title bar
        //(activity as MainActivity?)!!.setActionBarTitle("Home")
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
        navController = Navigation.findNavController(view)
        themeRV.adapter = myAdapter

        myAdapter.setOnItemClickListener { themeId->
            val action = ThemeFragmentDirections.actionThemeFragmentToThemeQuotesFragment(themeId)
            navController.navigate(action)
        }

        dao = CitataDatabase.getInstance(requireContext()).dao()
        setData()
    }

    private fun setData(){
        myAdapter.models = dao.getAllTheme()
    }

}