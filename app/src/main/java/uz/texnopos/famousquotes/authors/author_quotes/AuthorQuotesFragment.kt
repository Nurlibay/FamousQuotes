package uz.texnopos.famousquotes.authors.author_quotes

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.navArgs
import uz.texnopos.famousquotes.MainActivity
import uz.texnopos.famousquotes.R
import uz.texnopos.famousquotes.data.entities.CitataWithAuthor
import uz.texnopos.famousquotes.items_space.MarginItemDecoration
import uz.texnopos.famousquotes.themes.adapters.ThemeQuotesAdapter
import kotlinx.android.synthetic.main.fragment_author_quotes.*
import uz.texnopos.famousquotes.data.dao.CitataDao
import uz.texnopos.famousquotes.data.database.CitataDatabase

class AuthorQuotesFragment : Fragment(R.layout.fragment_author_quotes), AuthorQuotesView {

    // private lateinit var settings: Settings
    // private val myAdapter : AuthorQuotesAdapter = AuthorQuotesAdapter()
    private val myAdapter : ThemeQuotesAdapter = ThemeQuotesAdapter()
    private lateinit var authorQuotesPresenter: AuthorQuotesPresenter
    private lateinit var dao: CitataDao
    private val args: AuthorQuotesFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dao = CitataDatabase.getInstance(requireContext()).dao()

        // Set title bar
        (activity as MainActivity?)!!.setActionBarTitle(args.authorName)
        //setHasOptionsMenu(true)
        //settings = Settings(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorQuotesRV.adapter = myAdapter
        authorQuotesRV.addItemDecoration(MarginItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_standard)))
        authorQuotesPresenter = AuthorQuotesPresenter(dao, this)
        authorQuotesPresenter.getCitataByAuthorId(args.authorId)
        
        // search function here ...
        etSearchQuotes.addTextChangedListener {
//            val result : List<CitataWithAuthor> = dao.searchCitataByAuthor(args.authorId, "%${it.toString()}%")
//            myAdapter.models = result
            authorQuotesPresenter.searchCitataByAuthor(args.authorId, "%${it.toString()}%")
        }

        // fav icon click event
        myAdapter.setOnFavIconClickListener {
            authorQuotesPresenter.setFavorite(it)
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

    override fun setData(models: List<CitataWithAuthor>) {
        myAdapter.models = models
    }

    override fun searchCitataByAuthor(models: List<CitataWithAuthor>) {
        myAdapter.models = models
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

}