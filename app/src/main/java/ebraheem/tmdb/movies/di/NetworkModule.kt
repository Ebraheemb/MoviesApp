package ebraheem.tmdb.movies.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import ebraheem.tmdb.movies.MoviesApp
import ebraheem.tmdb.movies.common.Constants
import ebraheem.tmdb.movies.data.network.AuthInterceptor
import ebraheem.tmdb.movies.data.network.Routes
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit


@Module(includes = [AppModule::class])
class NetworkModule {


    @Provides
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .create()

    @Provides
    fun provideCache(cacheFile: File): Cache = Cache(cacheFile, Constants.OKHTTP_CACHE_DIR_SIZE)

    @Provides
    fun provideHttpCacheFile(app: MoviesApp): File {
        val directory = File(app.cacheDir.toString() + File.separator + Constants.OKHTTP_CACHE_DIR_NAME)
        if (!directory.exists())
            directory.mkdirs()
        return directory

    }

    @Provides
    fun provideOkHttpClient(cache: Cache, app: MoviesApp, authInterceptor: AuthInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(app.applicationContext))
            .addInterceptor(authInterceptor)
            .cache(cache)
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()


    @Provides
    fun provideAuthInterceptor(): AuthInterceptor =
        AuthInterceptor()

    @Provides
    fun RxJava2CallAdapterFactory()  = RxJava2CallAdapterFactory.create()

    @Provides
    fun getClient(okHttpClient: OkHttpClient, gson: Gson,rxJava2CallAdapterFactory: RxJava2CallAdapterFactory): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(rxJava2CallAdapterFactory)
            .build()


    @AppScope
    @Provides
    fun provideMoviesService(retrofit: Retrofit): Routes = retrofit.create(Routes::class.java)
}