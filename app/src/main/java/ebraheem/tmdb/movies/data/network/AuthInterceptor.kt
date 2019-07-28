package ebraheem.tmdb.movies.data.network

import ebraheem.tmdb.movies.common.Constants
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import java.io.IOException

class AuthInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        val url = request.url().newBuilder()
            .addQueryParameter("api_key", Constants.TMDB_API_KEY)
            .build()

        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
