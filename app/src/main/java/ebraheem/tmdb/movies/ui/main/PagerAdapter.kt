package ebraheem.tmdb.movies.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ebraheem.tmdb.movies.ui.base.BaseFragment

class PagerAdapter(fm: FragmentManager, behavior: Int, private val fragments: List<BaseFragment>) :
    FragmentStatePagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int = fragments.size

}
