package ebraheem.tmdb.movies.ui.main


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityOptionsCompat
import ebraheem.tmdb.movies.R
import ebraheem.tmdb.movies.data.model.Movie
import ebraheem.tmdb.movies.ui.base.BaseActivity
import ebraheem.tmdb.movies.ui.base.BaseFragment
import ebraheem.tmdb.movies.ui.main.favorites.FavoritesFragment
import ebraheem.tmdb.movies.ui.main.home.HomeFragment
import ebraheem.tmdb.movies.ui.movie_details.DetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.util.Pair

class MainActivity : BaseActivity(), MainActivityDelegate {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initToolbar()
        initViewPager()
    }

    private fun initViewPager() {
        var fragments: MutableList<BaseFragment> = mutableListOf()
        fragments.add(HomeFragment.newInstance())
        fragments.add(FavoritesFragment.newInstance())
        viewpager.apply {
            adapter = PagerAdapter(supportFragmentManager, 0, fragments)
        }

        tabLayout.setupWithViewPager(viewpager)
        for (i in 0..tabLayout.tabCount) {
            var tab = tabLayout.getTabAt(i)
            tab?.setIcon(fragments[i].getIconResource())
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
    }

    override fun setToolbarTitle(title: String) {
        supportActionBar?.title = title
    }


    override fun openMovieDetailsFragment(movie: Movie, posterImageView: ImageView, titleTextView: TextView) {

        DetailsActivity.getNewIntent(this, movieID = movie.id).also {

            val p1 = Pair.create<View, String>(posterImageView, getString(R.string.poster_image_transition_name))
            val p2 = Pair.create<View, String>(titleTextView, getString(R.string.title_transition_name))

            var options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, p1,p2)

            startActivity(it, options.toBundle())
        }
    }





}
