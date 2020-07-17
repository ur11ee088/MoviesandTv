package com.innoventesmovistv.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.innoventesmovistv.myapplication.R
import com.innoventesmovistv.myapplication.databinding.FragmentMoviesBinding
import com.innoventesmovistv.myapplication.ui.base.BaseFragment

class MoviesFragment : BaseFragment() {

    private lateinit var homeViewModel: MoviesViewModel
    private lateinit var binding: FragmentMoviesBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(MoviesViewModel::class.java)
        binding =  DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movies,
            container,
            false
        )


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.textHome.text = "jsj"
    }
}