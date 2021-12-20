package com.assesment.taskmo.movies.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.assesment.taskmo.R
import com.assesment.taskmo.base.BaseFragment
import com.assesment.taskmo.movies.data.Movie
import com.assesment.taskmo.utils.commons.GridSpacingItemDecoration
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider



class MoviesFragment : BaseFragment<MoviesViewModel>(), MovieAdapter.OnItemClick, (CombinedLoadStates) -> Unit, SearchView.OnQueryTextListener {

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mGridLayoutManager: Provider<GridLayoutManager>

    @Inject
    lateinit var mHorizontallayoutmanger : Provider<GridLayoutManager>

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    @Inject
    lateinit var mMovieAdapter: MovieAdapter

    override fun getLayoutId(): Int = R.layout.fragment_movies
    override fun getLifeCycleOwner(): LifecycleOwner = this

    override val viewModel by lazy {
        ViewModelProvider(requireActivity(), mViewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.search_menu, menu);
        val mSearchMenuItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView: SearchView = mSearchMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        searchView.queryHint = resources.getString(R.string.search_placeholder)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        mGridLayoutManager.get()?.let {
            it.reverseLayout = false
            it.isItemPrefetchEnabled = false
            moviesRecycler.layoutManager = it
        }
        moviesRecycler.apply {
            setHasFixedSize(true)
            addItemDecoration(mGridSpacingItemDecoration)
            itemAnimator = DefaultItemAnimator()
            mMovieAdapter.setListener(this@MoviesFragment)
            adapter = mMovieAdapter.withLoadStateFooter(
                footer = MovieStateAdapter { mMovieAdapter.retry() }
            )
        }
        mHorizontallayoutmanger.get()?.let {
            it.reverseLayout = false
            it.isItemPrefetchEnabled = false
            moviesRecycler2.layoutManager = it
            moviesRecycler2.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        }
        moviesRecycler2.apply {
            setHasFixedSize(true)
            addItemDecoration(mGridSpacingItemDecoration)
            itemAnimator = DefaultItemAnimator()
            mMovieAdapter.setListener(this@MoviesFragment)
            adapter = mMovieAdapter.withLoadStateFooter(
                footer = MovieStateAdapter { mMovieAdapter.retry() }
            )
        }
        listenForAdapterStates()
    }

    private fun listenForAdapterStates() {
        viewModel.movies.observe(viewLifecycleOwner,
            { paging -> lifecycleScope.launch { mMovieAdapter.submitData(paging) } })
        btn_retry.setOnClickListener { mMovieAdapter.retry() }
        mMovieAdapter.addLoadStateListener(this)
    }

    override fun onMovieClicked(movieEntity: Movie) {
        viewModel.setSelectedMovie(movieEntity)
        activity?.let { findNavController().navigate(R.id.details, Bundle()) }
    }

    override fun invoke(loadState: CombinedLoadStates) {
        if (loadState.refresh is LoadState.Loading) {
            btn_retry.visibility = View.GONE
            loadingView.visibility = View.VISIBLE
        } else {
            loadingView.visibility = View.GONE
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> {
                    btn_retry.visibility = View.VISIBLE
                    loadState.refresh as LoadState.Error
                }
                else -> null
            }
            errorState?.error?.localizedMessage?.let { showMessage(it) }
        }
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { viewModel.getSearchLiveData().postValue(it) }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }
}

