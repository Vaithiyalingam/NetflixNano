package com.vaithidroid.appone.netflixnano.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.vaithidroid.appone.netflixnano.R
import com.vaithidroid.appone.netflixnano.adapter.MoviesAdapter
import com.vaithidroid.appone.netflixnano.ui.MainActivity
import com.vaithidroid.appone.netflixnano.ui.viewmodel.MoviesViewModel
import com.vaithidroid.appone.netflixnano.util.Resource
import kotlinx.android.synthetic.main.fragment_movies.*
import androidx.recyclerview.widget.RecyclerView
import com.vaithidroid.appone.netflixnano.adapter.PopularMoviesAdapter
import com.vaithidroid.appone.netflixnano.adapter.TopRatedAdapter
import com.vaithidroid.appone.netflixnano.adapter.UpcomingAdapter


class MoviesFragment : Fragment(R.layout.fragment_movies) {

    lateinit var viewModel: MoviesViewModel
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var popularMoviesAdapter: PopularMoviesAdapter
    lateinit var topRatedAdapter: TopRatedAdapter
    lateinit var upcomingAdapter: UpcomingAdapter

    val TAG = "MoviesFragment"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setUpRecyclerView()

        viewModel.nowPlayingMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    response.data?.let { moviesResponse ->
                        moviesAdapter.differ.submitList(moviesResponse.results)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured $message")
                    }
                }
            }
        })

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    response.data?.let { moviesResponse ->
                        popularMoviesAdapter.differ.submitList(moviesResponse.results)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured $message")
                    }
                }
            }
        })

        viewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    response.data?.let { moviesResponse ->
                        topRatedAdapter.differ.submitList(moviesResponse.results)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured $message")
                    }
                }
            }
        })

        viewModel.upcomingMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response){
                is Resource.Success -> {
                    response.data?.let { moviesResponse ->
                        upcomingAdapter.differ.submitList(moviesResponse.results)
                    }
                }
                is Resource.Error -> {
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured $message")
                    }
                }
            }
        })
    }

    private fun setUpRecyclerView(){
        moviesAdapter = MoviesAdapter()
        rv_now_playing_movies.apply {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        popularMoviesAdapter = PopularMoviesAdapter()
        rv_popular_movies.apply {
            adapter = popularMoviesAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        topRatedAdapter = TopRatedAdapter()
        rv_top_rated.apply {
            adapter = topRatedAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        upcomingAdapter = UpcomingAdapter()
        rv_upcoming.apply {
            adapter = upcomingAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

    }

}