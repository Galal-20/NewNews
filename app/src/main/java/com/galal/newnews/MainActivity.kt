package com.galal.newnews

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        selectTab(getString(R.string.home))
        val navController = findNavController(R.id.nav_host_fragment)
        val fadeNavOptions = androidx.navigation.navOptions {
            anim {
                enter = R.anim.fade_in
                exit = R.anim.fade_out
                popEnter = R.anim.fade_in
                popExit = R.anim.fade_out
            }
        }
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val customBottomNav = findViewById<ConstraintLayout>(R.id.customBottomNav)
            customBottomNav.visibility = View.VISIBLE
            when (destination.id) {
                R.id.homeFragment -> {
                    selectTab(getString(R.string.home))
                    window.statusBarColor = getColor(R.color.white)
                }
                R.id.favoriteFragment -> selectTab(getString(R.string.save))
                R.id.profileFragment -> selectTab(getString(R.string.profile))
                R.id.detailsFragment -> {
                    selectTab("")
                }

            }
        }

        val lastFragmentId = savedInstanceState?.getInt("LAST_FRAGMENT_ID") ?: R.id.homeFragment
        navController.navigate(lastFragmentId)


        findViewById<ConstraintLayout>(R.id.nav_home_container).setOnClickListener {
            if (navController.currentDestination?.id != R.id.homeFragment) {
                navController.navigate(R.id.homeFragment, null, fadeNavOptions)
            }
        }

        findViewById<ConstraintLayout>(R.id.nav_save_container).setOnClickListener {
            if (navController.currentDestination?.id != R.id.favoriteFragment) {
                navController.navigate(R.id.favoriteFragment, null, fadeNavOptions)
            }
        }

        findViewById<ConstraintLayout>(R.id.nav_save_c_container).setOnClickListener {
            if (navController.currentDestination?.id != R.id.profileFragment) {
                navController.navigate(R.id.profileFragment, null, fadeNavOptions)
            }
        }
    }


    fun selectTab(tab: String) {
        findViewById<TextView>(R.id.home_label).setTextColor(getColor(R.color.colorIconUnSelection))
        findViewById<ImageView>(R.id.nav_home).setColorFilter(getColor(R.color.colorIconUnSelection))

        findViewById<TextView>(R.id.save_label).setTextColor(getColor(R.color.colorIconUnSelection))
        findViewById<ImageView>(R.id.nav_save).setColorFilter(getColor(R.color.colorIconUnSelection))

        findViewById<TextView>(R.id.profile_label).setTextColor(getColor(R.color.colorIconUnSelection))
        findViewById<ImageView>(R.id.nav_profile).setColorFilter(getColor(R.color.colorIconUnSelection))

        when (tab) {
            getString(R.string.home) -> {
                findViewById<TextView>(R.id.home_label).setTextColor(getColor(R.color.colorIconSelection))
                findViewById<ImageView>(R.id.nav_home).setColorFilter(getColor(R.color.colorIconSelection))
            }
            getString(R.string.save) -> {
                findViewById<TextView>(R.id.save_label).setTextColor(getColor(R.color.colorIconSelection))
                findViewById<ImageView>(R.id.nav_save).setColorFilter(getColor(R.color
                    .colorIconSelection))
            }
            getString(R.string.profile) -> {
                findViewById<TextView>(R.id.profile_label).setTextColor(getColor(R.color.colorIconSelection))
                findViewById<ImageView>(R.id.nav_profile).setColorFilter(getColor(R.color.colorIconSelection))
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val currentFragmentId = findNavController(R.id.nav_host_fragment).currentDestination?.id
        outState.putInt("LAST_FRAGMENT_ID", currentFragmentId ?: R.id.homeFragment)
    }



}

