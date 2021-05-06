package com.example.famousquotes.themes

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
import com.example.famousquotes.items_space.MarginItemDecoration
import com.example.famousquotes.R
import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.database.CitataDatabase
import com.example.famousquotes.data.entities.Citata
import com.example.famousquotes.data.entities.CitataWithAuthor
import kotlinx.android.synthetic.main.fragment_theme_quotes.*

class ThemeQuotesFragment : Fragment() {

    private val myAdapter : ThemeQuotesAdapter = ThemeQuotesAdapter()
    private lateinit var dao: CitataDao
    private lateinit var citata: Citata

    private val args: ThemeQuotesFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = CitataDatabase.getInstance(requireContext()).dao()
         //Set title bar
        (activity as MainActivity?)!!.setActionBarTitle(args.themeName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theme_quotes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        themeQuotesRV.adapter = myAdapter
        themeQuotesRV.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_standard)))
        setData(args.themeId)

        // search function here ...
        etSearch.addTextChangedListener {
            val result : List<CitataWithAuthor> = dao.searchCitataByText(args.themeId, "%${it.toString()}%")
            result.forEachIndexed { index, s->
                result[index].citata.text = s.citata.text.replace(it.toString(), "<b>${it.toString()}</b>", true)
            }
            myAdapter.models = result
        }

        // fav icon click event
        myAdapter.setOnFavIconClickListener {
            setFavorite(it)
        }

        // copy icon click event
        myAdapter.setOnCopyIconClickListener { citataText, authorName ->
            val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("EditText", "${citataText} \n ~ ${authorName}")
            clipboard.setPrimaryClip(clip)
            Toast.makeText(context, "Successful copied !", Toast.LENGTH_SHORT).show()
        }

        // share icon click event
        myAdapter.setOnShareIconClickListener { citataText, authorName ->
            val shareIntent = Intent().apply {
                this.action = Intent.ACTION_SEND
                this.putExtra(Intent.EXTRA_TEXT, "${citataText} \n ~ ${authorName}")
                this.type = "text/plain"
            }
            startActivity(shareIntent)
        }

    }

    private fun setData(themeId: Int) {
        myAdapter.models = dao.getCitataWithAuthorByThemeId(themeId)
    }

    private fun setFavorite(citata: Citata){
        citata.isFavorite= 1 - citata.isFavorite
        dao.citataUpdate(citata)
    }

}