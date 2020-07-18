package com.innoventesmovistv.myapplication.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.innoventesmovistv.myapplication.R
import com.innoventesmovistv.myapplication.databinding.MovieListItemBinding
import com.innoventesmovistv.myapplication.ui.model.Result
import com.innoventesmovistv.myapplication.utils.RecyclerViewHolder


class MoviesAdapter(private val listItem: List<Result>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecyclerViewHolder(
        DataBindingUtil.inflate<MovieListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.movie_list_item,
            parent,
            false
        )
    )


    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding : MovieListItemBinding =
            (holder as RecyclerViewHolder<*>).binding as MovieListItemBinding

        binding.movieTitle.text = listItem[position].title


        binding.executePendingBindings()
    }
}