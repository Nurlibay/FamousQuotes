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
import com.example.famousquotes.R
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
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteListRV.adapter = myAdapter
        setData()

        myAdapter.setOnFavIconClickListener {
            setFavorite(it)
            myAdapter.models = dao.getFavorites()
        }

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

    private fun setData() {
        myAdapter.models = dao.getFavorites()
    }

    private fun setFavorite(citata: Citata){
        citata.isFavorite= 1 - citata.isFavorite
        dao.citataUpdate(citata)
    }


}