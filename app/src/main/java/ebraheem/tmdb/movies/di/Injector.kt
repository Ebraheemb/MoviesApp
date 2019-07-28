package ebraheem.tmdb.movies.di

import ebraheem.tmdb.movies.MoviesApp
import ebraheem.tmdb.movies.ui.viewmodel.BaseViewModel


fun BaseViewModel.inject()  = MoviesApp.instance!!.component.inject(this)
