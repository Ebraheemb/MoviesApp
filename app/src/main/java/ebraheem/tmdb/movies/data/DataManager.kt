package ebraheem.tmdb.movies.data

import ebraheem.tmdb.movies.data.model.Movie
import ebraheem.tmdb.movies.data.model.MoviesResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver

interface DataManager {

    fun getFavoriteMovies() : Flowable<List<Movie>>

    fun getPopularMovies(page: Int): Observable<MoviesResponse>

    fun getMovieDetails(movieID: Long,observer: SingleObserver<Movie>)

    fun addMovieToFavorites(movie: Movie)

    fun removeMovieFromFavorites(movie: Movie)
}
