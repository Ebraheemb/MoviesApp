package ebraheem.tmdb.movies.ui.main

import android.widget.ImageView
import android.widget.TextView
import ebraheem.tmdb.movies.data.model.Movie

interface MainActivityDelegate {

    fun openMovieDetailsFragment(movie: Movie,posterImageView : ImageView,titleTextView: TextView)

    fun setToolbarTitle(title:String)

}