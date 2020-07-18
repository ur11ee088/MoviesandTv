package com.innoventesmovistv.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.core.content.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.innoventesmovistv.myapplication.BuildConfig
import com.innoventesmovistv.myapplication.R
import com.innoventesmovistv.myapplication.databinding.FragmentMoviesBinding
import com.innoventesmovistv.myapplication.factory.MoviesViewModelFactory
import com.innoventesmovistv.myapplication.ui.base.BaseFragment
import com.innoventesmovistv.myapplication.ui.home.adapter.MoviesAdapter
import com.innoventesmovistv.myapplication.ui.model.Result
import com.innoventesmovistv.myapplication.utils.snackbar
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class MoviesFragment : BaseFragment() {

    private lateinit var homeViewModel: MoviesViewModel
    private lateinit var binding: FragmentMoviesBinding
    private val factory: MoviesViewModelFactory by instance()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this,factory).get(MoviesViewModel::class.java)
        setViewModel(homeViewModel)
        binding =  DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_movies,
            container,
            false
        )


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        searchmovie("dil")
        homeViewModel.searchMoviesResponse.observe(viewLifecycleOwner, Observer {
            view?.snackbar(it.results.toString())

            bindUI(it.results)
        })
    }

    fun searchmovie(search : String ) = launch {
        homeViewModel.getMovies(search,1, BuildConfig.API_KEY)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main, menu)
        setUpSearchViewListener(menu)
    }
    private fun setUpSearchViewListener(menu: Menu) {
        val searchItem: MenuItem = menu.findItem(R.id.menu_item_search)
        val searchView = searchItem.actionView as SearchView

        searchView.apply {
            queryHint = context.getString(R.string.query_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    /* query?.let {
                        *//* movieListViewModel.query = query
                        getMovieList()*//*
                        Log.e("keysearch","jljjkkl"+query)
                        searchmovie(query)

                    }
                    onActionViewCollapsed()
                    searchItem.collapseActionView()
                    hideSoftKeyboard()*/
                    //onActionViewCollapsed()
                    //searchItem.collapseActionView()
                    //hideSoftKeyboard()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {

                    if (newText != null && newText.length >= 3) {
                        searchmovie(newText)
                        Log.e("keysearch", "jljjkkl" + query)
                        hideSoftKeyboard()

                    }

                    return false
                }

            })

        }

    }

    fun hideSoftKeyboard() {
        view?.let {
            val imm = context?.getSystemService<InputMethodManager>()

            imm?.hideSoftInputFromWindow(
                it.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
    fun bindUI(list: List<Result>) {

        binding.recyclerView.also {
            it.setHasFixedSize(true)
            it.layoutManager
           it.adapter = list?.let {
                    it1->
               MoviesAdapter( it1)
            }
        }
    }
}