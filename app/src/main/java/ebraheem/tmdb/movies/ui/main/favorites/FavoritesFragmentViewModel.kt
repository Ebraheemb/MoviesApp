package ebraheem.tmdb.movies.ui.main.favorites

import androidx.lifecycle.MutableLiveData
import ebraheem.tmdb.movies.data.model.Movie
import ebraheem.tmdb.movies.ui.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FavoritesFragmentViewModel : BaseViewModel(){

    val favoriteLiveData : MutableLiveData<List<Movie>> = MutableLiveData()

    fun loadFavoriteMovies() {
        dataManager.getFavoriteMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                favoriteLiveData.postValue(it)
            }
    }

}
