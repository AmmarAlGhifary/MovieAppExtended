@file:Suppress("DEPRECATION")

package com.blogspot.yourfavoritekaisar.movieapp2.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.blogspot.yourfavoritekaisar.movieapp2.R
import com.blogspot.yourfavoritekaisar.movieapp2.adapter.TvShowAdapter
import com.blogspot.yourfavoritekaisar.movieapp2.appComponent
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.dataModel.model.TvShow
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.viewModel.TvShowsViewModel
import com.blogspot.yourfavoritekaisar.movieapp2.view.activity.*
import javax.inject.Inject


class TvShowFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var viewModel: TvShowsViewModel

    private lateinit var popularTvShows: RecyclerView
    private lateinit var popularTvShowsAdapter: TvShowAdapter
    private lateinit var popularTvShowsLayoutMgr: LinearLayoutManager

    private lateinit var topRatedTvShows: RecyclerView
    private lateinit var topRatedTvShowsAdapter: TvShowAdapter
    private lateinit var topRatedTvShowsLayoutMgr: LinearLayoutManager

    private lateinit var onAirTvShows: RecyclerView
    private lateinit var onAirTvShowsAdapter: TvShowAdapter
    private lateinit var onAirTvShowsLayoutMgr: LinearLayoutManager

    private var onAirTvShowsPage = 1
    private var topRatedTvShowsPage = 1
    private var popularTvShowsPage = 1

    @Suppress("DEPRECATION")
    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(TvShowsViewModel::class.java)
    }

    @Suppress("DEPRECATION")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tv_show, container, false)

        viewModel = ViewModelProviders.of(this, factory).get(TvShowsViewModel::class.java)

        popularTvShows = view.findViewById(R.id.popular_tv_shows)
        popularTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        popularTvShows.layoutManager = popularTvShowsLayoutMgr
        popularTvShowsAdapter =
            TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        popularTvShows.adapter = popularTvShowsAdapter

        topRatedTvShows = view.findViewById(R.id.top_rated_tv_shows)
        topRatedTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        topRatedTvShows.layoutManager = topRatedTvShowsLayoutMgr
        topRatedTvShowsAdapter =
            TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        topRatedTvShows.adapter = topRatedTvShowsAdapter

        onAirTvShows = view.findViewById(R.id.on_air_tv_shows)
        onAirTvShowsLayoutMgr = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        onAirTvShows.layoutManager = onAirTvShowsLayoutMgr
        onAirTvShowsAdapter =
            TvShowAdapter(mutableListOf()) { tvShow -> showTvShowDetails(tvShow) }
        onAirTvShows.adapter = onAirTvShowsAdapter

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.popularTvShows.observe(viewLifecycleOwner, { tvShows ->
            popularTvShowsAdapter.appendTvShows(tvShows)
            attachPopularTvShowsOnScrollListener()
        })
        viewModel.topRatedTvShows.observe(viewLifecycleOwner, { tvShows ->
            topRatedTvShowsAdapter.appendTvShows(tvShows)
            attachTopRatedTvShowsOnScrollListener()
        })
        viewModel.onAirTvShows.observe(viewLifecycleOwner, { tvShows ->
            onAirTvShowsAdapter.appendTvShows(tvShows)
            attachOnAirTvShowsOnScrollListener()
        })
        viewModel.error.observe(viewLifecycleOwner, { onError() })
    }


    private fun attachPopularTvShowsOnScrollListener() {
        popularTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = popularTvShowsLayoutMgr.itemCount
                val visibleItemCount = popularTvShowsLayoutMgr.childCount
                val firstVisibleItem = popularTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    popularTvShows.removeOnScrollListener(this)
                    popularTvShowsPage++
                    viewModel.getPopularTvShows(popularTvShowsPage)
                }
            }
        })

    }

    private fun attachTopRatedTvShowsOnScrollListener() {
        topRatedTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = topRatedTvShowsLayoutMgr.itemCount
                val visibleItemCount = topRatedTvShowsLayoutMgr.childCount
                val firstVisibleItem = topRatedTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    topRatedTvShows.removeOnScrollListener(this)
                    topRatedTvShowsPage++
                    viewModel.getTopRatedTvShows(topRatedTvShowsPage)
                }
            }
        })
    }

    private fun attachOnAirTvShowsOnScrollListener() {
        onAirTvShows.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount = onAirTvShowsLayoutMgr.itemCount
                val visibleItemCount = onAirTvShowsLayoutMgr.childCount
                val firstVisibleItem = onAirTvShowsLayoutMgr.findFirstVisibleItemPosition()
                if (firstVisibleItem + visibleItemCount >= totalItemCount / 2) {
                    onAirTvShows.removeOnScrollListener(this)
                    onAirTvShowsPage++
                    viewModel.getOnAirTvShows(onAirTvShowsPage)
                }
            }
        })
    }


    private fun showTvShowDetails(tvShow: TvShow) {
        val intent = Intent(activity, TvShowDetailActivity::class.java)
        intent.putExtra(TV_SHOW_ID, tvShow.id)
        intent.putExtra(TV_SHOW_BACKDROP, tvShow.backdropPath)
        intent.putExtra(TV_SHOW_POSTER, tvShow.posterPath)
        intent.putExtra(TV_SHOW_TITLE, tvShow.name)
        intent.putExtra(TV_SHOW_RATING, tvShow.rating)
        intent.putExtra(TV_SHOW_RELEASE_DATE, tvShow.firstAirDate)
        intent.putExtra(TV_SHOW_OVERVIEW, tvShow.overview)
        startActivity(intent)
    }

    private fun onError() {
        Toast.makeText(activity, getString(R.string.error_fetch_movies), Toast.LENGTH_SHORT).show()
    }
}