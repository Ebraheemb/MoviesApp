package ebraheem.tmdb.movies.ui.main.home

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import ebraheem.tmdb.movies.R
import ebraheem.tmdb.movies.data.model.Movie
import ebraheem.tmdb.movies.data.model.MoviesResponse
import ebraheem.tmdb.movies.di.inject
import ebraheem.tmdb.movies.helpers.EndlessRecyclerViewScrollListener
import ebraheem.tmdb.movies.helpers.GridImageItemDecoration
import ebraheem.tmdb.movies.ui.main.MainActivityDelegate
import ebraheem.tmdb.movies.ui.base.BaseFragment
import kotlinx.android.synthetic.main.home_fragment.*

class HomeFragment : BaseFragment(), OnMovieClickListener {



    companion object {

        private const val SAVED_INSTANCE_STATE_DATA: String = "saved_instance_state_data_list"
        fun newInstance() = HomeFragment()
    }


    private var mainActivityDelegate: MainActivityDelegate? = null
    private lateinit var loadMoreImagesListener: LoadMoreImagesListener
    private lateinit var viewModel: HomeFragmentViewModel
    private val moviesAdapter by lazy {
        MoviesAdapter()
    }.also {
        it.value.setOnMovieClickListener(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retainInstance = true
        setHasOptionsMenu(true)
        initRecyclerView()
        initSwipeToRefresh()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                return true
            }
            R.id.settings -> {
                //..
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


    override fun getIconResource() : Int = R.drawable.ic_home_white_24dp

    override fun onCreateOptionsMenu (menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.home_toolbar_menu, menu)

    }

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
            viewModel.loadData()
        }
    }

    private fun initRecyclerView() {
        recyclerView.addItemDecoration(GridImageItemDecoration(resources.getDimensionPixelSize(R.dimen.grid_movie_items_margin)))
        recyclerView.apply {
            adapter = moviesAdapter
        }
        loadMoreImagesListener = LoadMoreImagesListener(recyclerView.layoutManager as GridLayoutManager)
        recyclerView.addOnScrollListener(loadMoreImagesListener)

    }

    override fun onSaveInstanceState(outState: Bundle) {

        var data = viewModel.moviesLiveData.value
        val gson = Gson()
        val dataJSON = gson.toJson(data)
        outState.putString(SAVED_INSTANCE_STATE_DATA, dataJSON)
        super.onSaveInstanceState(outState)
    }

    override fun onMovieClick(movie: Movie?,imageView: ImageView,titleTextView: TextView) {
        mainActivityDelegate!!.openMovieDetailsFragment(movie!!,imageView,titleTextView)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
        viewModel.inject()
        viewModel.moviesLiveData.observe(this, MoviesResponseLiveDataObserver())
        viewModel.moreMoviesLiveData.observe(this, MoreMoviesLiveDataObserver())

        if (savedInstanceState == null) {
            viewModel.loadData()
        } else {
            val gson = Gson()

            var dataJSON = savedInstanceState.getString(SAVED_INSTANCE_STATE_DATA)
            var data: MoviesResponse = gson.fromJson(dataJSON, MoviesResponse::class.java)
            viewModel.moviesLiveData.postValue(data)

        }
    }



    inner class LoadMoreImagesListener(layoutManager: GridLayoutManager) :
        EndlessRecyclerViewScrollListener(layoutManager) {

        override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView) {
            recyclerView.post {
                viewModel.loadMoreMovies(page)
            }
        }
    }


    inner class MoviesResponseLiveDataObserver : Observer<MoviesResponse> {

        override fun onChanged(t: MoviesResponse?) {

            swipeToRefreshLayout.isRefreshing = false
            loadMoreImagesListener.resetState()
            moviesAdapter.clearList()
            moviesAdapter.setMovieList(t?.movies)
        }

    }

    inner class MoreMoviesLiveDataObserver : Observer<MoviesResponse> {

        override fun onChanged(t: MoviesResponse?) {
            recyclerView.post { moviesAdapter.addMovies(t?.movies) }

        }

    }

}
