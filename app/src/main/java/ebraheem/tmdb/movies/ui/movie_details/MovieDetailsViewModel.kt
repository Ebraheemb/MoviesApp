package ebraheem.tmdb.movies.ui.movie_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import ebraheem.tmdb.movies.data.model.Movie
import ebraheem.tmdb.movies.ui.viewmodel.BaseViewModel
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MovieDetailsViewModel : BaseViewModel() {

    val movieLiveData: MutableLiveData<Movie> = MutableLiveData()

    fun loadMovieData(movieID: Long) {
        dataManager.getMovieDetails(movieID, observer = object : SingleObserver<Movie> {
            override fun onSuccess(movie: Movie) {
                movieLiveData.postValue(movie)
            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onError(e: Throwable) {
            }

        })
    }

    fun addMovieToFavorites(movie: Movie) {
        dataManager.addMovieToFavorites(movie)
    }

    fun removeMovieFromFavorites(movie: Movie) {
        dataManager.removeMovieFromFavorites(movie)
    }

}
