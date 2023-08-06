package com.example.sushitestapp.presentor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.sushitestapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var navController: NavController? = null
    private var appBarConfiguration: AppBarConfiguration? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navController =
            (supportFragmentManager.findFragmentById(R.id.navHost) as NavHostFragment).navController

        appBarConfiguration = AppBarConfiguration(navController!!.graph)
        setupActionBarWithNavController(navController!!, appBarConfiguration!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController!!.navigateUp(appBarConfiguration!!)
                || super.onSupportNavigateUp()
    }
}