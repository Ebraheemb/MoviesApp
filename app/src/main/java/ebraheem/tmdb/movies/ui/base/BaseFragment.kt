package ebraheem.tmdb.movies.ui.base

import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.fragment.app.Fragment
import ebraheem.tmdb.movies.R
import ebraheem.tmdb.movies.data.model.Movie


open class BaseFragment : Fragment(){



    @DrawableRes
    open fun getIconResource() : Int{
        return R.drawable.ic_home_white_24dp
    }

    open fun onMovieClick(movie: Movie?, imageView: ImageView) {}
}
