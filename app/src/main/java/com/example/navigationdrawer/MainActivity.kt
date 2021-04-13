package com.example.navigationdrawer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.example.navigationdrawer.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        // Inflate layout
        setContentView(R.layout.activity_main)

        // Set action bar
        setSupportActionBar(findViewById(R.id.toolbar))

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        appBarConfiguration = AppBarConfiguration(
            // these primary destinations will not display an up arrow when they are selected as they are at the top level.
            setOf(
                R.id.nav_archive,
                R.id.nav_bin,
                R.id.nav_home,
                R.id.nav_favorites,
                R.id.nav_recent
                // Which drawer layout to be display when hamburger menu is selected.
            ), findViewById(R.id.drawer_layout)
        )

        // This sets up the app bar with the navigation graph so that
        // any changes that are made to the destinations are reflected in the app bar
        setupActionBarWithNavController(navController, appBarConfiguration)

        // specifies the item within the navigation drawer that
        // should be highlighted when the user clicks on it
        findViewById<NavigationView>(R.id.nav_view)?.setupWithNavController(navController)
    }

    //  handles pressing the up button for the secondary destination,
    //  ensuring that it goes back to its parent primary destination
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // add the overflow menu to the app bar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    // handles what to do when the item is selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        // navigate to the destination within the navigation graph
        return item.onNavDestinationSelected(navController)
    }
}










