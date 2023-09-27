package com.example.fonecompany

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.fonecompany.databinding.ActivityMainBinding

class MainActivity : FragmentActivity() {

    private lateinit var navController: NavController

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment

        navController = navHostFragment.navController
        binding.bnv.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.bnv.isVisible = !fragmentsWithoutBottomNavigationView.contains(destination.id)
        }
    }

    companion object {
        private val fragmentsWithoutBottomNavigationView = arrayListOf(R.id.loginFragment, R.id.employerFragment)
    }
}