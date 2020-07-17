package com.innoventesmovistv.myapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.innoventesmovistv.myapplication.R
import com.innoventesmovistv.myapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: MoviesViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(MoviesViewModel::class.java)
        binding =  DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        //binding = DataBindingUtil.inflater.inflate(R.layout.fragment_home, container, false)
       // val textView: TextView = root.findViewById(R.id.text_home)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.textHome.text = "jsj"
    }
}