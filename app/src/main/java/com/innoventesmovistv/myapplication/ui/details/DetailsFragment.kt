package com.innoventesmovistv.myapplication.ui.details

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.innoventesmovistv.myapplication.R
import com.innoventesmovistv.myapplication.databinding.FragmentDetailsBinding
import com.innoventesmovistv.myapplication.databinding.FragmentMoviesBinding
import com.innoventesmovistv.myapplication.ui.base.BaseFragment
import com.innoventesmovistv.myapplication.ui.model.Result
import com.innoventesmovistv.myapplication.utils.Constant

class DetailsFragment : BaseFragment() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var movieDetails: Result



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel =
                ViewModelProvider(this).get(DetailsViewModel::class.java)
        binding =  DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        movieDetails = (arguments?.getSerializable(Constant.KEY_DETAILS) as Result?)!!

            binding.detailTitle.text = movieDetails.title
        val image = Constant.IMAGE_BASE_URL + Constant.IMAGE_FILE_SIZE + movieDetails.backdrop_path
            val uri = Uri.parse(image)
            binding.detailPoster.setImageURI(uri)
        binding.detailOverview.text = movieDetails.overview
        binding.vote.text = movieDetails.vote_count.toString()
        binding.releaseDate.text = movieDetails.release_date






    }
}