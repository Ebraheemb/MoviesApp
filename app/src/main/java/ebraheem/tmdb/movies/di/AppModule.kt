package ebraheem.tmdb.movies.di

import dagger.Module
import dagger.Provides
import ebraheem.tmdb.movies.MoviesApp
import java.util.concurrent.Executor
import java.util.concurrent.Executors

@Module
class AppModule(var app: MoviesApp) {

    @AppScope
    @Provides
    fun provideApp(): MoviesApp = app


    @AppScope
    @Provides
    fun provideExecutor() : Executor = Executors.newFixedThreadPool(2)

}