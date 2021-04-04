package com.financas_simples_android.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.financas_simples_android.R
import com.financas_simples_android.ui.fragment.OverviewFragment
import com.google.android.material.navigation.NavigationView
import android.content.res.Configuration as Configuration1


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var mDrawer: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    lateinit var nvView: NavigationView

    private var drawerToggle: ActionBarDrawerToggle? = null
    private var fragmentManager: FragmentManager? = null
    private var fragmentTransaction: FragmentTransaction? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Set a Toolbar to replace the ActionBar.
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // This will display an Up icon (<-), we will replace it with hamburger later
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Find our drawer view
        mDrawer = findViewById(R.id.drawer_layout)
        drawerToggle = setupDrawerToggle()

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle!!.isDrawerIndicatorEnabled = true
        drawerToggle!!.syncState()
        mDrawer!!.addDrawerListener(drawerToggle!!)

        nvView = findViewById(R.id.nvView)
        nvView.setNavigationItemSelectedListener(this)

        fragmentManager = this.supportFragmentManager
        fragmentTransaction = fragmentManager!!.beginTransaction()

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.container_root, OverviewFragment.newInstance(),
                    "OverviewFragment")
                .commit()
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle!!.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration1) {
        super.onConfigurationChanged(newConfig)
        // Pass any configuration change to the drawer toggles
        drawerToggle!!.onConfigurationChanged(newConfig)
    }

    @JvmName("onOptionsItemSelected1")
    fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (drawerToggle!!.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item!!)
    }

    private fun setupDrawerToggle(): ActionBarDrawerToggle {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return ActionBarDrawerToggle(
            this,
            mDrawer,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // The action bar home/up action should open or close the drawer.
        when (item.itemId) {
            android.R.id.home -> {
                mDrawer!!.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @SuppressWarnings("StatementWithEmptyBody")
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        this.mDrawer?.closeDrawer(GravityCompat.START)
        return true
    }
}