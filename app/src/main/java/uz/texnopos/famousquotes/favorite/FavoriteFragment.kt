package uz.texnopos.famousquotes.favorite

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import uz.texnopos.famousquotes.items_space.MarginItemDecoration
import uz.texnopos.famousquotes.data.entities.CitataWithAuthor
import kotlinx.android.synthetic.main.fragment_favorite.*
import uz.texnopos.famousquotes.R
import uz.texnopos.famousquotes.data.dao.CitataDao
import uz.texnopos.famousquotes.data.database.CitataDatabase

class FavoriteFragment : Fragment(R.layout.fragment_favorite), FavoriteView {

    private val myAdapter : FavoriteListAdapter = FavoriteListAdapter()
    private lateinit var dao : CitataDao
    private lateinit var favoritePresenter: FavoritePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = CitataDatabase.getInstance(requireContext()).dao()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteListRV.adapter = myAdapter
        favoriteListRV.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_standard)))
        favoritePresenter = FavoritePresenter(dao, this)
        favoritePresenter.getFavorites()
        
        // Fav icon clicked
        myAdapter.setOnFavIconClickListener {citata, position ->

            myAdapter.notifyItemRemoved(position)
            myAdapter.notifyItemRangeChanged(position, myAdapter.models.size)
            //myAdapter.notifyDataSetChanged()

            favoritePresenter.setFavorite(citata)
            favoritePresenter.getFavorites()

            if (myAdapter.models.isEmpty()){
                no_favorites.setText(R.string.no_favorites)
                no_favorites.textSize = resources.getDimension(R.dimen.no_favorites_text_size)
            }
        }

        // Copy icon click event
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
    
    override fun setData(models: List<CitataWithAuthor>) {
        myAdapter.models = models
    }
}