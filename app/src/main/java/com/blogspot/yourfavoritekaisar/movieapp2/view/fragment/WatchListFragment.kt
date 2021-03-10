package com.blogspot.yourfavoritekaisar.movieapp2.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.yourfavoritekaisar.movieapp2.R
import com.blogspot.yourfavoritekaisar.movieapp2.adapter.WatchListAdapter
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.WatchListType
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.model.WatchList
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.viewModel.WatchListViewModel
import com.blogspot.yourfavoritekaisar.movieapp2.view.activity.*
import javax.inject.Inject

class WatchListFragment : Fragment() {

    private lateinit var watchList: RecyclerView
    private lateinit var watchListAdapter: WatchListAdapter
    private lateinit var filter: Spinner

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: WatchListViewModel

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_watch_list, container, false)

        viewModel = ViewModelProviders.of(this, factory).get(WatchListViewModel::class.java)

        watchList = view.findViewById(R.id.watchlist)
        watchList.layoutManager = GridLayoutManager(context, 3)
        watchListAdapter = WatchListAdapter(listOf()) {
            when (it.type) {
                is WatchListType.MovieType -> showMovieDetails(it)
                is WatchListType.TvShowType -> showTvShowDetails(it)
            }
        }
        watchList.adapter = watchListAdapter

        filter = view.findViewById(R.id.watchlist_filter)
        ArrayAdapter.createFromResource(
            context!!,
            R.array.watch_list_filter,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Apply the adapter to the spinner
            filter.adapter = adapter

        }
        return view
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.all.observe(viewLifecycleOwner, { watchlist ->
            watchListAdapter.updateItems(watchlist)
        })
        viewModel.movies.observe(viewLifecycleOwner, { watchlist ->
            watchListAdapter.updateItems(watchlist)
        })
        viewModel.tvShows.observe(viewLifecycleOwner, { watchlist ->
            watchListAdapter.updateItems(watchlist)
        })
    }

    override fun onStart() {
        super.onStart()

        filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (position) {
                    0 -> viewModel.getWatchList()
                    1 -> viewModel.getMovies()
                    2 -> viewModel.getTvShows()
                }
            }
        }
    }

    private fun showMovieDetails(item: WatchList) {
        val intent = Intent(activity, MovieDetailActivity::class.java)
        intent.putExtra(MOVIE_ID, item.id)
        intent.putExtra(MOVIE_BACKDROP, item.backdropPath)
        intent.putExtra(MOVIE_POSTER, item.posterPath)
        intent.putExtra(MOVIE_TITLE, item.title)
        intent.putExtra(MOVIE_RATING, item.rating)
        intent.putExtra(MOVIE_RELEASE_DATE, item.releaseDate)
        intent.putExtra(MOVIE_OVERVIEW, item.overview)
        startActivity(intent)
    }

    private fun showTvShowDetails(item: WatchList) {
        val intent = Intent(activity, TvShowDetailActivity::class.java)
        intent.putExtra(TV_SHOW_ID, item.id)
        intent.putExtra(TV_SHOW_BACKDROP, item.backdropPath)
        intent.putExtra(TV_SHOW_POSTER, item.posterPath)
        intent.putExtra(TV_SHOW_TITLE, item.title)
        intent.putExtra(TV_SHOW_RATING, item.rating)
        intent.putExtra(TV_SHOW_RELEASE_DATE, item.releaseDate)
        intent.putExtra(TV_SHOW_OVERVIEW, item.overview)
        startActivity(intent)
    }

    override fun onHiddenChanged(hidden: Boolean) {
        if (!hidden) {
            when (filter.selectedItemPosition) {
                0 -> viewModel.getWatchList()
                1 -> viewModel.getMovies()
                2 -> viewModel.getTvShows()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        when (filter.selectedItemPosition) {
            0 -> viewModel.getWatchList()
            1 -> viewModel.getMovies()
            2 -> viewModel.getTvShows()
        }
    }
}