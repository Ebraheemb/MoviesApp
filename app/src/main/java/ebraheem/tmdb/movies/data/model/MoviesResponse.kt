package ebraheem.tmdb.movies.data.model

import com.google.gson.annotations.SerializedName


data class MoviesResponse (

    @field:SerializedName("page")
    var page: Int = 0,

    @field:SerializedName("total_results")
    var totalResults: Int = 0,

    @field:SerializedName("total_pages")
    var totalPages: Int = 0,

    @field:SerializedName("results")
    var movies: List<Movie>? = null
)