package edu.fer.porttown.ui.game

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.navigation.NavigationView
import com.mikepenz.fastadapter.adapters.FastItemAdapter
import edu.fer.porttown.BaseActivity
import edu.fer.porttown.R
import edu.fer.porttown.databinding.ActivityMainBinding
import edu.fer.porttown.model.Resource
import edu.fer.porttown.model.helpers.Resources
import edu.fer.porttown.network.NetResource
import edu.fer.porttown.network.Status
import edu.fer.porttown.viewmodels.GameViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var drawer: DrawerLayout
    private lateinit var resourceAdapter: FastItemAdapter<ResourceItem>

    private val gameViewModel: GameViewModel by viewModel { parametersOf(this.lifecycleScope) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        drawer = binding.navigationDrawer

        setContentView(binding.root)
        setupNavController()
        setupNavDrawer()
        setupToolbar()
        setupResourcesTracker()
        gameViewModel.resources.observe(this, resourceObserver)
    }

    private val resourceObserver = Observer<NetResource<List<Resource>>> {
        val newResources = when (it.status) {
            Status.LOADING -> Resources.createAllEmpty()
            Status.ERROR -> Resources.createAllEmpty() //TODO
            Status.SUCCESS -> it.data!!
        }

        resourceAdapter.itemAdapter.adapterItems.forEach { resourceItem ->
            resourceItem.resource.apply {
                setCount(
                    newResources.find { r -> r.getType() == this.getType() }!!.getCount()
                )
            }
        }
    }

    private fun setupNavController() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setupNavDrawer() {
        NavigationUI.setupWithNavController(binding.navView, navController)
        binding.navView.setNavigationItemSelectedListener(this)
        setupNavDrawerHeader()
    }

    private fun setupNavDrawerHeader() {
        val header = binding.navView.getHeaderView(0)
        val headerTownName = header.findViewById<TextView>(R.id.header_username)
        val headerUserName = header.findViewById<TextView>(R.id.header_town_name)

        headerUserName.text = sessionManager.getUserName()
        headerTownName.text = sessionManager.getTownName()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.materialToolbar)
        setupActionBarWithNavController(navController, drawer)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupResourcesTracker() {
        resourceAdapter = FastItemAdapter()
        binding.resourcesGrid.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            itemAnimator = DefaultItemAnimator()
            adapter = resourceAdapter
        }

        resourceAdapter.add(Resources.createAllEmpty().map { ResourceItem(it) })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_logout -> {
                sessionManager.logOut()
            }
            R.id.nav_town -> navController.navigate(R.id.townFragment)
            R.id.nav_settings -> navController.navigate(R.id.settingsFragment)
            R.id.nav_market -> navController.navigate(R.id.marketFragment)
            else -> throw AssertionError("Navigation item missing!")
        }

        binding.navigationDrawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.navigationDrawer)
    }

    override fun onResume() {
        super.onResume()
        gameViewModel.startGameLoop()
    }

    override fun onPause() {
        super.onPause()
        gameViewModel.syncToCloud()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}