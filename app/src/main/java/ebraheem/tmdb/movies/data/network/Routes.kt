package ebraheem.tmdb.movies.data.network

import androidx.lifecycle.LiveData
import ebraheem.tmdb.movies.data.model.Movie
import ebraheem.tmdb.movies.data.model.MoviesResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface Routes {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Call<MoviesResponse>

    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("page") page: Int): Call<MoviesResponse>

    @GET("movie/now_playing")
    fun getNowPlayingMovies(@Query("page") page: Int): Observable<MoviesResponse>

    @GET("movie/{id}")
    fun getMovieDetails(@Path("id") id: Long): Single<Movie>

}
