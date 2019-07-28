package ebraheem.tmdb.movies.data


import ebraheem.tmdb.movies.MoviesApp
import ebraheem.tmdb.movies.data.db.MoviesDao
import ebraheem.tmdb.movies.data.model.Movie
import ebraheem.tmdb.movies.data.model.MoviesResponse
import ebraheem.tmdb.movies.data.network.Routes
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executor

class AppDataManager(
    private var app: MoviesApp,
    private var apiService: Routes,
    private var moviesDao: MoviesDao,
    private var executor: Executor
) : DataManager {


    override fun getFavoriteMovies(): Flowable<List<Movie>> {
        return moviesDao.getAllFavorites()
    }

    override fun getPopularMovies(page: Int): Observable<MoviesResponse> {
        return apiService.getNowPlayingMovies(page)
    }


    override fun getMovieDetails(movieID: Long,observer: SingleObserver<Movie>){
        return moviesDao.getMovieById(id = movieID)
            .subscribeOn(Schedulers.io())
            .onErrorResumeNext(apiService.getMovieDetails(movieID))
            .flatMap {
                moviesDao.insert(it)
                Single.just(it)}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(observer)

    }

    override fun removeMovieFromFavorites(movie: Movie) {
        executor.execute {
            movie.isFavorite = false
            moviesDao.updateMovie(movie)
        }
    }

    override fun addMovieToFavorites(movie: Movie) {
        executor.execute {

            movie.isFavorite = true
            moviesDao.insert(movie)
            moviesDao.updateMovie(movie)
        }
    }


}
