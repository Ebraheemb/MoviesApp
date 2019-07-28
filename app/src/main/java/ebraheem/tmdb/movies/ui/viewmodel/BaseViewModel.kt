package ebraheem.tmdb.movies.ui.viewmodel

import androidx.lifecycle.ViewModel
import ebraheem.tmdb.movies.data.DataManager
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var dataManager: DataManager

}