package ebraheem.tmdb.movies.ui.main.home;

import android.widget.ImageView;
import android.widget.TextView;
import ebraheem.tmdb.movies.data.model.Movie;

public interface OnMovieClickListener {
    void onMovieClick(Movie movie, ImageView posterImageView, TextView titleTextView);
}
