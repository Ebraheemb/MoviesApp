package ebraheem.tmdb.movies.ui.movie_details

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.CheckBox
import androidx.lifecycle.Observer

import ebraheem.tmdb.movies.data.model.Movie
import ebraheem.tmdb.movies.databinding.MovieDetailsFragmentBinding
import ebraheem.tmdb.movies.di.inject
import ebraheem.tmdb.movies.ui.base.BaseFragment
import ebraheem.tmdb.movies.R
import ebraheem.tmdb.movies.ui.main.MainActivity


class MovieDetailsFragment : BaseFragment() {

    companion object {
        private const val EXTRA_MOVIE_ID = "extra_movie_id"

        fun newInstance(movieID: Long): MovieDetailsFragment {

            var fragment = MovieDetailsFragment()
            var bundle = Bundle()
            bundle.putLong(EXTRA_MOVIE_ID, movieID)
            fragment.arguments = bundle

            return fragment
        }
    }


    private lateinit var movie: Movie
    private var viewBinding: MovieDetailsFragmentBinding? = null

    private lateinit var viewModel: MovieDetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = MovieDetailsFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        initToolbar()
        return viewBinding!!.root
    }



    private fun initToolbar() {
        (activity as? MainActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as? MainActivity)?.supportActionBar?.setHomeButtonEnabled(true)
    }




    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var movieID: Long = arguments!!.getLong(EXTRA_MOVIE_ID)


    }


}
