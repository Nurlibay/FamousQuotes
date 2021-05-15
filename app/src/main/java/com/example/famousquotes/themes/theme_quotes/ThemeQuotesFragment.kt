package com.example.famousquotes.themes.theme_quotes

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.famousquotes.MainActivity
import com.example.famousquotes.R
import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.database.CitataDatabase
import com.example.famousquotes.data.entities.CitataWithAuthor
import com.example.famousquotes.items_space.MarginItemDecoration
import com.example.famousquotes.themes.adapters.ThemeQuotesAdapter
import kotlinx.android.synthetic.main.fragment_theme_quotes.*

class ThemeQuotesFragment : Fragment(R.layout.fragment_theme_quotes), ThemeQuotesView {

    //todo private lateinit var settings: Settings
    private val myAdapter: ThemeQuotesAdapter = ThemeQuotesAdapter()
    private lateinit var dao: CitataDao
    private lateinit var themeQuotesPresenter: ThemeQuotesPresenter
    private val args: ThemeQuotesFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = CitataDatabase.getInstance(requireContext()).dao()
        //Set title bar
        (requireActivity() as MainActivity).setActionBarTitle(args.themeName)
        //setHasOptionsMenu(true)
        //settings = Settings(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        themeQuotesRV.adapter = myAdapter
        //myAdapter.textSize = settings.getTextSize()
        themeQuotesRV.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_standard)))
        themeQuotesPresenter = ThemeQuotesPresenter(dao, this)
        themeQuotesPresenter.getCitataWithAuthorByThemeId(args.themeId)

        // search function here ...
        etSearch.addTextChangedListener {
            val words = it.toString().split(" ")
            var searchWord = "%"
            words.forEach { word ->
                searchWord += "$word%"
            }
            //val result: List<CitataWithAuthor> = dao.searchCitataByText(args.themeId, searchWord)
            themeQuotesPresenter.searchCitataByText(args.themeId, searchWord, words)
        }

        // fav icon click event
        myAdapter.setOnFavIconClickListener {
            it.isFavorite = 1 - it.isFavorite
            themeQuotesPresenter.setFavorite(it)
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

    override fun setSearchResult(words: List<String>, result: List<CitataWithAuthor>) {
        result.forEachIndexed { index, s ->
            words.forEach { word ->
                if (word.isNotBlank() && word.isNotEmpty())
                    result[index].citata.text = s.citata.text.replace(word, "<b>${word}</b>", true)
            }
        }
        myAdapter.models = result
    }

    override fun setData(models: List<CitataWithAuthor>) {
        myAdapter.models = models
    }
}

//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        inflater.inflate(R.menu.text_size, menu)
//        super.onCreateOptionsMenu(menu, inflater)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.btn_minus -> {
//                if (settings.getTextSize() > 12) {
//                    settings.decrementTextSize()
//                    onTextSizeChanged(settings.getTextSize())
//                }
//            }
//            R.id.btn_plus -> {
//                if (settings.getTextSize() < 32) {
//                    settings.incrementTextSize()
//                    onTextSizeChanged(settings.getTextSize())
//                }
//            }
//        }
//        return true
//    }
//
//    override fun onTextSizeChanged(size: Float) {
//        myAdapter.textSize = size
//    }