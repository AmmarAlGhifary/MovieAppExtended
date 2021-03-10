package com.blogspot.yourfavoritekaisar.movieapp2.view.activity

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.blogspot.yourfavoritekaisar.movieapp2.R
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.AppDatabase
import com.blogspot.yourfavoritekaisar.movieapp2.data.model.entity.TvShowEntity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import javax.inject.Inject

const val TV_SHOW_ID = "extra_tv_show_id"
const val TV_SHOW_BACKDROP = "extra_tv_show_backdrop"
const val TV_SHOW_POSTER = "extra_tv_show_poster"
const val TV_SHOW_TITLE = "extra_tv_show_title"
const val TV_SHOW_RATING = "extra_tv_show_rating"
const val TV_SHOW_RELEASE_DATE = "extra_tv_show_release_date"
const val TV_SHOW_OVERVIEW = "extra_tv_show_overview"

class TvShowDetailActivity : AppCompatActivity() {

    private lateinit var backdrop: ImageView
    private lateinit var poster: ImageView
    private lateinit var title: TextView
    private lateinit var rating: RatingBar
    private lateinit var releaseDate: TextView
    private lateinit var overview: TextView
    private lateinit var addToWatchList: Button

    private var tvShowId = 0L
    private var tvShowBackdrop = ""
    private var tvShowPoster = ""
    private var tvShowTitle = ""
    private var tvShowRating = 0f
    private var tvShowReleaseDate = ""
    private var tvShowOverview = ""

    @Inject
    lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        backdrop = findViewById(R.id.tv_show_backdrop)
        poster = findViewById(R.id.tv_show_poster)
        title = findViewById(R.id.tv_show_title)
        rating = findViewById(R.id.tv_show_rating)
        releaseDate = findViewById(R.id.tv_show_release_date)
        overview = findViewById(R.id.tv_show_overview)
        addToWatchList = findViewById(R.id.add_to_watch_list)

        val extras = intent.extras

        if (extras != null) {
            populateDetails(extras)
        } else {
            finish()
        }
    }

    private fun populateDetails(extras: Bundle) {
        tvShowId = extras.getLong(TV_SHOW_ID)
        tvShowBackdrop = extras.getString(TV_SHOW_BACKDROP, "")
        tvShowPoster = extras.getString(TV_SHOW_POSTER, "")
        tvShowTitle = extras.getString(TV_SHOW_TITLE, "")
        tvShowRating = extras.getFloat(TV_SHOW_RATING, 0f)
        tvShowReleaseDate = extras.getString(TV_SHOW_RELEASE_DATE, "")
        tvShowOverview = extras.getString(TV_SHOW_OVERVIEW, "")

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w1280$tvShowBackdrop")
            .transform(CenterCrop())
            .into(backdrop)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w342$tvShowPoster")
            .transform(CenterCrop())
            .into(poster)

        title.text = tvShowTitle
        rating.rating = tvShowRating / 2
        releaseDate.text = tvShowReleaseDate
        overview.text = tvShowOverview

        val tvShow = getTvShow(tvShowId)

        if (tvShow == null) {
            addToWatchList.text = getString(R.string.add_to_watch_list)
        } else {
            addToWatchList.text = getString(R.string.remove_from_watch_list)
        }
    }

    override fun onStart() {
        super.onStart()

        addToWatchList.setOnClickListener {
            if (getTvShow(tvShowId) == null) {
                val entity = TvShowEntity(
                    tvShowId,
                    tvShowTitle,
                    tvShowOverview,
                    tvShowPoster,
                    tvShowBackdrop,
                    tvShowRating,
                    tvShowReleaseDate
                )
                db.tvShowDao().insert(entity)
                addToWatchList.text = getString(R.string.remove_from_watch_list)
            } else {
                db.tvShowDao().delete(tvShowId)
                addToWatchList.text = getString(R.string.add_to_watch_list)
            }
        }
    }

    private fun getTvShow(id: Long): TvShowEntity? {
        return db.tvShowDao().findById(id)
    }
}
