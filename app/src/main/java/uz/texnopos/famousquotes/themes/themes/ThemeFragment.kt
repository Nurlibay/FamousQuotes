package uz.texnopos.famousquotes.themes.themes

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import uz.texnopos.famousquotes.MainActivity
import uz.texnopos.famousquotes.R
import uz.texnopos.famousquotes.data.entities.Theme
import uz.texnopos.famousquotes.themes.adapters.ThemeRVAdapter
import kotlinx.android.synthetic.main.fragment_theme.*
import uz.texnopos.famousquotes.data.dao.CitataDao
import uz.texnopos.famousquotes.data.database.CitataDatabase

class ThemeFragment : Fragment(R.layout.fragment_theme), ThemeView {

    private lateinit var navController: NavController
    private val myAdapter: ThemeRVAdapter = ThemeRVAdapter()
    private lateinit var dao: CitataDao
    private lateinit var themePresenter: ThemePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set title bar
        //(activity as MainActivity?)!!.setActionBarTitle("Home")

        val mAlertDialogBuilder = context?.let { AlertDialog.Builder(it) }

        val callback: OnBackPressedCallback =
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {

                        mAlertDialogBuilder?.setTitle("Famous Quotes")
                        mAlertDialogBuilder?.setIcon(R.mipmap.ic_launcher)
                        mAlertDialogBuilder?.setMessage("Are you sure do you want to exit ?")
                        mAlertDialogBuilder?.setCancelable(false)

                        mAlertDialogBuilder?.setPositiveButton("Yes") { _, _ ->
                            //finish
                            requireActivity().finish()
                        }

                        mAlertDialogBuilder?.setNegativeButton("NO") { _, _ ->
                            mAlertDialogBuilder.setCancelable(true)
                        }

                        mAlertDialogBuilder?.setNeutralButton("Cancel") { _, _ ->
                            mAlertDialogBuilder.setCancelable(true)
                        }

                        val mAlertDialog = mAlertDialogBuilder?.create()
                        mAlertDialog?.show()

                    }
                }

        (requireActivity() as MainActivity).onBackPressedDispatcher.addCallback(this, callback)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        themeRV.adapter = myAdapter

        myAdapter.setOnItemClickListener { themeId, themeName ->
            val action = ThemeFragmentDirections.actionThemeFragmentToThemeQuotesFragment(themeId, themeName)
            navController.navigate(action)
        }

        dao = CitataDatabase.getInstance(requireContext()).dao()
        themePresenter = ThemePresenter(dao, this)
        themePresenter.getAllThemes()
    }
    
    override fun setData(models: List<Theme>) {
        myAdapter.models = models
    }

}