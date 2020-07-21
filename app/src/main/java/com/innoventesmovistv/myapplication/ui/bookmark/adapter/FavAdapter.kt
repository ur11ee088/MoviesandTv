package com.innoventesmovistv.myapplication.ui.bookmark.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.innoventesmovistv.myapplication.R
import com.innoventesmovistv.myapplication.databinding.ItemMovieBinding
import com.innoventesmovistv.myapplication.databinding.MovieListItemBinding
import com.innoventesmovistv.myapplication.ui.base.RecyclerViewClickListener
import com.innoventesmovistv.myapplication.ui.model.MovieResult
import com.innoventesmovistv.myapplication.ui.model.Result
import com.innoventesmovistv.myapplication.utils.RecyclerViewHolder


class FavAdapter(private val listItem: List<MovieResult>,
                 private val listener: RecyclerViewClickListener<MovieResult>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = RecyclerViewHolder(
        DataBindingUtil.inflate<ItemMovieBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
    )


    override fun getItemCount() = listItem.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding: ItemMovieBinding =
            (holder as RecyclerViewHolder<*>).binding as ItemMovieBinding
        binding.movie = listItem[position]
        binding.crdMovie.setOnClickListener {
            listener.onRecyclerViewItemClick(it, listItem[position])
        }
        binding.executePendingBindings()
    }
}