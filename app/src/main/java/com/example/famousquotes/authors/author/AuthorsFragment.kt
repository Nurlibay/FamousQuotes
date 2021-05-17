package com.example.famousquotes.authors.author

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.famousquotes.R
import com.example.famousquotes.authors.adapters.AuthorsRVAdapter
import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.database.CitataDatabase
import com.example.famousquotes.data.entities.Author
import kotlinx.android.synthetic.main.fragment_authors.*

class AuthorsFragment : Fragment(R.layout.fragment_authors), AuthorView {

    private lateinit var navController: NavController
    private val myAdapter : AuthorsRVAdapter = AuthorsRVAdapter()
    private lateinit var dao: CitataDao
    private lateinit var authorPresenter: AuthorPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        authorsRV.adapter = myAdapter

        dao = CitataDatabase.getInstance(requireContext()).dao()
        authorPresenter = AuthorPresenter(dao, this)
        authorPresenter.getAllAuthors()

        myAdapter.setOnItemClickListener { authorId, authorName ->
            val action = AuthorsFragmentDirections.actionAuthorsFragmentToAuthorQuotesFragment(
                authorId,
                authorName
            )
            navController.navigate(action)
        }

        // search function here ...
        etSearchAuthors.addTextChangedListener {
//            val result: List<Author> = dao.searchAuthorByName("%${it.toString()}%")
//            myAdapter.models = result
            authorPresenter.searchAuthorByName("%${it.toString()}%")
        }
    }

    override fun setData(models: List<Author>) {
        myAdapter.models = models
    }

    override fun searchAuthorByName(models: List<Author>) {
        myAdapter.models = models
    }

}