package uz.texnopos.famousquotes.authors.author

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.navigation.NavController
import androidx.navigation.Navigation
import uz.texnopos.famousquotes.R
import uz.texnopos.famousquotes.authors.adapters.AuthorsRVAdapter
import uz.texnopos.famousquotes.data.entities.Author
import kotlinx.android.synthetic.main.fragment_authors.*
import uz.texnopos.famousquotes.data.dao.CitataDao
import uz.texnopos.famousquotes.data.database.CitataDatabase

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