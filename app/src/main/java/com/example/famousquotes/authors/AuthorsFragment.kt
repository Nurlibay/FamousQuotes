package com.example.famousquotes.authors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.famousquotes.R
import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.database.CitataDatabase
import kotlinx.android.synthetic.main.fragment_authors.*

class AuthorsFragment : Fragment() {

    private lateinit var navController: NavController
    private val myAdapter : AuthorsRVAdapter = AuthorsRVAdapter()
    private lateinit var dao: CitataDao

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
        navController = Navigation.findNavController(view)
        authorsRV.adapter = myAdapter

        myAdapter.setOnItemClickListener { authorId ->
            val action = AuthorsFragmentDirections.actionAuthorsFragmentToAuthorQuotesFragment(authorId)
            navController.navigate(action)
        }

        dao = CitataDatabase.getInstance(requireContext()).dao()
        setData()
    }

    private fun setData() {
        myAdapter.models = dao.getAllTAuthors()
    }

}