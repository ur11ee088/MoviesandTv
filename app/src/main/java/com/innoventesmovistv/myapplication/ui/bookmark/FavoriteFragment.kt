package com.innoventesmovistv.myapplication.ui.bookmark

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.innoventesmovistv.myapplication.R
import com.innoventesmovistv.myapplication.ui.base.BaseVMFragment
import com.innoventesmovistv.myapplication.ui.base.RecyclerViewClickListener
import com.innoventesmovistv.myapplication.ui.bookmark.adapter.FavAdapter
import com.innoventesmovistv.myapplication.ui.model.MovieResult
import kotlinx.android.synthetic.main.fragment_slideshow.*


class FavoriteFragment : BaseVMFragment<MovieDetailViewModel>(),
    RecyclerViewClickListener<MovieResult> {

    override fun getViewModel(): Class<MovieDetailViewModel> = MovieDetailViewModel::class.java

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_slideshow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getAllMovies().observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.e("movies","getmovies: "+it)
                recyclerviewFavorites.adapter = FavAdapter(it,this)
            }

        })
    }

    override fun onRecyclerViewItemClick(view: View, item: MovieResult) {
        val bundle = bundleOf("movie_details" to item)
        Navigation.findNavController(view)
            .navigate(R.id.nav_details, bundle)
    }
}
