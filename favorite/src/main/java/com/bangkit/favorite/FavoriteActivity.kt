package com.bangkit.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.di.favNewsModule
import com.bangkit.favorite.databinding.ActivityFavoriteBinding
import com.bangkit.submissionmade1.core.adapter.NewsAdapter
import com.bangkit.submissionmade1.ui.detail.DetailNewsActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private var binding: ActivityFavoriteBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        loadKoinModules(favNewsModule)
        supportActionBar?.title = "Favorite News"


        val newsAdapter = NewsAdapter()
        newsAdapter.onItemClick = {
            val intent = Intent(this, DetailNewsActivity::class.java)
            intent.putExtra(DetailNewsActivity.EXTRA_DATA, it)
            startActivity(intent)
        }

        favoriteViewModel.favNews.observe(this, { data ->
            newsAdapter.setData(data)
            binding?.viewEmpty?.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
        })

        setupAdapter(newsAdapter)

    }

    private fun setupAdapter(newsAdapter: NewsAdapter) {
        binding?.rvUsers?.layoutManager = LinearLayoutManager(this)
        binding?.rvUsers?.setHasFixedSize(true)
        binding?.rvUsers?.adapter = newsAdapter
    }
}