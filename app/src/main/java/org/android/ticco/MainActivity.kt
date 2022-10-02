package org.android.ticco

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import dagger.hilt.android.AndroidEntryPoint
import org.android.ticco.databinding.ActivityMainBinding
import org.android.ticco.presentation.util.isGranted

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.permissionFragment,
                R.id.onboardingFragment,
                R.id.homeFragment,
                R.id.joinFragment,
                R.id.existingUserFragment
            )
        )

        val graphInflater = navHostFragment.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.nav_graph)

        navController = navHostFragment.navController
        val startDestination = when {
            isGranted().not() -> {
                R.id.permissionFragment
            }
            TiccoApplication.preferences.accessToken == null -> {
                R.id.onboardingFragment
            }
            else -> R.id.homeFragment
        }
        navGraph.setStartDestination(startDestination)
        navController.graph = navGraph
    }
}