package com.example.famousquotes.favorite

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
import com.example.famousquotes.items_space.MarginItemDecoration
import com.example.famousquotes.R
import com.example.famousquotes.R.layout.fragment_favorite
import com.example.famousquotes.data.dao.CitataDao
import com.example.famousquotes.data.database.CitataDatabase
import com.example.famousquotes.data.entities.Citata
import kotlinx.android.synthetic.main.fragment_favorite.*

class FavoriteFragment : Fragment() {

    private val myAdapter : FavoriteListAdapter = FavoriteListAdapter()
    private lateinit var dao : CitataDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = CitataDatabase.getInstance(requireContext()).dao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteListRV.adapter = myAdapter
        favoriteListRV.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_standard)))
        setData()

        myAdapter.setOnFavIconClickListener {
            setFavorite(it)
            myAdapter.models = dao.getFavorites()
            if (myAdapter.models.isEmpty()){
                //favMenu.setBackgroundResource(R.drawable.ic_favorite_not_marked)
                no_favorites.setText(R.string.no_favorites)
                no_favorites.textSize = resources.getDimension(R.dimen.no_favorites_text_size)
                //Toast.makeText(context, "No Favorites", Toast.LENGTH_SHORT).show()
            }
        }

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

    private fun setData() {
        myAdapter.models = dao.getFavorites()
    }

    private fun setFavorite(citata: Citata){
        citata.isFavorite= 1 - citata.isFavorite
        dao.citataUpdate(citata)
    }
}