package ebraheem.tmdb.movies.ui.main.favorites

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import ebraheem.tmdb.movies.R
import ebraheem.tmdb.movies.data.model.Movie
import ebraheem.tmdb.movies.di.inject
import ebraheem.tmdb.movies.helpers.GridImageItemDecoration
import ebraheem.tmdb.movies.ui.main.MainActivity
import ebraheem.tmdb.movies.ui.main.MainActivityDelegate
import ebraheem.tmdb.movies.ui.base.BaseFragment
import ebraheem.tmdb.movies.ui.main.home.MoviesAdapter
import kotlinx.android.synthetic.main.favorites_fragment.recyclerView
import kotlinx.android.synthetic.main.favorites_fragment.swipeToRefreshLayout

class FavoritesFragment : BaseFragment() {

    private lateinit var mViewModel: FavoritesFragmentViewModel
    private var mainActivityDelegate: MainActivityDelegate? = null
    private val moviesAdapter by lazy {
        MoviesAdapter()
    }.also {
//        it.value.setOnMovieClickListener(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        initSwipeToRefresh()


    }

    override fun getIconResource() : Int = R.drawable.ic_favorite_white_24dp

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is MainActivityDelegate) {
            mainActivityDelegate = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mainActivityDelegate = null
    }

    private fun initSwipeToRefresh() {
        swipeToRefreshLayout.setOnRefreshListener {
            mViewModel.loadFavoriteMovies()
        }
    }

    private fun initRecyclerView() {
        recyclerView.addItemDecoration(GridImageItemDecoration(resources.getDimensionPixelSize(R.dimen.grid_movie_items_margin)))
        recyclerView.apply {
            adapter = moviesAdapter
        }


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mViewModel = ViewModelProviders.of(this).get(FavoritesFragmentViewModel::class.java)
        mViewModel.inject()
        mViewModel.favoriteLiveData.observe(this, FavotiesLiveDataObserver())
        mViewModel.loadFavoriteMovies()

    }

    inner class FavotiesLiveDataObserver : Observer<List<Movie>> {

        override fun onChanged(t: List<Movie>?) {
            swipeToRefreshLayout.isRefreshing = false
            moviesAdapter.movieList = t
        }

    }

    companion object {

        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

}
