package com.financas_simples_android.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.financas_simples_android.R
import com.financas_simples_android.adapter.MovementsAdapter
import com.financas_simples_android.model.response.MovementResponse
import com.financas_simples_android.service.ApiService
import com.financas_simples_android.service.MovementService
import com.google.android.material.navigation.NavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.res.Configuration as Configuration1


class HomeActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var movementAdapter: MovementsAdapter
    private var mDrawer: DrawerLayout? = null
    private var toolbar: Toolbar? = null
    private val nvDrawer: NavigationView? = null

    private var drawerToggle: ActionBarDrawerToggle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Set a Toolbar to replace the ActionBar.
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // This will display an Up icon (<-), we will replace it with hamburger later
        supportActionBar?.setDisplayHomeAsUpEnabled(true);

        // Find our drawer view
        mDrawer = findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle!!.isDrawerIndicatorEnabled = true;
        drawerToggle!!.syncState();
        mDrawer!!.addDrawerListener(drawerToggle!!);

        recyclerView = findViewById(R.id.rv_last_movement)

        getMovements()
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

    private fun getMovements() {

        val request = ApiService.buildService(MovementService::class.java)
        val call = request.getMovements()

        call.enqueue(object : Callback<List<MovementResponse>> {
            override fun onResponse(
                call: Call<List<MovementResponse>>,
                response: Response<List<MovementResponse>>
            ) {
                if (response.isSuccessful) {

                    val body = response.body()
                    movementAdapter = body?.let { MovementsAdapter(it) }!!

                    recyclerView.apply {

                        layoutManager = LinearLayoutManager(this@HomeActivity)
                        recyclerView.layoutManager = layoutManager
                        setHasFixedSize(true)
                        adapter = movementAdapter
                    }
                }
            }

            override fun onFailure(call: Call<List<MovementResponse>>, t: Throwable) {
                Toast.makeText(this@HomeActivity, "deu ruim", Toast.LENGTH_LONG).show()
            }
        })
    }
}