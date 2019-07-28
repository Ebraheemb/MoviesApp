package ebraheem.tmdb.movies.di

import dagger.Module
import dagger.Provides
import ebraheem.tmdb.movies.MoviesApp
import ebraheem.tmdb.movies.data.AppDataManager
import ebraheem.tmdb.movies.data.DataManager
import ebraheem.tmdb.movies.data.db.MoviesDao
import ebraheem.tmdb.movies.data.network.Routes
import java.util.concurrent.Executor


@Module(includes = [AppModule::class, NetworkModule::class, DatabaseModule::class])
class DataModule {

    @AppScope
    @Provides
    fun provideAppDataManager(app:MoviesApp, apiService: Routes,moviesDao: MoviesDao,executor : Executor): DataManager =
        AppDataManager(app, apiService,moviesDao,executor)


}