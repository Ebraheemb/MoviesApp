package ebraheem.tmdb.movies.data.db

import androidx.room.*
import ebraheem.tmdb.movies.data.model.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MoviesDao {

    @Query("SELECT * FROM movie")
    fun all() : List<Movie>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll( movies: Movie)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: Movie)

    @Delete
    fun delete(user: Movie)

    @Update
    fun updateMovie(movie: Movie)

    @Query("SELECT * FROM movie where is_favorite = 1 ")
    fun getAllFavorites() : Flowable<List<Movie>>

    @Query("SELECT * FROM movie where movie.id = :id")
    fun getMovieById(id: Long) : Single<Movie>
}
