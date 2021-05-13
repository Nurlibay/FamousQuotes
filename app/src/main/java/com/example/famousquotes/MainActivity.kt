package com.example.famousquotes

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navController = findNavController(R.id.fragment)
        setupActionBarWithNavController(navController)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bottom_bar, menu)
        bottom_bar.setupWithNavController(menu!!, navController)
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        navController.navigateUp()
        return true
    }
    fun setActionBarTitle(title: String?) {
        supportActionBar!!.title = title
    }

//    override fun onBackPressed() {
//        if (supportFragmentManager.backStackEntryCount == 1) {
//            val dialog = AlertDialog.Builder(this)
//                    .setTitle("Aniq shig'asizba?")
//                    .setPositiveButton("Awa") { dialog, which ->
//                        finish()
//                    }
//                    .setNegativeButton("Yaq") { dialog, which ->
//                        dialog.dismiss()
//                    }
//                    .setCancelable(false)
//            dialog.show()
//        } else {
//            supportFragmentManager.popBackStackImmediate(
//                    supportFragmentManager.getBackStackEntryAt(
//                            1
//                    ).id, FragmentManager.POP_BACK_STACK_INCLUSIVE
//            )
//        }
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.darkMode -> {
//                Toast.makeText(this, "Dark Icon Clicked !", Toast.LENGTH_SHORT).show()
//            }
//        }
//        return true
//    }



//    override fun onBackPressed() {
//
//        val mAlertDialogBuilder = AlertDialog.Builder(this)
//
//        mAlertDialogBuilder.setTitle("Famous Quotes")
//        mAlertDialogBuilder.setIcon(R.mipmap.ic_launcher)
//        mAlertDialogBuilder.setMessage("Are you sure do you want to exit ?")
//        mAlertDialogBuilder.setCancelable(false)
//
//        mAlertDialogBuilder.setPositiveButton("Yes"){ _, _ ->
//            //finish()
//            super.onBackPressed()
//        }
//
//        mAlertDialogBuilder.setNegativeButton("NO"){_, _ ->
//            mAlertDialogBuilder.setCancelable(true)
//        }
//
//        mAlertDialogBuilder.setNeutralButton("Cancel"){_, _ ->
//            mAlertDialogBuilder.setCancelable(true)
//        }
//
//        val mAlertDialog = mAlertDialogBuilder.create()
//        mAlertDialog.show()
//
////        if(backPressedTime + 2000 > System.currentTimeMillis()) {
////            super.onBackPressed()
////        } else {
////            Toast.makeText(this, "Press back again to exit app", Toast.LENGTH_SHORT).show()
////        }
////        backPressedTime = System.currentTimeMillis()
//    }

}