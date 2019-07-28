package ebraheem.tmdb.movies.ui.main.home

import androidx.lifecycle.MutableLiveData
import ebraheem.tmdb.movies.data.model.MoviesResponse
import ebraheem.tmdb.movies.ui.viewmodel.BaseViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeFragmentViewModel : BaseViewModel() {


    var moviesLiveData: MutableLiveData<MoviesResponse> = MutableLiveData()
    var moreMoviesLiveData: MutableLiveData<MoviesResponse> = MutableLiveData()


    fun loadData() {
        dataManager.getPopularMovies(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MoviesResponse> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: MoviesResponse) {
                    moviesLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {

                }

            })

    }

    fun loadMoreMovies(page : Int){
        dataManager.getPopularMovies(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<MoviesResponse> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: MoviesResponse) {
                    moreMoviesLiveData.postValue(t)
                }

                override fun onError(e: Throwable) {

                }

            })
    }


}
