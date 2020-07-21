package com.innoventesmovistv.myapplication.ui.details

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.innoventesmovistv.myapplication.R
import com.innoventesmovistv.myapplication.databinding.FragmentDetailsBinding
import com.innoventesmovistv.myapplication.databinding.FragmentMoviesBinding
import com.innoventesmovistv.myapplication.ui.base.BaseFragment
import com.innoventesmovistv.myapplication.ui.bookmark.MovieDetailViewModel
import com.innoventesmovistv.myapplication.ui.model.MovieResult
import com.innoventesmovistv.myapplication.ui.model.Result
import com.innoventesmovistv.myapplication.utils.Constant
import com.innoventesmovistv.myapplication.utils.snackbar
import kotlin.math.roundToInt

class DetailsFragment : BaseFragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var binding: FragmentDetailsBinding
    private var movie: MovieResult? = null
    private var isFav: Boolean = false


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )
        arguments?.let {
            movie = it?.getParcelable("movie_details")
            checkFav()
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        var movieDetails = arguments?.getParcelable<MovieResult>("movie_details")


        binding.textTitle.text = movieDetails?.title
        val image = Constant.IMAGE_BASE_URL + Constant.IMAGE_FILE_SIZE + movieDetails?.backdrop_path
        val uri = Uri.parse(image)
        binding.imgCover.setImageURI(uri)

        val image_poster =
            Constant.IMAGE_BASE_URL + Constant.IMAGE_FILE_SIZE + movieDetails?.poster_path
        val uri_poster = Uri.parse(image_poster)
        binding.imgPoster.setImageURI(uri_poster)

        binding.textSynopsis.text = movieDetails?.overview
        binding.textTotalVotes.text = movieDetails?.vote_count.toString()
        binding.textReleaseDate.text = movieDetails?.release_date
        binding.ratingScore.rating = movieDetails?.vote_count!!.toFloat()

        binding.fabAddFavorite.setOnClickListener {
            view?.snackbar(movieDetails?.title + " Added to Faviorate ")
            if (isFav) {
                viewModel.deleteMovie(movieDetails)
                Toast.makeText(context, "Removed", Toast.LENGTH_SHORT).show()
            } else {
                viewModel.insertMovie(movieDetails)
                Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
            }
        }


    }
    private fun checkFav(){
        viewModel.getSingleMovie(movie?.movieId).observe(viewLifecycleOwner, Observer {
            if (it != null){
                binding.fabAddFavorite.setImageResource(R.drawable.my_fav)
                isFav = true
            }else{
                binding.fabAddFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                isFav = false
            }
        })
    }
}