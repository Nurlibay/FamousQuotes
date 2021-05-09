package com.example.famousquotes.authors

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.navArgs
import com.example.famousquotes.MainActivity
import com.example.famousquotes.R
import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.database.CitataDatabase
import com.example.famousquotes.data.entities.Citata
import com.example.famousquotes.data.entities.CitataWithAuthor
import com.example.famousquotes.items_space.MarginItemDecoration
import com.example.famousquotes.themes.ThemeQuotesAdapter
import kotlinx.android.synthetic.main.fragment_author_quotes.*
import kotlinx.android.synthetic.main.fragment_theme_quotes.*

class AuthorQuotesFragment : Fragment() {

    //private val myAdapter : AuthorQuotesAdapter = AuthorQuotesAdapter()
    private val myAdapter : ThemeQuotesAdapter = ThemeQuotesAdapter()

    private lateinit var dao: CitataDao
    private val args: AuthorQuotesFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = CitataDatabase.getInstance(requireContext()).dao()

        //Set title bar
        (activity as MainActivity?)!!.setActionBarTitle(args.authorName)

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
        authorQuotesRV.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_standard)))
        setData(args.authorId)
        
        // search function here ...
        etSearchQuotes.addTextChangedListener {
            val result : List<CitataWithAuthor> = dao.searchCitataByAuthor(args.authorId, "%${it.toString()}%")
            myAdapter.models = result
        }

        // fav icon click event
        myAdapter.setOnFavIconClickListener {
            setFavorite(it)
        }

        // copy icon click event
        myAdapter.setOnCopyIconClickListener { citataText, authorName ->
            val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("EditText", "$citataText \n ~ $authorName")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "Successful copied !", Toast.LENGTH_SHORT).show()
        }

        // share icon click event
        myAdapter.setOnShareIconClickListener { citataText, authorName ->
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT, "$citataText \n ~ $authorName")
                this.type = "text/plain"
            }
            startActivity(shareIntent)
        }

    }

    private fun setData(authorId: Int) {
        myAdapter.models = dao.getCitataByAuthorId(authorId)
    }

    private fun setFavorite(citata: Citata){
        citata.isFavorite= 1 - citata.isFavorite
        dao.citataUpdate(citata)
    }
    
}