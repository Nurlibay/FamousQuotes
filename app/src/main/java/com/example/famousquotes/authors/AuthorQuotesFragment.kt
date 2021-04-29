package com.example.famousquotes.authors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.navArgs
import com.example.famousquotes.R
import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.database.CitataDatabase
import com.example.famousquotes.data.entities.Citata
import com.example.famousquotes.items_space.ItemsBetweenSpace
import kotlinx.android.synthetic.main.fragment_author_quotes.*

class AuthorQuotesFragment : Fragment() {

    private val myAdapter : AuthorQuotesAdapter = AuthorQuotesAdapter()
    private lateinit var dao: CitataDao
    private val args: AuthorQuotesFragmentArgs by navArgs()
    private val space = ItemsBetweenSpace()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = CitataDatabase.getInstance(requireContext()).dao()
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
        space.spacingItemDecoration(30)
        authorQuotesRV.addItemDecoration(space)
        setData(args.authorId)

        // search function here ...
        etSearchQuotes.addTextChangedListener {
            val result : List<Citata> = dao.searchCitataByText(args.authorId, "${it.toString()}%")
            myAdapter.models = result
        }
    }

    private fun setData(authorId: Int) {
        myAdapter.models = dao.getCitataByAuthorId(authorId)
    }
}