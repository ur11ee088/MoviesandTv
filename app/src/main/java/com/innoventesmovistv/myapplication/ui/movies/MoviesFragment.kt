package com.innoventesmovistv.myapplication.ui.movies

import android.app.SearchManager
import android.database.Cursor
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import androidx.appcompat.widget.SearchView
import androidx.core.content.getSystemService
import androidx.core.os.bundleOf
import androidx.cursoradapter.widget.CursorAdapter
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.innoventesmovistv.myapplication.BuildConfig
import com.innoventesmovistv.myapplication.R
import com.innoventesmovistv.myapplication.dao.Word
import com.innoventesmovistv.myapplication.dao.WordViewModel
import com.innoventesmovistv.myapplication.databinding.FragmentMoviesBinding
import com.innoventesmovistv.myapplication.factory.MoviesViewModelFactory
import com.innoventesmovistv.myapplication.ui.base.BaseFragment
import com.innoventesmovistv.myapplication.ui.base.RecyclerViewClickListener
import com.innoventesmovistv.myapplication.ui.movies.adapter.MoviesAdapter
import com.innoventesmovistv.myapplication.ui.model.Result
import com.innoventesmovistv.myapplication.utils.Constant.KEY_DETAILS
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class MoviesFragment : BaseFragment(), RecyclerViewClickListener<Result> {

    private lateinit var homeViewModel: MoviesViewModel
    private lateinit var binding: FragmentMoviesBinding
    private lateinit var wordViewModel: WordViewModel

    private val factory: MoviesViewModelFactory by instance()
    val suggestionskey = ArrayList<String>()




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this,factory).get(MoviesViewModel::class.java)
        setViewModel(homeViewModel)
        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)

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
        binding.progressLoading.visibility = View.VISIBLE
        searchmovie("dil")
        homeViewModel.searchMoviesResponse.observe(viewLifecycleOwner, Observer {
           // view?.snackbar(it.results.toString())

            bindUI(it.results)
            binding.progressLoading.visibility = View.GONE

        })

        wordViewModel.allWords.observe(viewLifecycleOwner, Observer { it ->
            for (i in it){
                suggestionskey.add(i.word)

            }

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
        searchView.findViewById<AutoCompleteTextView>(R.id.search_src_text).threshold = 1

        val from = arrayOf(SearchManager.SUGGEST_COLUMN_TEXT_1)
        val to = intArrayOf(R.id.item_label)
        val cursorAdapter = SimpleCursorAdapter(context, R.layout.search_item, null, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER)

        searchView.suggestionsAdapter = cursorAdapter

        searchView.apply {
            queryHint = context.getString(R.string.query_hint)
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    val cursor = MatrixCursor(arrayOf(BaseColumns._ID, SearchManager.SUGGEST_COLUMN_TEXT_1))

                    suggestionskey.forEachIndexed { index, suggestion ->
                        if ( true)
                            cursor.addRow(arrayOf(index, suggestion))
                    }
                    if (newText != null && newText.length >= 3) {
                        searchmovie(newText)
                        binding.progressLoading.visibility = View.VISIBLE
                        val word = Word(newText)
                        wordViewModel.insert(word)

                        Log.e("keysearch", "jljjkkl" + query)
                        hideSoftKeyboard()

                    }

                    cursorAdapter.changeCursor(cursor)

                    return false
                }

            })

        }
        searchView.setOnSuggestionListener(object: SearchView.OnSuggestionListener {
            override fun onSuggestionSelect(position: Int): Boolean {
                return false
            }

            override fun onSuggestionClick(position: Int): Boolean {
                val cursor = searchView.suggestionsAdapter.getItem(position) as Cursor
                val selection = cursor.getString(cursor.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1))
                searchView.setQuery(selection, false)

                return true
            }

        })

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
               MoviesAdapter( it1,this)
            }
        }
    }

    override fun onRecyclerViewItemClick(view: View, item: Result) {
        val bundle = bundleOf(KEY_DETAILS to item)
        findNavController().navigate(R.id.nav_details, bundle)
    }

}