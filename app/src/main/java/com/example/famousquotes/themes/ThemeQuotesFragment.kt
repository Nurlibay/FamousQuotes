package com.example.famousquotes.themes

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
import com.example.famousquotes.data.entities.CitataWithAuthor
import kotlinx.android.synthetic.main.fragment_theme_quotes.*

class ThemeQuotesFragment : Fragment() {

    private val myAdapter : ThemeQuotesAdapter = ThemeQuotesAdapter()
    private lateinit var dao: CitataDao
    lateinit var citata: Citata

    private val args: ThemeQuotesFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = CitataDatabase.getInstance(requireContext()).dao()
        // Set title bar
        //(activity as MainActivity?)!!.setActionBarTitle("Citata")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theme_quotes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        themeQuotesRV.adapter = myAdapter
        setData(args.themeId)
        citata = dao.citataById(args.themeId)

        // search function here ...
        etSearch.addTextChangedListener {
            val result : List<CitataWithAuthor> = dao.searchCitataByText(args.themeId, "${it.toString()}%")
            myAdapter.models = result
        }
        myAdapter.setOnFavIconClickListener {
            if (citata.isFavorite == 1) {
                it.setBackgroundResource(R.drawable.ic_favorite_marked)
            }else{
                it.setBackgroundResource(R.drawable.ic_favorite_not_marked)
            }
            setFavorite()
        }
    }

    private fun setData(themeId: Int) {
        myAdapter.models = dao.getCitataWithAuthorByThemeId(themeId)
    }
    private fun setFavorite(){
      if(citata.isFavorite == null) citata.isFavorite = 1
        else citata.isFavorite= 1-citata.isFavorite!!

        dao.citataUpdate(citata)

    }
    
}