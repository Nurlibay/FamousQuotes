package uz.texnopos.famousquotes.settings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import uz.texnopos.famousquotes.R
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_group.addOnButtonCheckedListener { group, selectedBtnId, isChecked ->
            if(isChecked){
                val theme = when (selectedBtnId) {
                    R.id.defaultBtn -> {
                        AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
                    }
                    R.id.darkBtn -> {
                        AppCompatDelegate.MODE_NIGHT_YES
                    }
                    else -> {
                        AppCompatDelegate.MODE_NIGHT_NO
                    }
                }
                Log.d(TAG, "isChecked $isChecked theme:$theme")
                AppCompatDelegate.setDefaultNightMode(theme)
            }
        }
    }

}