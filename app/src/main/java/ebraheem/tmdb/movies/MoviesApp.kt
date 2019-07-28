package ebraheem.tmdb.movies

import android.app.Application
import ebraheem.tmdb.movies.di.AppComponent
import ebraheem.tmdb.movies.di.AppModule
import ebraheem.tmdb.movies.di.DaggerAppComponent
import java.io.File

class MoviesApp : Application() {

    lateinit var component: AppComponent
        private set

    override fun onCreate() = super.onCreate().also {

        component = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

        instance = this

    }



    companion object {

        @JvmStatic
        var instance: MoviesApp? = null
            private set

    }
}