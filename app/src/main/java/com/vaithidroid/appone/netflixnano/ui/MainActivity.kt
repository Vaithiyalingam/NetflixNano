package com.vaithidroid.appone.netflixnano.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.vaithidroid.appone.netflixnano.R
import com.vaithidroid.appone.netflixnano.db.MoviesDatabase
import com.vaithidroid.appone.netflixnano.repository.MoviesRepository
import com.vaithidroid.appone.netflixnano.ui.viewmodel.MoviesViewModel
import com.vaithidroid.appone.netflixnano.ui.viewmodel.MoviesViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MoviesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val moviesRepository = MoviesRepository(MoviesDatabase(this))
        val moviesViewModelProviderFactory = MoviesViewModelProviderFactory(application ,moviesRepository)
        viewModel = ViewModelProvider(this, moviesViewModelProviderFactory).get(MoviesViewModel::class.java)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFrag) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)

    }
}