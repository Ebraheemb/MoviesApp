package ebraheem.tmdb.movies.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import ebraheem.tmdb.movies.MoviesApp
import ebraheem.tmdb.movies.common.Constants
import ebraheem.tmdb.movies.data.db.AppDatabase
import ebraheem.tmdb.movies.data.db.MoviesDao


@Module(includes = [AppModule::class])
class DatabaseModule {

    @Provides
    fun provideDatabase(app: MoviesApp): AppDatabase = Room.databaseBuilder(app, AppDatabase::class.java, Constants.APP_DATABASE_NAME).build()


    @Provides
    fun provieMoviesDao(appDatabase: AppDatabase) : MoviesDao = appDatabase.moviesDao()

}
