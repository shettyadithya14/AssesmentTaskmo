package com.assesment.taskmo.detail.presentation

import com.assesment.taskmo.R
import com.assesment.taskmo.base.BaseFragment
import com.assesment.taskmo.detail.data.TrailerRemote
import com.assesment.taskmo.movies.presentation.MoviesViewModel
import com.assesment.taskmo.movies.presentation.MoviesViewModel.Companion.POSTER_BASE_URL
import com.assesment.taskmo.movies.presentation.MoviesViewModel.Companion.YOUTUBE_APP_URI
import com.assesment.taskmo.movies.presentation.MoviesViewModel.Companion.YOUTUBE_WEB_URI
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import javax.inject.Inject

class DetailFragment : BaseFragment<MoviesViewModel>() {

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mLinearLayoutManager: LinearLayoutManager



    override fun getLayoutId(): Int = R.layout.fragment_detail
    override fun getLifeCycleOwner(): LifecycleOwner = this

    override val viewModel by lazy {
        ViewModelProvider(requireActivity(), mViewModelFactory).get(MoviesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderMovieDetails()
    }



    private fun renderMovieDetails() {
        viewModel.getSelectedMovie()?.apply {
            tv_title.text = title
            tv_plot.text = overview
            tv_release_date.text = String.format(getString(R.string.released_in), release_date)
            tv_votes_count.text = String.format(getString(R.string.votes_count), vote_count.toString())
            Glide.with(requireActivity())
                    .load("$POSTER_BASE_URL${poster_path}")
                    .into(img_poster)
            viewModel.getLikeState(id)
            viewModel.fetchMovieTrailers(id)
        }
    }

    override fun renderViewState(data: Any) {
        when (data) {
            is DetailViewState.MessageRes -> showMessage(getString(data.resId))

        }
    }








}