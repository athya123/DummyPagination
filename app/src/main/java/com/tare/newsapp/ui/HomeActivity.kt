package com.tare.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebView
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import autodispose2.AutoDispose.autoDisposable
import autodispose2.androidx.lifecycle.AndroidLifecycleScopeProvider
import com.tare.newsapp.R
import com.tare.newsapp.adapter.NewsAdapter
import com.tare.newsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var webView: WebView
    private lateinit var recyclerView: RecyclerView
    private val adapter = NewsAdapter()
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            viewModel = homeViewModel
            lifecycleOwner = this@HomeActivity
        }
        webView = binding.webView
        recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        adapter.viewModel = homeViewModel
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(recyclerView)
    }

    override fun onStart() {
        super.onStart()
        subscribeObservers()
    }

    override fun onBackPressed() {
        if (webView.visibility == View.VISIBLE) {
            webView.startAnimation(
                AnimationUtils.loadAnimation(
                    this,
                    android.R.anim.slide_out_right
                )
            )
            webView.visibility = View.INVISIBLE
        } else
            super.onBackPressed()

    }

    private fun subscribeObservers() {
        homeViewModel.flowable.to(autoDisposable(AndroidLifecycleScopeProvider.from(this)))
            .subscribe { adapter.submitData(lifecycle, it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}
