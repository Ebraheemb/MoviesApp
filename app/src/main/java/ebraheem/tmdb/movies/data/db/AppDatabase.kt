package ebraheem.tmdb.movies.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import ebraheem.tmdb.movies.data.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}