package com.innoventesmovistv.myapplication.ui.base

import android.view.View

interface RecyclerViewClickListener<T> {
    fun onRecyclerViewItemClick(view: View, item: T)
}
interface RecyclerViewItemClickListener<Int> {
    fun onRecyclerViewItemClick(postion: Int)
}