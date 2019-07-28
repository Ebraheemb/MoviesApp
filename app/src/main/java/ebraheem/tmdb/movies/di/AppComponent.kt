package ebraheem.tmdb.movies.di

import dagger.Component
import ebraheem.tmdb.movies.data.DataManager
import ebraheem.tmdb.movies.ui.main.MainActivity
import ebraheem.tmdb.movies.ui.main.home.HomeFragment
import ebraheem.tmdb.movies.ui.viewmodel.BaseViewModel

@AppScope
@Component(modules = [AppModule::class, DataModule::class,NetworkModule::class])
interface AppComponent {


    fun dataManager(): DataManager

    fun inject(mainActivity: MainActivity)

    fun inject(mainActivity: HomeFragment)

    fun inject(baseViewModel: BaseViewModel)

}