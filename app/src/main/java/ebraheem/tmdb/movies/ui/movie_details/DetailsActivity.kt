package ebraheem.tmdb.movies.ui.movie_details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import androidx.databinding.library.baseAdapters.BR.movie
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ebraheem.tmdb.movies.R
import ebraheem.tmdb.movies.data.model.Movie
import ebraheem.tmdb.movies.databinding.ActivityDetailsBinding
import ebraheem.tmdb.movies.di.inject
import ebraheem.tmdb.movies.ui.base.BaseActivity
import ebraheem.tmdb.movies.ui.main.MainActivity
import ebraheem.tmdb.movies.ui.viewmodel.getViewModel

class DetailsActivity : BaseActivity() {

    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var mBinding: ActivityDetailsBinding
    private var favoriteCheckBox: CheckBox? = null
    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_details)
        initToolbar()


        viewModel = ViewModelProviders.of(this).get(MovieDetailsViewModel::class.java)
        viewModel.inject()
        viewModel.movieLiveData.observe(this, MovieLiveDataObserver())
        viewModel.loadMovieData(intent?.getLongExtra(EXTRA_MOVIE_ID, 0)!!)

    }


    private fun initToolbar() {
        setSupportActionBar(mBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.details_toolbar_menu, menu)
        var menuCheckBoxItem = menu.findItem(R.id.favorite_checkbox_item)
        favoriteCheckBox = menuCheckBoxItem.actionView as CheckBox
        favoriteCheckBox?.buttonDrawable = resources.getDrawable(R.drawable.favirite_checkbox_bg)
        favoriteCheckBox?.setOnCheckedChangeListener { checkBox, checked ->
            if (checked) {
                viewModel.addMovieToFavorites(movie)
            } else {
                viewModel.removeMovieFromFavorites(movie)
            }
        }
        return true
    }


    inner class MovieLiveDataObserver : Observer<Movie> {

        override fun onChanged(t: Movie?) {
            movie = t!!
            mBinding!!.movie = t
            favoriteCheckBox?.let{
                it.isChecked = movie.isFavorite
            }
        }

    }

    companion object {
        val EXTRA_MOVIE_ID = "extra_movie_id"

        fun getNewIntent(context: Context, movieID: Long): Intent =
            Intent(context, DetailsActivity::class.java)
                .putExtra(EXTRA_MOVIE_ID, movieID)

    }
}
