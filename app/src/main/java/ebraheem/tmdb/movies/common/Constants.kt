package ebraheem.tmdb.movies.common

import ebraheem.tmdb.movies.BuildConfig

object Constants {
    val APP_DATABASE_NAME: String = "movie_app_db"
    val TMDB_API_KEY: String = BuildConfig.TMDB_API_KEY
    val API_BASE_URL: String ="https://api.themoviedb.org/3/"
    val OKHTTP_CACHE_DIR_NAME: String ="http.cache.directory"
    val OKHTTP_CACHE_DIR_SIZE: Long = 1024 * 1024 * 5

    val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/"
    val IMAGE_SIZE_W185 = "w185"
    val BACKDROP_SIZE = "w780"
    val IMAGE_URL = IMAGE_BASE_URL + IMAGE_SIZE_W185
    val BACKDROP_URL = IMAGE_BASE_URL + BACKDROP_SIZE
}
