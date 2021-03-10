package com.blogspot.yourfavoritekaisar.movieapp2.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.yourfavoritekaisar.movieapp2.R
import com.blogspot.yourfavoritekaisar.movieapp2.view.fragment.MovieFragment
import com.blogspot.yourfavoritekaisar.movieapp2.view.fragment.TvShowFragment
import com.blogspot.yourfavoritekaisar.movieapp2.view.fragment.WatchListFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

private const val MOVIES_FRAGMENT = "movies_fragment"
private const val TV_SHOWS_FRAGMENT = "tv_shows_fragment"
private const val WATCH_LIST_FRAGMENT = "watch_list_fragment"

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavView = findViewById(R.id.bottom_navigation_view)
        bottomNavView.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.movies -> showMoviesFragment()
                R.id.shows -> showTvShowsFragment()
                R.id.watchlist -> showWatchListFragment()
            }

            return@setOnNavigationItemSelectedListener true
        }

        showMoviesFragment()
    }

    private fun showMoviesFragment() {
        val transaction = supportFragmentManager.beginTransaction()

        val fragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val tvShowsFragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        val watchListFragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)

        tvShowsFragment?.let { transaction.hide(it) }
        watchListFragment?.let { transaction.hide(it) }

        if (fragment == null) {
            transaction.add(R.id.fragment_container, MovieFragment(), MOVIES_FRAGMENT)
        } else {
            transaction.show(fragment)
        }

        transaction.commit()
    }

    private fun showTvShowsFragment() {
        val transaction = supportFragmentManager.beginTransaction()

        val fragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)
        val moviesFragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val watchListFragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)

        moviesFragment?.let { transaction.hide(it) }
        watchListFragment?.let { transaction.hide(it) }

        if (fragment == null) {
            transaction.add(R.id.fragment_container, TvShowFragment(), TV_SHOWS_FRAGMENT)
        } else {
            transaction.show(fragment)
        }

        transaction.commit()
    }

    private fun showWatchListFragment() {
        val transaction = supportFragmentManager.beginTransaction()

        val fragment = supportFragmentManager.findFragmentByTag(WATCH_LIST_FRAGMENT)
        val moviesFragment = supportFragmentManager.findFragmentByTag(MOVIES_FRAGMENT)
        val tvShowsFragment = supportFragmentManager.findFragmentByTag(TV_SHOWS_FRAGMENT)

        moviesFragment?.let { transaction.hide(it) }
        tvShowsFragment?.let { transaction.hide(it) }

        if (fragment == null) {
            transaction.add(R.id.fragment_container, WatchListFragment(), WATCH_LIST_FRAGMENT)
        } else {
            transaction.show(fragment)
        }

        transaction.commit()
    }
}